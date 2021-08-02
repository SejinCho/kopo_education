
function signUpFormCheck() {
	console.log('들어오나요')
	//이름
	if( $('#nameCheck').html() != "") { 
		console.log('이름:' + ($('#nameCheck').val() != ""))
		return false
	}
	//아이디
	if($('#user_idCheck').html() != '사용가능') { //아이디
		console.log('아디:' + ($('#nameCheck').val() != ""))
		return false
	}
	//비번
	if( $('#user_passwordCheck').html() != "" ) { //비번
		console.log('비번:' + ($('#nameCheck').val() != ""))
		return false
	}
	//비번 확인
	if( $('#user_rePasswordCheck').html() != "" ) { //재확인
		console.log('비번재확인:' + ($('#nameCheck').val() != ""))
		return false
	}
	//핸드폰
	if($('#mobileNoCheck').html() != "인증성공" ) {
		console.log('폰인증성공:' + ($('#nameCheck').val() != ""))
		return false
	}
	//주민번호
	if($('#jumin_noCheck').html() != "" ) {
		console.log('주민:' + ($('#nameCheck').val() != ""))
		return false
	}
	
	//이메일
	if($('#emailNoCheck').html() != "인증성공" ) {
		console.log('이메일:' + ($('#nameCheck').val() != ""))
		return false
	}
	//간편비번
	if( $('#easy_passwordCheck').html() != "" ) { //재확인
		console.log('간편비번:' + ($('#nameCheck').val() != ""))
		return false
	}
	//간편비번 확인
	if( $('#reEasy_passwordCheck').html() != "" ) { //재확인
		console.log('간편비번 재확인:' + ($('#nameCheck').val() != ""))
		return false
	}
	
	//생년월일
	if($('#birth').val() == "") {
		$('#birthCheck').html('필수 입력 정보입니다.')
		document.querySelector('#birthCheck').style.display = "block";
		console.log('생년월일:' + ($('#nameCheck').val() != ""))
        return false;
	}else {
		document.querySelector('#birthCheck').style.display = "none";
	}
	return true
}



