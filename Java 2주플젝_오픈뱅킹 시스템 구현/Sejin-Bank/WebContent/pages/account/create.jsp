<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lnag="ko">
    <head>
	    <meta charset="utf-8">
	    <meta http-equiv="x-ua-compatible" content="ie=edge">
	    <title>비대면 계좌 개설</title>
	    <meta name="description" content="">
	    <meta name="viewport" content="width=device-width, initial-scale=1">
	
	    <!-- <link rel="manifest" href="site.webmanifest"> -->
	    <link rel="shortcut icon" type="image/x-icon" href="<%=request.getContextPath()%>/resources/img/favicon.png">
	    <!-- Place favicon.ico in the root directory -->
	
	    <!-- CSS here -->
	    
	    <link rel="stylesheet" href="https://ajax.googleapis.com/ajax/libs/jqueryui/1.11.2/themes/smoothness/jquery-ui.css">
		
	    
	    <link rel="stylesheet" href="<%=request.getContextPath()%>/resources/css/signUpCss.css">
	    <link rel="stylesheet" href="<%=request.getContextPath()%>/resources/css/modal/modal.css">
	    <!-- <link rel="stylesheet" href="css/responsive.css"> -->
	    
	    <script src="<%=request.getContextPath()%>/resources/js/jquery-3.6.0.min.js"></script>
	    <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
		<script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
		<script src="<%=request.getContextPath()%>/resources/js/jquery-3.6.0.min.js"></script>
		
		
		<script type="text/javascript">
			var emailAuthNum = '';
			var phoneAuthNum = '';
			var easy_password = '';
			$(document).ready(function(){
				let error = document.querySelectorAll('.error_next_box');
				
				
				//핸드폰 인증 클릭
				$('#smsAuth').click(function(){
					var result = checkPhoneNum($('#mobile').val())
					if(result) {
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
								error[1].innerHTML = "문자가 전송되었습니다 인증번호를 입력하세요.";
						        error[1].style.display = "block";
							},
							error: function (request, status, error){
								var msg = "ERROR : " + request.status + "<br>"
								msg += + "내용 : " + request.responseText + "<br>" + error;
								console.log(msg);
							}

						})
						
					}
				})
				
				//이메일 인증
				$('#emailAuth').click(function(){
					let result = checkEmail()
					if(result) {
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
								error[3].innerHTML = "이메일이 전송되었습니다 인증번호를 입력하세요.";
						        error[3].style.display = "block";
							},
							error: function (request, status, error){
								var msg = "ERROR : " + request.status + "<br>"
								msg += + "내용 : " + request.responseText + "<br>" + error;
								console.log(msg);
							}

						})
						
						
					}
				})
				
				
				//간편 비밀번호 가져오기
				$.ajax({
					type: "POST",
					url : "<%=request.getContextPath()%>/account/easyPw.do",
					method : 'post',
					data : {
						"member_id" : $('#member_id').val()
					},
					dataType: "text",
					success : function(data) {
				        console.log($.trim(data));
				        easy_password = $.trim(data)
					},
					error: function (request, status, error){
						var msg = "ERROR : " + request.status + "<br>"
						msg += + "내용 : " + request.responseText + "<br>" + error;
						console.log(msg);
					}

				})
				
				
				
			})
			
			//submit 버튼 클릭했을 때
			function createFormCheck() {
				
				//패스워드 체크 (완료)
				let result = checkPw(easy_password)
				
				if(result === false){
					return false
				}
				//이메일 인증번호 체크 emailAuthNum (완료)
				result = checkEmailNo(emailAuthNum)
				if(result === false){
					return false
				}
				
				//핸드폰 인증번호 체크 phoneAuthNum (**************나중에**************)
				result = checkPhoneNo(phoneAuthNum) 
				if(result === false){
					return false
				}
				
				
				var radio1 = $('input:radio[name="purpose1"]:checked').val();
				var radio2 = $('input:radio[name="purpose2"]:checked').val();
				//나머지 radio box 체크  (완료)
				result = radioCheck(radio1, radio2)
				if(result === false){
					return false
				}
				//계좌 비번(4자리)
				result = checkAccountPw()
				if(result === false){
					return false
				}
				result = comparePw()
				if(result === false){
					return false
				}
				
				return true;
			}
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
            	<form action="<%=request.getContextPath()%>/account/createPro.do" onsubmit="return createFormCheck()" method="post"> 
            <div id="content">
            	<input type="hidden" name="id" value="${memberVO.id}" id="member_id">
            		<h2 align="center">비대면 계좌 개설</h2>
					
	                
	                <!-- 간편 비밀번호 -->
	                <div>
	                    <h3 class="join_title"><label for="pswd">간편 비밀번호(6자리)</label></h3>
	                    <span class="box int_pass">
	                        <input type="password" id="pswd" name="easy_password" class="int" maxlength="20" required>
	                        <span id="alertTxt">사용불가</span>
	                        <img src="<%=request.getContextPath()%>/resources/img/signUp/m_icon_pass.png" id="pswd_img" class="pswdImg">
	                    </span>
	                    <span class="error_next_box"></span>
	                </div>
	
	                
	                
	                
	                <!-- 모바일 -->
	                <div>
						<h3 class="join_title"><label for="addr">휴대전화(- 제외)</label></h3>
						<div id="bir_wrap">
							<div >
								<span class="box">
									<input type="text" class="int" id="mobile"  placeholder="전화번호 입력" required>
								</span>
								<span class="error_next_box"></span>
							</div>
							
							<div id="bir_mm">	
								<span >
									<input type="button"  value="전화번호 인증" id="smsAuth"><br>
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
								<span class="error_next_box"></span>
							</div>
							
							<div id="bir_mm">	
							</div>
						</div>
	                </div>
					
					<!-- 이메일 인증 -->
	                <div>
						<h3 class="join_title"><label for="addr">이메일</label></h3>
						<div id="bir_wrap">
							<div >
								<span class="box">
									<input type="text" class="int" id="email" placeholder="이메일 입력" required>
								</span>
								<span class="error_next_box"></span>
							</div>
							
							<div id="bir_mm">	
								<span >
									<input type="button"  value="이메일 인증" id="emailAuth"><br>
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
								<span class="error_next_box"></span>
							</div>
							
							<div id="bir_mm">	
							</div>
						</div>
	                </div>
					
					
					<!-- 개인정보 수집 이용 동의 -->
					<div>
	                	<h3 class="join_title"><label for="addr">개인(신용)정보수집 / 이용(조회)동의</label></h3>
	                	
	                	<!-- 첫 번쨰 -->
	                	<span>
	                		<input type="checkbox" required>
	                		<a class="btn">필수 개인정보 수집 이용 동의서(비여신 금융거래 설정 등)</a>
	                	</span>
	                	
	                	
	                	<!-- 두 번째 -->
	                	<span>
	                		<input type="checkbox" required>
	                		<a class="btn">개인(신용)정보 수집, 이용 동의서(상품서비스 안내 등)</a>
	                	</span>
	                	
					    <br>
					    
					    <!-- 세 번째 -->
	                	<span>
	                		<input type="checkbox" required>
	                		<a class="btn">개인(신용)정보 수집,이용(조회)<br> 제공동의서 관련 고객 권리 안내문</a>
	                	</span>
	                	
	                	
	                	
	                </div>
	                
	                <div>
	                	<h3 class="join_title"><label for="addr">타인으로부터 통장대여 요청을 받은 적이 있습니까?</label></h3>
	                	<span>
	                		<input type="radio" name="purpose1" value="y" checked> <span>예</span>&nbsp;&nbsp; <input type="radio" name="purpose1" value="n"> <span>아니요</span>
	                	</span>
	                	<span class="error_next_box"></span>
	                </div>
	                
	                <div>
	                	<h3 class="join_title"><label for="addr">타인으로부터 신용등급 상향, 대출 등의 목적으로
	                									통장개설 등 금융 거래를 요청받은 사실이 있습니까?</label></h3>
	                	<span>
	                		<input type="radio" name="purpose2"  value="y" checked> <span>예</span>&nbsp;&nbsp; <input type="radio" name="purpose2" value="n"> <span>아니요</span>
	                	</span>
	                	<span class="error_next_box"></span>
	                </div>
	                
	                <!-- PW1 -->
	                <div>
	                    <h3 class="join_title"><label for="pswd1">계좌 비밀번호(4자리)</label></h3>
	                    <span class="box int_pass">
	                        <input type="password" id="pswd1" name="account_password" class="int" maxlength="20"   required>
	                        <span id="alertTxt">사용불가</span>
	                        <img src="<%=request.getContextPath()%>/resources/img/signUp/m_icon_pass.png" id="pswd1_img1" class="pswdImg">
	                    </span>
	                    <span class="error_next_box"></span>
	                </div>
	
	                <!-- PW2 -->
	                <div>
	                    <h3 class="join_title"><label for="pswd2">계좌 비밀번호 재확인 (4자리)</label></h3>
	                    <span class="box int_pass_check">
	                        <input type="password" id="pswd2" class="int" maxlength="20" required>
	                        <img src="<%=request.getContextPath()%>/resources/img/signUp/m_icon_check_disable.png" id="pswd2_img1" class="pswdImg">
	                    </span>
	                    <span class="error_next_box"></span>
	                </div>
	                
	                
	                <!-- 별칭 -->
	                <div>
						<h3 class="join_title"><label for="addr">계좌 별칭</label></h3>
						<div id="bir_wrap">
							<div >
								<span class="box">
									<input type="text" class="int" name="nickname"  placeholder="계좌 별칭 입력" required>
								</span>
								<span class="error_next_box"></span>
							</div>
						</div>
	                </div>
	                
	                
	                <!-- JOIN BTN-->
	                <div class="btn_area">
	                	<input type="submit" id="btnJoin" >
	                </div>
	                
	                


            </div> 
                </form>
            <!-- content-->
            
			<!-- modal -->
			
			<div class="modal">
 
		      <!-- 첫 번째 Modal의 내용 -->
		      <div class="modal-content">
		        <span class="close">&times;</span>                         
		        <p><b>개인(신용)정보 수집·이용 동의서(상품서비스 안내·오픈뱅킹용)</b> <br>
					귀 행이 상품서비스 안내 등을 위하여 본인의 개인(신용)정보를 수집·이용하는 경우에는 「신용정보의 이용 
					및 보호에 관한 법률」 제15조 제2항, 제32조 제1항, 제33조 및 제34조, 「개인정보 보호법」 제15조 제1항
					제1호, 제22조 제3항에 따라 본인의 동의가 필요합니다.  <br>
					
					아래에서 (금융)거래라 함은 은행업무(여신, 수신, 외국환), 겸영업무(신탁, 펀드, 방카슈랑스, 신용카드 등), 부수업무(보증, 팩토링, 대여금고, 보호예수 등)와 관련된 거래를 의미합니다. 
					<br>
					1. 수집 이용 목적 <br>
					타행 (금융)거래정보를 활용한 상품, 서비스 홍보 및 판매 권유<br>
					2. 수집‧이용할 항목<br>
					￭ 개인정보<br>
					‣ 성명, 생년월일, 주소, 연락처(전자우편주소, 집/직장/휴대폰 전화번호 등), 국적, 연소독, 직업, 직장정보, 결혼여부, 주거 및 가족사항, 거주기간, 관심 금융/서비
					스 정보, 금융/비금융 자산현황<br>
					￭ 오픈뱅킹 및 통합조회 서비스 이용을 위하여 등록한 타행 (금융)거래정보<br>
					‣ 상품종류, 거래조건(이자율, 만기 등), 거래일시, 금액 등 거래 설정·내역정보 및 오픈
					뱅킹 서비스의 설정·유지·이행·관리를 위한 상담 등을 통해 생성되는 정보
					※ 본 동의 이전에 발생한 개인(신용)정보도 포함됩니다.<br>
					3. 보유 이용 기간<br>
					위 개인(신용)정보는 (금융)거래종료일* 또는 동의 철회 시까지 보유· 이용할 수 있습니다. 이후에는 관련된 사고조사, 분쟁해결, 민원처리, 법령상 의
					무이행을 위하여 필요한 범위 내에서만 보유․이용됩니다. * (금융)거래 종료일이란 당행과 거래중인 모든 계약(여·수신, 내·외국환, 카드 및 제3자 담보
					제공 등)해지 및 서비스(대여금고, 보호예수, 외국환거래지정, 인터넷뱅킹 포함 전자금융
					거래 등)가 종료된 날을 의미합니다.
				</p>
		      </div>
		    </div>
			
			
		    
		    
		    <div class="modal">
 
		      <!-- 첫 번째 Modal의 내용 -->
		      <div class="modal-content">
		        <span class="close">&times;</span>                         
		        <p><b>개인 정보 수집·이용에 관한 동의서</b> <br>
					  주식회사 가나다는 개인정보보호법 등 관련 법상의 개인정보호 규정을 준수하여 근로자의 개인정보 보호에 최선을 다하고 있습니다. 회사는 개인정보보호법 제17조에 근거하여 다음과 같이 회사 업무의 수행 및 소속직원들의 채용, 해지, 발령 등에 따른 인사기록의 보관 등과 같이 반드시 필요한 범위내에서 직원의 개인정보 수집, 이용, 보관하고 이를 제3자에게 제공하는데 동의를 받고자합니다.
					<br>
					1. 개인정보 수집 목적 <br>
					   채용, 배치, 평가, 취업 알선, 기타 법령으로 정한 용도로의 활용 및 제3자에 대한 제공 <br>
					
					2. 수집항목 <br>
					   가. 필수항목 : 이름, 주민등록번호 앞 6자리, 주소, 전화번호(연락을 목적으로 사용 가능), 보유자격 및 교육이수현황
					   나. 선택항목 : 가족관계, 이력 및 경력사항 <br>
					
					3. 개인정보 보유 및 이용기간  <br>
					   가. 채용 전 : 제출일로부터 3개월간 <br>
					   나. 채용 시 : 채용일로부터 퇴사 후 5년간 <br>
					
					4. 제3자에 대한 제공 동의(□ 동의 함 / □ 동의하지 않음) <br>
					   가. 제공 받은 자 : 배치예정 또는 배치하고자하는 사업장의 사업주 또는 담당자 <br>
					   나. 제공 받은 자의 목적 : 정보제공자의 배치적정 여부 판단 및 인적사항 확보 <br>
					   다. 제공하는 항목 : 정보 주체가 제출한 이력서, 자격증 사본에 기재된 사항 일체 <br>
					   라. 제공 받은 자의 보유/이용기간 : 정보 주체가 해당 사업장에 배치되어 종사하는 기간 동안  <br>
					   마. 동의 거부권 : 귀 개인정보의 수집·이용에 대한 동의를 거부할 수 있으며, 동의하지 않는 경우 사업장의 배치 거부로 채용 또는 배치가 제한 또는 거절 될 수 있습니다. <br>
					
					5. 개인정보의 수집·이용에 대한 동의 거부  <br>
					   개인정보의 수집·이용을 거부할 수 있습니다. 다만, 개인정보의 수집·이용 등에 동의하지 않을 경우 신원확인 및 선임, 직무능력 등이 입증되지 않아 채용되지 않거나 채용 이후에도 채용이 취소될 수 있습니다.</p>
		      </div>
		    </div>
		    
		    <div class="modal">
 
		      <!-- 첫 번째 Modal의 내용 -->
		      <div class="modal-content">
		        <span class="close">&times;</span>                         
		        <p><b>개인(신용)정보 수집,이용(조회)<br> 제공동의서 관련 고객 권리 안내문</b> <br>
					1. 금융서비스 이용 범위 <br>
					고객의 개인(신용)정보는 고객이 동의한 목적을 위하여만 수집ㆍ이용 및 제공됩니다. 필수적 정보에 대한 수집ㆍ이용 및 제공은 계약의 체결 및 이행을 위하여 필수적이므로, 위 사항에 동의하셔야만 [(금융)거래] 관계의 설정 및 유지가 가능합니다. 반면 선택적 정보에 대한 수집ㆍ이용 및 제공에 대하여는 거부하실 수 있으며, 다만 동의하지 않으실 경우 [(금융)거래] 조건 등에 불이익을 받으실 수 있습니다.
					<br>
					2. 고객 개인(신용)정보의 제공 및 마케팅 활용 중단 신청<br>
					가. 고객은 가입 신청 시 동의한 본인 개인(신용)정보의 제3자 제공 또는 당사의 [금융상품(서비스)] 소개 등 영업목적의 사용에 대하여 전체 또는 사안별로 당사에 제공 활용을 중단시킬 수 있습니다(개인정보 보호법 제37조, 신용정보의 이용 및 보호에 관한 법률 제37조).
					다만, ① 법률에 특별한 규정*이 있거나 법령상 의무를 준수하기 위하여 불가피한 경우, ② 다른 사람의 생명ㆍ신체를 해할 우려가 있거나 다른 사람의 재산과 그 밖의 이익을 부당하게 침해할 우려가 있는 경우, ③ 개인(신용)정보를 처리하지 아니하면 정보주체와 약정한 서비스를 제공하지 못하는 등 계약의 이행이 곤란한 경우로서 정보주체가 그 계약의 해지 의사를 명확하게 밝히지 아니한 경우에는 제공 활용중단 신청이 거절될 수 있습니다.
					* 신용조회회사 또는 신용정보집중기관에 제공하여 개인의 신용도를 평가하기 위한 목적으로 행한 신용정보의 제공 동의는 철회할 수 없습니다(신용정보의 이용 및 보호에 관한 법률 제37조제1항 단서).
					*[(금융)거래]관계를 설정하면서 동의를 한 경우 계약 체결일로부터 3개월 내에는 신용정보의 제공 동의를 철회할 수 없습니다(신용정보의 이용 및 보호에 관한 법률 시행령 제32조제1항 단서).
					<br>
					3. 고객 개인(신용)정보의 자기정보결정권<br>
					가. 고객은 개인정보 보호법, 신용정보의 이용 및 보호에 관한 법률 및 신용정보업감독규정 등에 따라 아래의 권리가 부여되어 있습니다. 동 권리의 세부내용에 대해서는 당사 홈페이지(www.shinhansavings.com) 또는 금융감독원 홈페이지(www.fss.or.kr)에 게시되어 있습니다. 동 권리를 행사하고자 하는 고객은 연락중지청구권의 경우 두낫콜 홈페이지(www.donotcall.or.kr)에서, 그 밖의 권리는 당사 각 영업점 앞으로 신청하여 주시기 바랍니다.
					개인신용정보 이용 및 제공 사실 조회 요청권(신용정보의 이용 및 보호에 관한 법률 제35조) : 금융회사가 내부경영관리의 목적으로 이용하거나 반복적인 업무위탁을 위해 제공하는 경우 등을 제외하고 개인신용정보를 이용하거나 제공중인 현황을 확인할 수 있는 권리
					연락중지청구권(신용정보의 이용 및 보호에 관한 법률 제37조 제2항) : 원치 않는 마케팅 목적의 연락(휴대폰 전화 또는 문자메세지)을 거부할 수 있는 권리
					개인(신용)정보 열람 및 오류 개인(신용)정보의 정정 삭제 요구권(개인정보보호법 제35조 제36조, 신용정보의 이용 및 보호에 관한 법률 제38조) : 당사에 본인의 개인(신용)정보에 대한 열람을 요구할 수 있는 권리 및 자신의 개인(신용)정보를 열람한 후 사실과 다르거나 확인할 수 없는 개인(신용)정보에 대하여 정정 또는 삭제를 요구할 수 있는 권리
					개인신용정보 이용 제공 사실 통보 요구권(신용정보의 이용 및 보호에 관한 법률 제35조) : 신용정보회사 등이 본인에 관한 신용정보를 이용 제공한 경우 매 1년마다 이용 제공현황을 통보해줄 것을 요구할 수 있는 권리(단, 신용정보 이용 제공사실 통보에 대한 수수료가 발생할 수 있습니다.)
					개인신용정보 삭제요구권(신용정보의 이용 및 보호에 관한 법률 제38조의3) : 당사와의 금융거래 종료 후 법령에서 정한 기간이 경과 시 당사가 보유한 본인 정보의 파기를 요구할 수 있는 권리
				</p>
		      </div>
		    </div>
		    
		    <!-- modal -->
        </div> 
        <!-- wrapper -->
		

		<footer>
			<jsp:include page="/pages/include/bottom.jsp"></jsp:include>	
		</footer>
		<script src="<%=request.getContextPath()%>/resources/js/account/account.js"></script>
		<script type="text/javascript">
			// Modal을 가져옵니다.
			var modals = document.getElementsByClassName("modal");
			// Modal을 띄우는 클래스 이름을 가져옵니다.
			var btns = document.getElementsByClassName("btn");
			// Modal을 닫는 close 클래스를 가져옵니다.
			var spanes = document.getElementsByClassName("close");
			var funcs = [];
			 
			// Modal을 띄우고 닫는 클릭 이벤트를 정의한 함수
			function Modal(num) {
			  return function() {
			    // 해당 클래스의 내용을 클릭하면 Modal을 띄웁니다.
			    btns[num].onclick =  function() {
			        modals[num].style.display = "block";
			        console.log(num);
			    };
			 
			    // <span> 태그(X 버튼)를 클릭하면 Modal이 닫습니다.
			    spanes[num].onclick = function() {
			        modals[num].style.display = "none";
			    };
			  };
			}
			 
			// 원하는 Modal 수만큼 Modal 함수를 호출해서 funcs 함수에 정의합니다.
			for(var i = 0; i < btns.length; i++) {
			  funcs[i] = Modal(i);
			}
			 
			// 원하는 Modal 수만큼 funcs 함수를 호출합니다.
			for(var j = 0; j < btns.length; j++) {
			  funcs[j]();
			}
			 
			// Modal 영역 밖을 클릭하면 Modal을 닫습니다.
			window.onclick = function(event) {
			  if (event.target.className == "modal") {
			      event.target.style.display = "none";
			  }
			};
			
			
			
		</script>
    </body>
</html>