-- 다른은행 ->다른은행
create or replace function transfer_open
(
    n_name in sj_member.name%type,  --내이름
    n_send_account_number in sj_account_transfer_info.account_number%type, --보낸사람
    n_send_bank_code in sj_account_transfer_info.bank_code%type,
    
    n_receive_account_number in sj_account_transfer_info.account_number%type, --받는 사람
    n_receive_bank_code in sj_account_transfer_info.bank_code%type,
    
    n_tran_amt in sj_account_transfer_info.tran_amt%type,
    n_my_content in sj_account_transfer_info.content%type,
    n_receive_content in sj_account_transfer_info.content%type
)
return number  
is
    pragma autonomous_transaction;

    v_result number(1);
    v_error_message sj_transfer_exception_log.error_message%type;
begin
    --보내는 은행 
    --[ 다른은행 update(돈 -), insert (받는 은행) ]
    case 
        when n_send_bank_code = 'J' then --종범쓰
            insert into BANK_TRANSACTION@JBMorgan_link(TRANSACTION_NO, AMOUNT, COUNTERPART_ACCT_NO, TYPE, COUNTERPART, COUNTERPART_BANK, ACCT_NO) 
            VALUES(TRANSACTION_SEQ.NEXTVAL@JBMorgan_link, n_tran_amt, n_receive_account_number, '출금', n_name, 'S', n_send_account_number); 
            
            update bank_account@JBMorgan_link set balance = balance - n_tran_amt where acct_no = n_send_account_number; 
            
        when n_send_bank_code = 'D' then --소영쓰 DONJO_link 
            INSERT INTO TRANHISTORY@DONJO_link( TRANSEQ, MYACC, MYBANK, TR_CODE, BALANCE, OTHACC, OTHBANK)
            VALUES ( SEQ_TRANHISTORY_CODE.nextval@DONJO_link, n_send_account_number, 'D', 'M', n_tran_amt, n_receive_account_number, 'S' );
            
            update ACCOUNTDB@DONJO_link set balance = balance - n_tran_amt where ACCOUNT = n_send_account_number; 
            
        when n_send_bank_code = 'S' then --세진쓰 
            insert into sj_account_transfer_info(id, account_number, bank_code, tran_amt, content, inout_type) 
            values('transfer' || sj_seq_transfer_info_id.nextval, n_send_account_number, 'S', 
            n_tran_amt, n_receive_content, 'O');
            
            update sj_account_info set balance = balance - n_tran_amt where account_number = n_send_account_number;
            
        when n_send_bank_code = 'Y' then --현석쓰 YGBank_link
        
            INSERT INTO HISTORY@YGBank_link(HISTORY_SQ, CUSTOMER_ACCOUNT_SQ, HISTORY_TASK,RECEIVER_ACCOUNT,RECEIVER_BANK,BEFORE_CHANGE,MODIFY_CHANGE) 
            VALUES(HISTORY_SEQ.NEXTVAL@YGBank_link, (select CUSTOMER_ACCOUNT_SQ  from CUSTOMER_ACCOUNT@YGBank_link where ACCOUNT_NUMBER = n_send_account_number), 
            '출금', n_receive_account_number, 'S', (select CUSTOMER_ACCOUNT_CHANGE from CUSTOMER_ACCOUNT@YGBank_link where ACCOUNT_NUMBER = n_send_account_number), 
            n_tran_amt);

            update CUSTOMER_ACCOUNT@YGBank_link set 
            CUSTOMER_ACCOUNT_CHANGE = CUSTOMER_ACCOUNT_CHANGE - n_tran_amt where ACCOUNT_NUMBER = n_send_account_number;
    end case;
    
    --받는 은행
    --[ 세진은행 update(돈 +), insert ]
    case 
        when n_receive_bank_code = 'S' then--세진 은행이 받는 사람
            --insert 
            insert into sj_account_transfer_info(id, account_number, bank_code, tran_amt, content, inout_type) 
            values('transfer' || sj_seq_transfer_info_id.nextval, n_receive_account_number, n_receive_bank_code, 
            n_tran_amt, n_receive_content, 'I');
            
            -- update 
            update sj_account_info set balance = balance + n_tran_amt where account_number = n_receive_account_number;
    
        when n_receive_bank_code = 'J' then --종범은행이 받는 사람
            insert into BANK_TRANSACTION@JBMorgan_link(TRANSACTION_NO, AMOUNT, COUNTERPART_ACCT_NO, TYPE, COUNTERPART, COUNTERPART_BANK, ACCT_NO) 
            VALUES(TRANSACTION_SEQ.NEXTVAL@JBMorgan_link, n_tran_amt, n_send_account_number, '입금', n_name, 'S', n_receive_account_number); 
            
            update bank_account@JBMorgan_link set balance = balance + n_tran_amt where acct_no = n_receive_account_number; 
            
        when n_receive_bank_code = 'Y' then -- 현석은행이 받는 사람
            INSERT INTO HISTORY@YGBank_link(HISTORY_SQ, CUSTOMER_ACCOUNT_SQ, HISTORY_TASK,RECEIVER_ACCOUNT,RECEIVER_BANK,BEFORE_CHANGE,MODIFY_CHANGE) 
            VALUES(HISTORY_SEQ.NEXTVAL@YGBank_link, (select CUSTOMER_ACCOUNT_SQ  from CUSTOMER_ACCOUNT@YGBank_link where ACCOUNT_NUMBER = n_receive_account_number), 
            '입금', n_send_account_number, 'S', (select CUSTOMER_ACCOUNT_CHANGE from CUSTOMER_ACCOUNT@YGBank_link where ACCOUNT_NUMBER = n_receive_account_number), 
            n_tran_amt);

            update CUSTOMER_ACCOUNT@YGBank_link set 
            CUSTOMER_ACCOUNT_CHANGE = CUSTOMER_ACCOUNT_CHANGE + n_tran_amt where ACCOUNT_NUMBER = n_receive_account_number;
            
        when n_receive_bank_code = 'D' then --소영은행이 받는 사람
            INSERT INTO TRANHISTORY@DONJO_link( TRANSEQ, MYACC, MYBANK, TR_CODE, BALANCE, OTHACC, OTHBANK)
            VALUES ( SEQ_TRANHISTORY_CODE.nextval@DONJO_link, n_receive_account_number, 'D', 'A', n_tran_amt, n_send_account_number, 'S' );
            
            update ACCOUNTDB@DONJO_link set balance = balance + n_tran_amt where ACCOUNT = n_receive_account_number;
    end case;
    
    
    v_result := 1;
    commit;
    return v_result;
exception
    when others then
        rollback;        
        v_result := 0;

        begin --exception을 log 테이블에 기록
            v_error_message := sqlerrm;
            insert into sj_transfer_exception_log(id, program_name, error_message, description)
                values('exception' || sj_seq_transfer_exception_log.nextval,'transfer_to_s', v_error_message, 'send_account_number : ' || n_send_account_number || 
                ', n_receive_account_number : ' || n_receive_account_number || ', tran_amt : ' || n_tran_amt);
            commit;
            return v_result;
            exception
                when others then
                    null;
            end;
           
end;
