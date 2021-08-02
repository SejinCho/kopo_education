//********************************************
//               계좌 생성
//********************************************
var error = document.querySelectorAll('.error_next_box');
var mobile = document.querySelector('#mobile');
//모바일 인증번호 입력
var mobileNo = document.querySelector('#mobileNo');

var email = document.querySelector('#email');
//이메일 인증번호 입력
var emailNo = document.querySelector('#emailNo');

//비밀번호 6자리 확인
var pw = document.querySelector('#pswd');
var pwMsg = document.querySelector('#alertTxt');
var pwImg = document.querySelector('#pswd_img');
var pwMsgArea = document.querySelector('.int_pass');


//계좌 비밀번호
var pw1 = document.querySelector('#pswd1');
var pwMsg = document.querySelector('#alertTxt');
var pwImg1 = document.querySelector('#pswd1_img1');

var pw2 = document.querySelector('#pswd2');
var pwImg2 = document.querySelector('#pswd2_img1');
var pwMsgArea = document.querySelector('.int_pass');

//핸드폰
function checkPhoneNum(phoneAuthNum) {
	var isPhoneNum = /([01]{2})([01679]{1})([0-9]{3,4})([0-9]{4})/;
	if(mobile.value === "") {
        error[1].innerHTML = "필수 정보입니다.";
        error[1].style.display = "block";
        return false;
    } else if(!isPhoneNum.test(mobile.value)) {
        error[1].innerHTML = "형식에 맞지 않는 번호입니다.";
        error[1].style.display = "block";
        return false;
    } else {
        error[1].style.display = "none";
        return true;
    }
}

//*************핸드폰 인증번호 일치************** error 2 
function checkPhoneNo(phoneAuthNum) {
	if(phoneAuthNum !== mobileNo.value){
    	error[2].innerHTML = "핸드폰 인증번호가 일치하지 않습니다.";
        error[2].style.display = "block";
        return false;
    }else{
    	error[2].style.display = "none";
    	return true;
    }
}


//이메일 입력창
function checkEmail() {
	var isEmail = /^[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*.[a-zA-Z]{2,3}$/i;
	if(email.value === "") {
        error[3].innerHTML = "필수 정보입니다.";
        error[3].style.display = "block";
        return false;
    } else if(!isEmail.test(email.value)) {
        error[3].innerHTML = "형식에 맞지 않는 번호입니다.";
        error[3].style.display = "block";
        return false;
    }  else {
        error[3].style.display = "none";
        return true;
    }
}

//이메일 인증번호 일치
function checkEmailNo(emailAuthNum) {
	if(emailAuthNum !== emailNo.value){
    	error[4].innerHTML = "이메일 인증번호가 일치하지 않습니다.";
        error[4].style.display = "block";
        return false;
    }else{
    	error[4].style.display = "none";
    	return true;
    }
}



//비밀번호 확인
function checkPw(easy_password) {
    var pwPattern = /[0-9]{6}$/;
    if(pw.value === "") {
        error[0].innerHTML = "필수 정보입니다.";
        error[0].style.display = "block";
        return false;
    } else if(!pwPattern.test(pw.value)) {
        error[0].innerHTML = "6자리 숫자만 가능합니다.";
        pwMsg.innerHTML = "사용불가";
        pwMsgArea.style.paddingRight = "93px";
        error[0].style.display = "block";
        
        pwMsg.style.display = "block";
        pwImg.src = "/Sejin-Bank/resources/img/signUp/m_icon_not_use.png";
        return false;
    } else if(pw.value !== easy_password){ 
    	error[0].innerHTML = "간편 비밀번호가 일치하지 않습니다.";
        pwMsg.innerHTML = "불일치";
        pwMsgArea.style.paddingRight = "93px";
        error[0].style.display = "block";
        
        pwMsg.style.display = "block";
        pwImg.src = "/Sejin-Bank/resources/img/signUp/m_icon_not_use.png";
        return false;
    } else {
        error[0].style.display = "none";
        pwMsg.innerHTML = "안전";
        pwMsg.style.display = "block";
        pwMsg.style.color = "#03c75a";
        pwImg.src = "/Sejin-Bank/resources/img/signUp/m_icon_safe.png";
        return true;
    }
}


//라디오 박스 확인
function radioCheck(radio1, radio2) {
	if(radio1 === 'y' ) {
		error[5].innerHTML = "통장대여 요청은 처벌 대상입니다. ";
        error[5].style.display = "block";
        return false;
	} else if(radio1 === 'n'){
		error[5].style.display = "none";
        if(radio2 === 'n') {
        	error[6].style.display = "none";
        	return true;
        }
        error[6].innerHTML = "통장대개설 요청은 처벌 대상입니다. ";
        error[6].style.display = "block";
        return false;
	} else if(radio2 === 'y') {
		error[6].innerHTML = "통장대개설 요청은 처벌 대상입니다. ";
        error[6].style.display = "block";
        return false;
	} else if(radio2 === 'n'){
		error[6].style.display = "none";
        if(radio1 === 'n') {
        	error[5].style.display = "none";
        	return true;
        } 
        error[5].innerHTML = "통장대여 요청은 처벌 대상입니다. ";
        error[5].style.display = "block";
        return false;
	}
}


//비밀번호 확인
function checkAccountPw() {
    var pwPattern = /[0-9]{4}$/;
    if(pw1.value === "") {
        error[7].innerHTML = "필수 정보입니다.";
        error[7].style.display = "block";
        return false;
    } else if(!pwPattern.test(pw1.value)) {
        error[7].innerHTML = "4자리 숫자만 가능합니다.";
        pwMsg.innerHTML = "사용불가";
        pwMsgArea.style.paddingRight = "93px";
        error[7].style.display = "block";
        
        pwMsg.style.display = "block";
        pwImg1.src = "/Sejin-Bank/resources/img/signUp/m_icon_not_use.png";
        return false;
    } else {
        error[7].style.display = "none";
        pwMsg.innerHTML = "안전";
        pwMsg.style.display = "block";
        pwMsg.style.color = "#03c75a";
        pwImg1.src = "/Sejin-Bank/resources/img/signUp/m_icon_safe.png";
        return true;
    }
}


//간편비밀번호 재입력
function comparePw() {
    if(pw2.value === pw1.value && pw2.value !== "") {
        pwImg2.src = "/Sejin-Bank/resources/img/signUp/m_icon_check_enable.png";
        error[8].style.display = "none";
        return true;
    } else if(pw2.value !== pw1.value) {
        pwImg2.src = "/Sejin-Bank/resources/img/signUp/m_icon_check_disable.png";
        error[8].innerHTML = "비밀번호가 일치하지 않습니다.";
        error[8].style.display = "block";
        return false;
    } 

    if(pw2.value === "") {
        error[8].innerHTML = "필수 정보입니다.";
        error[8].style.display = "block";
        return false;
    }
}


//********************************************
//					계좌 조회
//********************************************


//********************************************
//					계좌 이체
//********************************************

//계좌 비밀번호(사용자가 적은 것)
var account_password = document.querySelector('#account_password');

//전체 흐름
function availTransfer(ajax_pw, bank_code,receive_account_number) {
	//계좌 비번 확인
	let result = availTransferPw(ajax_pw)
	if(! result) {
		return false
	}
	//계좌번호 패턴 확인
	result = availTransferBankCode(bank_code,receive_account_number)
	return result
}

//계좌 비번 확인
function availTransferPw(ajax_pw) {
	if(ajax_pw != account_password.value) {
		$('#account_password_check').text('비밀번호가 일치하지 않습니다.')
		return false
	}else {
		$('#account_password_check').text('')
		return true
	}
}

//계좌번호 패턴 확인
function availTransferBankCode(bank_code,receive_account_number) {
	let pwPattern = /[0-9]{13}$/; //기본이 세진은행
	
	if(bank_code == 'S') { //세진은행
		if(! pwPattern.test(receive_account_number)){ //패턴이 틀릴경우
			$('#account_receive_account_check').text('계좌번호를 확인해주세요.')
			return false
		} 
	}
	
	if(bank_code == 'J') { //종범은행
		pwPattern = /[0-9]{14}$/;
		if(! pwPattern.test(receive_account_number)){ //패턴이 틀릴경우
			$('#account_receive_account_check').text('계좌번호를 확인해주세요.')
			return false
		} 
	}
	
	if(bank_code == 'D') { //소영은행
		pwPattern = /[0-9]{13}$/;
		if(! pwPattern.test(receive_account_number)){ //패턴이 틀릴경우
			$('#account_receive_account_check').text('계좌번호를 확인해주세요.')
			return false
		} 
	}
	
	if(bank_code == 'Y') { //현석은행
		pwPattern = /[0-9]{13}$/;
		if(! pwPattern.test(receive_account_number)){ //패턴이 틀릴경우
			$('#account_receive_account_check').text('계좌번호를 확인해주세요.')
			return false
		} 
	}
	$('#account_receive_account_check').text('')
	return true
}






