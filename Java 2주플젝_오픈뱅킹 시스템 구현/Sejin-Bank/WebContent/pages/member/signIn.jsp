<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="<%=request.getContextPath()%>/resources/css/member/login.css">
<script src="<%=request.getContextPath()%>/resources/js/jquery-3.6.0.min.js"></script>
<script src="https://developers.kakao.com/sdk/js/kakao.js"></script>
</head>

<body>
	<!-- header -->
   	<header>
		<jsp:include page="/pages/include/topMenu.jsp"></jsp:include>
	</header>
	<form action="<%=request.getContextPath()%>/member/loginPro.do" method="post">
	<div class="main-container">
		<div class="main-wrap">
			<h2 align="center">로그인</h2> 
		<section class="login-input-section-wrap">
			<div class="login-input-wrap">	
				<input placeholder="ID" type="text" name="user_id"></input>
			</div>
			<div class="login-input-wrap password-wrap">	
				<input placeholder="Password" type="password" name="user_password"></input>
			</div>
			<div class="login-button-wrap">
				<button type="submit">Sign in</button>
			</div>
			<div class="login-stay-sign-in">
				
			</div>
		</section>
		<section class="Easy-sgin-in-wrap">
			<h2>Easier sign in</h2>
			<ul class="sign-button-list">
				<li><a id="kakao-login-btn" ></a>
					<p id="token-result"></p></li>
			</ul>
		</section>
		
		</div>
	</div>
	</form>
	<footer>
		<jsp:include page="/pages/include/bottom.jsp"></jsp:include>	
	</footer>
	<script type="text/javascript">
	
	//카카오 로그인
	Kakao.init('cfc17fc44b85ad4e13ad79b712562a83');
	
	// SDK 초기화 여부를 판단
	console.log(Kakao.isInitialized());
	
	Kakao.Auth.createLoginButton({
	    container: '#kakao-login-btn',
	    success: function(authObj) {
	      Kakao.API.request({
	        url: '/v2/user/me',
	        success: function(res) {
	          //alert(JSON.stringify(res))   	 
	         // 사용하고픈 정보들을 변수에 담는다.
	          //var accessToken = kakao.Auth.getAccessToken(); //엑세스 토큰 할당
	          //kakao.Auth.setAccessToken(accessToken); //엑세스 토큰 사용하게 등록
	          var kakao_id = res.id;
	          //var nickname = res.properties['nickname'];
	          //var profile_img = res.properties['profile_image'];
	          //var connected_time = res.connected_at;
	          var email = res.kakao_account['email'];
	          //var phone_number = res.kakao_account['phone_number'];         
	          //var gender = res.kakao_account['gender']
	          //var kakao_access_token = authObj.access_token;	       		          		          	      
			  location.href = '<%=request.getContextPath()%>/member/loginPro.do?kakao_id=' + kakao_id + '&email=' + email
	          
	        },
	        fail: function(error) {
	          alert(
	            'login success, but failed to request user information: ' +
	              JSON.stringify(error)
	          )
	        },
	      })
	    },
	    fail: function(err) {
	      alert('failed to login: ' + JSON.stringify(err))
	    },
	  })
	
	</script>
</body>
</html>