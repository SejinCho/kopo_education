<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lnag="ko">
    <head>
	    <meta charset="utf-8">
	    <meta http-equiv="x-ua-compatible" content="ie=edge">
	    <title>sign up</title>
	    <meta name="description" content="">
	    <meta name="viewport" content="width=device-width, initial-scale=1">
	
	    <!-- <link rel="manifest" href="site.webmanifest"> -->
	    <link rel="shortcut icon" type="image/x-icon" href="<%=request.getContextPath()%>/resources/img/favicon.png">
	    <!-- Place favicon.ico in the root directory -->
	
	    <!-- CSS here -->
	    
	    <link rel="stylesheet" href="https://ajax.googleapis.com/ajax/libs/jqueryui/1.11.2/themes/smoothness/jquery-ui.css">
		
	    
	    <link rel="stylesheet" href="<%=request.getContextPath()%>/resources/css/signUpCss.css">
	    <!-- <link rel="stylesheet" href="css/responsive.css"> -->
	    
	    <script src="<%=request.getContextPath()%>/resources/js/jquery-3.6.0.min.js"></script>
	    <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
		<script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
		<script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
		
		
		<script type="text/javascript">
			$(document).ready(function(){
				
				//이름 
				$('#name').keyup(function(){
					if($('#name').val() == "") {
						$('#nameCheck').html('필수 정보입니다.')
						document.querySelector('#nameCheck').style.display = "block";
					}else if(!/[a-zA-Z가-힣]/.test($('#name').val())){
						$('#nameCheck').html('한글과 영문 대 소문자를 사용하세요. (특수기호, 공백 사용 불가).')
						document.querySelector('#nameCheck').style.display = "block";
						
					} else{
						$('#nameCheck').empty()
						document.querySelector('#nameCheck').style.display = "none";
					}
					
				})
				
				//아이디
				$('#user_id').keyup(function(){
					
					if($('#user_id').val() == "") {
						$('#user_idCheck').html('사용할 아이디를 입력해주세요.')
						document.querySelector('#user_idCheck').style.display = "block";
					}  else if ( !/^[a-z0-9_]{4,20}$/.test($('#user_id').val()) ) {
						$('#user_idCheck').html('아이디는 대소문자, 숫자만 입력가능합니다.(4자리에서 20자리까지 가능)')
						document.querySelector('#user_idCheck').style.display = "block";
					} else {
						$.ajax({
							type: "POST",
							url : "<%=request.getContextPath()%>/member/userIdValidAjax.do",
							mathod : 'post',
							async: false,
							data : {
								"user_id" : $('#user_id').val()
							},
							success : function(data){ 
								console.log($.trim(data));
								let result = $.trim(data)
								console.log(typeof(result))
								$('#user_idCheck').empty()
								
								if(result == 'true'){ //아이디 사용 가능
									$('#user_idCheck').html('사용가능')
									document.querySelector('#user_idCheck').style.color = "#03c75a";
									document.querySelector('#user_idCheck').style.display = "block";
								}else{ //아이디 사용 불가
									$('#user_idCheck').html('중복되는 아이디입니다.')
									document.querySelector('#user_idCheck').style.display = "block";
									
								}
							}
						})
					}
					
				})
				
				
				//패스워드
				let pwMsg = document.querySelector('.user_password');
				let pwImg = document.querySelector('#pswd_img');
				let pwMsgArea = document.querySelector('.int_pass');
				
				//패스워드
				$('#user_password').keyup(function(){
					
					if($('#user_password').val() == "") {
						$('#user_passwordCheck').html('필수 정보입니다.')
						document.querySelector('#user_passwordCheck').style.display = "block";
					} else if( !/^(?=.*[a-zA-Z])(?=.*[!@#$%^*+=-])(?=.*[0-9]).{8,25}$/.test($('#user_password').val())) {
						$('#user_passwordCheck').html('숫자+영문자+특수문자 조합으로 8자리 이상 사용')
						pwMsg.innerHTML = "사용불가";
						pwMsgArea.style.paddingRight = "93px";
						document.querySelector('#user_passwordCheck').style.display = "block";
						
						pwMsg.style.display = "block";
						pwImg.src = "/Sejin-Bank/resources/img/signUp/m_icon_not_use.png";
						
					} else{
						$('#user_passwordCheck').empty()
						document.querySelector('#user_passwordCheck').style.display = "none";
						pwMsg.innerHTML = "안전";
				        pwMsg.style.color = "#03c75a";
				        pwMsg.style.display = "block";
						pwImg.src = "/Sejin-Bank/resources/img/signUp/m_icon_safe.png";
					}
				})
				
				let rePswd_img = document.querySelector('#rePswd_img');
				//패스워드 확인
				$('#user_rePassword').keyup(function(){
					if($('#user_password').val() != $('#user_rePassword').val()) {
						$('#user_rePasswordCheck').html('비밀번호가 일치하지 않습니다.')
						document.querySelector('#user_rePasswordCheck').style.display = "block";
					} else{
						rePswd_img.src = "/Sejin-Bank/resources/img/signUp/m_icon_check_enable.png";
						$('#user_rePasswordCheck').empty()
						document.querySelector('#user_rePasswordCheck').style.display = "none";
					}
					
				})
				
				
				//휴대전화
				$('#mobile').keyup(function(){
					if($('#mobile').val() == "") {
						$('#mobileCheck').html('핸드폰 번호를 입력하세요.')
						document.querySelector('#mobileCheck').style.display = "block";
					}else if( !/([01]{2})([01679]{1})([0-9]{3,4})([0-9]{4})/.test($('#mobile').val()) ) {
						$('#mobileCheck').html('형식에 맞지 않는 번호입니다.')
						document.querySelector('#mobileCheck').style.display = "block";
					} else{
						$('#mobileCheck').empty()
						document.querySelector('#mobileCheck').style.display = "none";
					}
				})
				
				let phoneAuthNum = ''
				
				//핸드폰 인증 클릭
				$('#smsAuth').click(function(){
					
					$.ajax({
						type: "POST",
						url : "<%=request.getContextPath()%>/auth/smsAuth.do",
						method : 'post',
						data : {
							"phone" : $('#mobile').val()
						},
						success : function(data) {
							console.log('핸드폰 인증번호(핸드폰 인증 클릭) : ' + $.trim(data));
							phoneAuthNum = $.trim(data) 
							$('#mobileCheck').html('문자가 전송되었습니다 인증번호를 입력하세요.')
							document.querySelector('#mobileCheck').style.display = "block";
							
						},
						error: function (request, status, error){
							var msg = "ERROR : " + request.status + "<br>"
							msg += + "내용 : " + request.responseText + "<br>" + error;
							console.log(msg);
						}

					})
						
					
				})
				
				//핸드폰 인증번호 입력
				$('#mobileNo').keyup(function(){
					if(phoneAuthNum == '') {
						$('#mobileNoCheck').html('인증하기 버튼을 클릭해주세요.')
						document.querySelector('#mobileNoCheck').style.display = "block";
						
					}else if(phoneAuthNum == $('#mobileNo').val() ) {
						$('#mobileNoCheck').html('인증성공')
						document.querySelector('#mobileNoCheck').style.color = "#03c75a";
						document.querySelector('#mobileNoCheck').style.display = "block";
						
					}else{
						$('#mobileNoCheck').html('인증번호가 잘못 입력되었습니다.')
						document.querySelector('#mobileNoCheck').style.display = "block";
					}
				})
				
				
				//주민번호
				$('#jumin_no').keyup(function(){
					if($('#jumin_no').val() == "") {
						$('#jumin_noCheck').html('필수 정보입니다.')
						document.querySelector('#jumin_noCheck').style.display = "block";
						
					}else if(!/[0-9]{13}$/.test($('#jumin_no').val())){
						$('#jumin_noCheck').html('형식에 맞지 않습니다.')
						document.querySelector('#jumin_noCheck').style.display = "block";
						
					} else{
						$('#jumin_noCheck').empty()
						document.querySelector('#jumin_noCheck').style.display = "none";
					}
				})
				
				//이메일
				$('#email').keyup(function(){
					if($('#email').val() == "") {
						$('#emailCheck').html('이메일을 입력하세요.')
						document.querySelector('#emailCheck').style.display = "block";
					}else if( !/^[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*.[a-zA-Z]{2,3}$/i.test($('#email').val()) ) {
						$('#emailCheck').html('형식에 맞지 않습니다.')
						document.querySelector('#emailCheck').style.display = "block";
					} else{
						$('#emailCheck').empty()
						document.querySelector('#emailCheck').style.display = "none";
					}
				})
				
				let emailAuthNum = '';
				//이메일 인증 클릭
				$('#emailAuth').click(function(){
					$.ajax({
						type: "POST",
						url : "<%=request.getContextPath()%>/auth/emailAuth.do",
						method : 'post',
						data : {
							"email" : $('#email').val()
						},
						dataType: "text",
						success : function(data) {
					        console.log('이멜 인증 번호 : ' + $.trim(data));
					        emailAuthNum = $.trim(data)
					        $('#emailCheck').html('이메일이 전송되었습니다 인증번호를 입력하세요.')
							document.querySelector('#emailCheck').style.display = "block";
						},
						error: function (request, status, error){
							var msg = "ERROR : " + request.status + "<br>"
							msg += + "내용 : " + request.responseText + "<br>" + error;
							console.log(msg);
						}

					})
						
						
				})
				
				//이메일 인증번호 입력
				$('#emailNo').keyup(function(){
					if(emailAuthNum == '') {
						$('#emailNoCheck').html('인증하기 버튼을 클릭해주세요.')
						document.querySelector('#emailNoCheck').style.display = "block";
						
					}else if(emailAuthNum == $('#emailNo').val() ) {
						$('#emailNoCheck').html('인증성공')
						document.querySelector('#emailNoCheck').style.color = "#03c75a";
						document.querySelector('#emailNoCheck').style.display = "block";
						
					}else{
						$('#emailNoCheck').html('인증번호가 잘못 입력되었습니다.')
						document.querySelector('#emailNoCheck').style.display = "block";
					}
				})
				
				//간편비밀번호
				let pwMsg1 = document.querySelector('.easy_password');
				let pwImg1 = document.querySelector('#pswd1_img1');
				
				//간편비밀번호
				$('#easy_password').keyup(function(){
					
					if($('#easy_password').val() == "") {
						$('#easy_passwordCheck').html('필수 정보입니다.')
						document.querySelector('#easy_passwordCheck').style.display = "block";
						
					} else if( !/[0-9]{6}$/.test($('#easy_password').val())) {
						$('#easy_passwordCheck').html('6자리 숫자만 가능합니다.')
						pwMsg1.innerHTML = "사용불가";
						pwMsgArea.style.paddingRight = "93px";
						document.querySelector('#easy_passwordCheck').style.display = "block";
						
						pwMsg1.style.display = "block";
						pwImg1.src = "/Sejin-Bank/resources/img/signUp/m_icon_not_use.png";
						
					} else{
						$('#easy_passwordCheck').empty()
						document.querySelector('#easy_passwordCheck').style.display = "none";
						pwMsg1.innerHTML = "안전";
				        pwMsg1.style.color = "#03c75a";
				        pwMsg1.style.display = "block";
						pwImg1.src = "/Sejin-Bank/resources/img/signUp/m_icon_safe.png";
					}
				})
				
				let pwImg2 = document.querySelector('#pswd2_img1');
				
				//간편비밀번호 확인
				$('#reEasy_password').keyup(function(){
					if($('#easy_password').val() != $('#reEasy_password').val()) {
						$('#reEasy_passwordCheck').html('비밀번호가 일치하지 않습니다.')
						document.querySelector('#reEasy_passwordCheck').style.display = "block";
					} else{
						pwImg2.src = "/Sejin-Bank/resources/img/signUp/m_icon_check_enable.png";
						$('#reEasy_passwordCheck').empty()
						document.querySelector('#reEasy_passwordCheck').style.display = "none";
					}
					
				})
				
				
				
			})//document ready
			
		
		</script>
	</head>
    <body>
        <!-- header -->
        <header>
			<jsp:include page="/pages/include/topMenu.jsp"></jsp:include>
		</header>
        <!-- wrapper -->
        <div id="wrapper">
			
            <!-- content-->
            <div id="content">
            	<form action="<%=request.getContextPath()%>/member/joinPro.do" onsubmit="return signUpFormCheck()" method="post"> 
            	<input type="hidden" name="kakao_id" value="${kakao_id}">
            	
            		<h2 align="center">회원가입</h2>
            		
					<!-- NAME -->
	                <div>
	                    <h3 class="join_title"><label for="name">이름</label></h3>
	                    <span class="box int_name">
	                        <input type="text" id="name" name="name" class="int" maxlength="20">
	                    </span>
	                    <span class="error_next_box" id="nameCheck"></span>
	                </div>
	                
	                <!-- id -->
	                <div>
	                    <h3 class="join_title"><label for="name">ID</label></h3>
	                    <span class="box int_name">
	                        <input type="text" id="user_id" name="user_id" class="int" maxlength="20">
	                    </span>
	                    <span class="error_next_box" id="user_idCheck"></span>
	                </div>
	                
	                <!-- 패스워드 -->
	                <div>
	                    <h3 class="join_title"><label for="pswd1">비밀번호</label></h3>
	                    <span class="box int_pass">
	                        <input type="password" id="user_password" name="user_password" class="int" maxlength="20">
	                        <span id="alertTxt" class="user_password">사용불가</span>
	                        <img src="<%=request.getContextPath()%>/resources/img/signUp/m_icon_pass.png" id="pswd_img" class="pswdImg">
	                    </span>
	                    <span class="error_next_box" id="user_passwordCheck"></span>
	                </div>
	                
	                <!-- 패스워드 확인 -->
	                <div>
	                    <h3 class="join_title"><label for="pswd2">비밀번호 확인</label></h3>
	                    <span class="box int_pass_check">
	                        <input type="password" id="user_rePassword" class="int" maxlength="20">
	                        <img src="<%=request.getContextPath()%>/resources/img/signUp/m_icon_check_disable.png" id="rePswd_img" class="pswdImg">
	                    </span>
	                    <span class="error_next_box" id="user_rePasswordCheck"></span>
	                </div>
	                
	                <!-- 모바일 -->
	                <div>
						<h3 class="join_title"><label for="addr">휴대전화(- 제외)</label></h3>
						<div id="bir_wrap">
							<div >
								<span class="box">
									<input type="text" class="int" id="mobile" name="phone" placeholder="전화번호 입력">
								</span>
								<span class="error_next_box" id="mobileCheck"></span>
							</div>
							
							<div id="bir_mm">	
								<span class="box">
									<input type="button" class="int" id="smsAuth" value="전화번호 인증"><br>
								</span>
							</div>
						</div>
	                </div>
	                
	                <!-- 모바일 인증번호 입력창 -->
	                <div>
						<h3 class="join_title"><label for="addr">휴대전화 인증번호 입력</label></h3>
						<div id="bir_wrap">
							<div >
								<span class="box">
									<input type="text" class="int" id="mobileNo"  placeholder="인증번호 입력" required>
								</span>
								<span class="error_next_box" id="mobileNoCheck"></span>
							</div>
							
							<div id="bir_mm">	
							</div>
						</div>
	                </div>
	                
	                <!-- 주민번호 -->
	                <div>
	                    <h3 class="join_title"><label for="name">주민등록번호</label></h3>
	                    <span class="box int_name">
	                        <input type="password" id="jumin_no" name="jumin_no" class="int" maxlength="20">
	                    </span>
	                    <span class="error_next_box" id="jumin_noCheck"></span>
	                </div>
	                
	                <!-- 이메일 -->
	                <div>
						<h3 class="join_title"><label for="addr">이메일</label></h3>
						<div id="bir_wrap">
							<div >
								<span class="box">
									<input type="text" class="int" id="email" name="email" placeholder="이메일 입력">
								</span>
								<span class="error_next_box" id="emailCheck"></span>
							</div>
							
							<div id="bir_mm">	
								<span class="box">
									<input type="button" class="int" id="emailAuth"  value="이메일 인증"><br>
								</span>
							</div>
						</div>
	                </div>
	                
	                <!-- 이메일 인증번호 입력창 -->
	                <div>
						<h3 class="join_title"><label for="addr">이메일 인증번호 입력</label></h3>
						<div id="bir_wrap">
							<div >
								<span class="box">
									<input type="text" class="int" id="emailNo"  placeholder="인증번호 입력" required>
								</span>
								<span class="error_next_box" id="emailNoCheck"></span>
							</div>
							
							<div id="bir_mm">	
							</div>
						</div>
	                </div>
	                
	                
	                <!-- 간편 비밀번호 -->
	                <!-- PW1 -->
	                <div>
	                    <h3 class="join_title"><label for="pswd1">간편 비밀번호(6자리)</label></h3>
	                    <span class="box int_pass">
	                        <input type="password" id="easy_password" name="easy_password" class="int" maxlength="20">
	                        <span id="alertTxt" class="easy_password">사용불가</span>
	                        <img src="<%=request.getContextPath()%>/resources/img/signUp/m_icon_pass.png" id="pswd1_img1" class="pswdImg">
	                    </span>
	                    <span class="error_next_box" id="easy_passwordCheck"></span>
	                </div>
	
	                <!-- PW2 -->
	                <div>
	                    <h3 class="join_title"><label for="pswd2">간편 비밀번호 재확인 (6자리)</label></h3>
	                    <span class="box int_pass_check">
	                        <input type="password" id="reEasy_password" class="int" maxlength="20">
	                        <img src="<%=request.getContextPath()%>/resources/img/signUp/m_icon_check_disable.png" id="pswd2_img1" class="pswdImg">
	                    </span>
	                    <span class="error_next_box" id="reEasy_passwordCheck"></span>
	                </div>
	                
	                
	
	                <!-- BIRTH -->
	                
	                <div>
	                    <h3 class="join_title"><label for="yy">생년월일</label></h3>
	                    <span class="box int_bir">
	                    	<input type="date" id="birth" name="birth" class="int" maxlength="16" required="required">
	                    </span>
	                    <span class="error_next_box" id="birthCheck"></span>
	                    
	                </div>
	                
	
	                <!-- GENDER -->
	                <div>
	                    <h3 class="join_title"><label for="gender">성별</label></h3>
	                    <span class="gender_code">
	                        <select id="gender" class="sel" name="gender" required="required">
	                            <option>선택</option>
	                            <option value="M">남자</option>
	                            <option value="F">여자</option>
	                        </select>                            
	                    </span>
	                    <span class="error_next_box"></span>
	                </div>
					
					<br><br><br>
					
					<!-- addr -->
					<div>
						<!-- 우편주소 -->
						<h3 class="join_title"><label for="addr">우편번호</label></h3>
						<div id="bir_wrap">
							<div >
								<span class="box">
									<input type="text" class="int" name="zipcode" id="sample4_postcode" placeholder="우편번호 찾기를 눌러주세요." required="required" readonly>
								</span>
								<span class="error_next_box"></span>
							</div>
							
							<div id="bir_mm">	
								<span class="box">
									<input type="button" class="int" onclick="sample4_execDaumPostcode()" value="우편번호 찾기"><br>
								</span>
							</div>
						</div>
						
						<!-- 도로명 주소 -->
		                <div>
		                    <h3 class="join_title"><label for="name">도로명 주소</label></h3>
		                    <span class="box int_name">
		                        <input type="text" class="int" name="addr1_load" id="sample4_roadAddress" placeholder="우편번호 찾기를 눌러주세요." required="required" readonly>
		                    </span>
		                    <span class="error_next_box"></span>
		                </div>
		                
		                <!-- 지번 주소 -->
		                <div>
		                    <h3 class="join_title"><label for="name">지번 주소</label></h3>
		                    <span class="box int_name">
		                        <input type="text" class="int" name="addr1_jibun" id="sample4_jibunAddress" placeholder="우편번호 찾기를 눌러주세요." required="required" readonly>
		                    </span>
		                    <span id="guide" style="color:#999;display:none"></span>
		                    <span class="error_next_box"></span>
		                </div>
		                
		                <!-- 상세 주소 -->
		                <div>
		                    <h3 class="join_title"><label for="name">상세주소</label></h3>
		                    <span class="box int_name">
		                    	<input type="text" class="int" name="addr2" id="sample4_detailAddress" placeholder="상세주소" required="required">
		                    </span>
		                    <span class="error_next_box"></span>
		                </div>
						
					</div>
					
					
					<!-- 카카오 알림 받을건지 -->
	                <div>
	                    <h3 class="join_title"><label for="name">카카오톡 알림을 받으시겠습니까?</label></h3>
	                    <span>
	                    	<input type="radio" id="receive_sms_yn" name="receive_sms_yn" value="Y" > <label for="name">수신</label>
	                    	<input type="radio" id="receive_sms_yn" name="receive_sms_yn" value="N"> <label for="name">수신 거부</label>
	                    </span>
	                    <span class="error_next_box"></span>
	                </div>
	               
	
	                <!-- JOIN BTN-->
	                <div class="btn_area">
	                	<input type="submit" id="btnJoin" >
	                </div>

                </form>

            </div> 
            <!-- content-->

        </div> 
        <!-- wrapper -->
    <script src="<%=request.getContextPath()%>/resources/js/signUp.js"></script>
    <script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
    <script type="text/javascript">
	    function sample4_execDaumPostcode() {
	        new daum.Postcode({
	            oncomplete: function(data) {
	                // 팝업에서 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분.
	
	                // 도로명 주소의 노출 규칙에 따라 주소를 표시한다.
	                // 내려오는 변수가 값이 없는 경우엔 공백('')값을 가지므로, 이를 참고하여 분기 한다.
	                var roadAddr = data.roadAddress; // 도로명 주소 변수
	                var extraRoadAddr = ''; // 참고 항목 변수
	
	                // 법정동명이 있을 경우 추가한다. (법정리는 제외)
	                // 법정동의 경우 마지막 문자가 "동/로/가"로 끝난다.
	                if(data.bname !== '' && /[동|로|가]$/g.test(data.bname)){
	                    extraRoadAddr += data.bname;
	                }
	                // 건물명이 있고, 공동주택일 경우 추가한다.
	                if(data.buildingName !== '' && data.apartment === 'Y'){
	                   extraRoadAddr += (extraRoadAddr !== '' ? ', ' + data.buildingName : data.buildingName);
	                }
	                // 표시할 참고항목이 있을 경우, 괄호까지 추가한 최종 문자열을 만든다.
	                if(extraRoadAddr !== ''){
	                    extraRoadAddr = ' (' + extraRoadAddr + ')';
	                }
	
	                // 우편번호와 주소 정보를 해당 필드에 넣는다.
	                document.getElementById('sample4_postcode').value = data.zonecode;
	                document.getElementById("sample4_roadAddress").value = roadAddr;
	                document.getElementById("sample4_jibunAddress").value = data.jibunAddress;
	                
	
	                var guideTextBox = document.getElementById("guide");
	                // 사용자가 '선택 안함'을 클릭한 경우, 예상 주소라는 표시를 해준다.
	                if(data.autoRoadAddress) {
	                    var expRoadAddr = data.autoRoadAddress + extraRoadAddr;
	                    guideTextBox.innerHTML = '(예상 도로명 주소 : ' + expRoadAddr + ')';
	                    guideTextBox.style.display = 'block';
	
	                } else if(data.autoJibunAddress) {
	                    var expJibunAddr = data.autoJibunAddress;
	                    guideTextBox.innerHTML = '(예상 지번 주소 : ' + expJibunAddr + ')';
	                    guideTextBox.style.display = 'block';
	                } else {
	                    guideTextBox.innerHTML = '';
	                    guideTextBox.style.display = 'none';
	                }
	            }
	        }).open();
	    }
    </script>
    
    
	<footer>
		<jsp:include page="/pages/include/bottom.jsp"></jsp:include>	
	</footer>
	<script src="<%=request.getContextPath()%>/resources/js/vendor/modernizr-3.5.0.min.js"></script>
    <script src="<%=request.getContextPath()%>/resources/js/vendor/jquery-1.12.4.min.js"></script>
    <script src="<%=request.getContextPath()%>/resources/js/popper.min.js"></script>
    <script src="<%=request.getContextPath()%>/resources/js/bootstrap.min.js"></script>
    <script src="<%=request.getContextPath()%>/resources/js/owl.carousel.min.js"></script>
    <script src="<%=request.getContextPath()%>/resources/js/isotope.pkgd.min.js"></script>
    <script src="<%=request.getContextPath()%>/resources/js/ajax-form.js"></script>
    <script src="<%=request.getContextPath()%>/resources/js/waypoints.min.js"></script>
    <script src="<%=request.getContextPath()%>/resources/js/jquery.counterup.min.js"></script>
    <script src="<%=request.getContextPath()%>/resources/js/imagesloaded.pkgd.min.js"></script>
    <script src="<%=request.getContextPath()%>/resources/js/scrollIt.js"></script>
    <script src="<%=request.getContextPath()%>/resources/js/jquery.scrollUp.min.js"></script>
    <script src="<%=request.getContextPath()%>/resources/js/wow.min.js"></script>
    <script src="<%=request.getContextPath()%>/resources/js/nice-select.min.js"></script>
    <script src="<%=request.getContextPath()%>/resources/js/jquery.slicknav.min.js"></script>
    <script src="<%=request.getContextPath()%>/resources/js/jquery.magnific-popup.min.js"></script>
    <script src="<%=request.getContextPath()%>/resources/js/plugins.js"></script>
    <!-- <script src="js/gijgo.min.js"></script> -->
    <script src="<%=request.getContextPath()%>/resources/js/slick.min.js"></script>



    <!--contact js-->
    <script src="<%=request.getContextPath()%>/resources/js/contact.js"></script>
    <script src="<%=request.getContextPath()%>/resources/js/jquery.ajaxchimp.min.js"></script>
    <script src="<%=request.getContextPath()%>/resources/js/jquery.form.js"></script>
    <script src="<%=request.getContextPath()%>/resources/js/jquery.validate.min.js"></script>
    <script src="<%=request.getContextPath()%>/resources/js/mail-script.js"></script>


    <script src="<%=request.getContextPath()%>/resources/js/main.js"></script>
    </body>
</html>