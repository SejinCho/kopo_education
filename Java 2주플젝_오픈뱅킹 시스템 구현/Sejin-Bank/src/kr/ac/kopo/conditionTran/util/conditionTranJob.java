package kr.ac.kopo.conditionTran.util;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import kr.ac.kopo.account.vo.AccountTransferInfoVO;
import kr.ac.kopo.conditionTran.service.ConditionTranServiceImpl;
import kr.ac.kopo.conditionTran.vo.ConditionTranVO;
import kr.ac.kopo.openBanking.service.OpenBankingServiceImpl;

/**
 * @author homo.efficio@gmail.com
 * created on 2018-08-12
 */

public class conditionTranJob implements Job {
    @Override
    public void execute(JobExecutionContext arg0) throws JobExecutionException {
    	ConditionTranServiceImpl conditionService = new ConditionTranServiceImpl();
    	OpenBankingServiceImpl openBankingService = new OpenBankingServiceImpl();
    	
        //현재 날짜, 시간
        SimpleDateFormat format_date = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat format_time = new SimpleDateFormat("HH:mm");
        Date time = new Date();
        
        String condition_tran_date = format_date.format(time);
        String condition_tran_time = format_time.format(time);
        
        //condition table에서 현재 날짜 시간에 맞는 거 찾아오기
        List<ConditionTranVO> conditionTranList = conditionService.getReserveTranList(condition_tran_date, condition_tran_time);
        
        AccountTransferInfoVO transInfo = new AccountTransferInfoVO();
        if(! conditionTranList.isEmpty()) {
        	for(ConditionTranVO vo : conditionTranList) {
        		
        		transInfo.setName(vo.getName());
				transInfo.setSend_account_number(vo.getSend_account_number());
				transInfo.setReceive_account_number(vo.getReceive_account_number());
				transInfo.setSend_bank_code(vo.getSend_bank_code());
				transInfo.setReceive_bank_code(vo.getReceive_bank_code());
				transInfo.setTran_amt(vo.getTran_amt());
				transInfo.setMy_content(vo.getMy_content());
				transInfo.setReceive_content(vo.getReceive_content());
        		openBankingService.transferReceive(transInfo); //계좌이체
        		
        		//condition table 계좌이체 후 상태 성공(S)으로 update
        		conditionService.updateReserveState(vo.getId());
        	}
        }else {
        	System.out.println("여기는 없앨거야");
        }
        
        //이체 ㄱㄱ
        
    }
}