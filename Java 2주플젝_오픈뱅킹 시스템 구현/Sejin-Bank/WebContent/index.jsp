<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!doctype html>
<html class="no-js" lang="zxx">

<head>
    <meta charset="utf-8">
    <meta http-equiv="x-ua-compatible" content="ie=edge">
    <title>Transportion</title>
    <meta name="description" content="">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <!-- <link rel="manifest" href="site.webmanifest"> -->
    <link rel="shortcut icon" type="image/x-icon" href="<%=request.getContextPath()%>/resources/img/favicon.png">
    <!-- Place favicon.ico in the root directory -->

    <!-- CSS here -->
    <link rel="stylesheet" href="<%=request.getContextPath()%>/resources/css/bootstrap.min.css">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/resources/css/owl.carousel.min.css">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/resources/css/magnific-popup.css">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/resources/css/font-awesome.min.css">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/resources/css/themify-icons.css">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/resources/css/nice-select.css">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/resources/css/flaticon.css">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/resources/css/gijgo.css">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/resources/css/animate.css">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/resources/css/slick.css">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/resources/css/slicknav.css">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/resources/css/myCss.css">
    <link rel="stylesheet" href="https://ajax.googleapis.com/ajax/libs/jqueryui/1.11.2/themes/smoothness/jquery-ui.css">
	
    <link rel="stylesheet" href="<%=request.getContextPath()%>/resources/css/style.css">
    <!-- <link rel="stylesheet" href="css/responsive.css"> -->
    
    <script src="<%=request.getContextPath()%>/resources/js/jquery-3.6.0.min.js"></script>
    <script src="https://developers.kakao.com/sdk/js/kakao.js"></script>
    <script type="text/javascript">
    	$(document).ready(function(){
    		if('${loginCheck}' == 'none'){
    			document.querySelector("#join_modal").classList.remove("hidden");
        	}
    		
    		if('${joinCheck}' == 'none'){
    			$('#joinResultType').text('회원가입에 실패했습니다.')
    			document.querySelector("#join_false_modal").classList.remove("hidden");
        	} else if('${joinCheck}' == 'success'){ 
        		$('#joinResultType').text('회원가입 성공')
        		document.querySelector("#join_false_modal").classList.remove("hidden");
        	}
    		
    		if('${accountCreateResult}' == 'success') { //계좌 생성 확인
    			$('#accountCreateResultMsg').text('계좌가 생성되었습니다.')
    			document.querySelector("#account_create_modal").classList.remove("hidden");
    			Kakao.API.request({
      			  url: '/v2/api/talk/memo/send',
	   				  data: {
	   				    template_id: 57559
	   				  },
	   				  success: function(response) {
	   				    console.log(response);
	   				  },
	   				  fail: function(error) {
	   				    console.log(error);
	   				  },
   				  });
    		
    		}else if('${accountCreateResult}' === 'none') {
    			$('#accountCreateResultMsg').text('계좌 생성 실패')
    			document.querySelector("#account_create_modal").classList.remove("hidden");
    		}
    		
    		if('${transferResult}' == 'success') { //카카오톡 보내기 + 모달
    			Kakao.API.request({
    			  url: '/v2/api/talk/memo/send',
   				  data: {
   				    template_id: 57456
   				  },
   				  success: function(response) {
   				    console.log(response);
   				  },
   				  fail: function(error) {
   				    console.log(error);
   				  },
   				});
   		     
    		} //if문 끝
    	})
    </script>
    
</head>

<body>

	<header>
		<jsp:include page="/pages/include/topMenu.jsp"></jsp:include>
	</header>
	
    <!-- slider_area_start -->
    <div class="slider_area">
        <div class="single_slider d-flex align-items-center index_banner">
            <div class="container">
                <div class="row align-items-center justify-content-center">
                    <div class="col-xl-8 ">
                        <div class="slider_text text-center justify-content-center index_banner_text">
                            <p>78억만이 함께하는 따뜻한 금융</p>
                            <h3>개발은 세진개발 <br>
                                                                       은행은 세진은행</h3>
                                <c:if test="${empty memberVO}">
                                <a id="kakao-login-btn" >
								  
								</a>
								<p id="token-result"></p>
								</c:if>
								<c:if test="${! empty memberVO}">
									<h4>[ ${memberVO.name} ] 님 반갑습니다.</h4>
								</c:if>
								
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <!-- slider_area_end -->

    <div class="transportaion_area">
        <div class="container">
            <div class="row">
                <div class="col-xl-4 col-lg-4 col-md-6">
                    <div class="single_transport">
                        <div class="icon">
                            <img src="<%=request.getContextPath()%>/resources/img/svg_icon/airplane.png" alt="">
                        </div>
                        <h3>Transportation</h3>
                        <p>Esteem spirit temper too say adieus who direct esteem. It look estee luckily or picture
                            placing drawing.</p>
                    </div>
                </div>
                <div class="col-xl-4 col-lg-4 col-md-6">
                    <div class="single_transport">
                        <div class="icon">
                            <img src="<%=request.getContextPath()%>/resources/img/svg_icon/live.png" alt="">
                        </div>
                        <h3>Live Monitoring</h3>
                        <p>Esteem spirit temper too say adieus who direct esteem. It look estee luckily or picture
                            placing drawing.</p>
                    </div>
                </div>
                <div class="col-xl-4 col-lg-4 col-md-6">
                    <div class="single_transport">
                        <div class="icon">
                            <img src="<%=request.getContextPath()%>/resources/img/svg_icon/world.png" alt="">
                        </div>
                        <h3>Worldwide Service</h3>
                        <p>Esteem spirit temper too say adieus who direct esteem. It look estee luckily or picture
                            placing drawing.</p>
                    </div>
                </div>
            </div>
        </div>
    </div>


    <!-- chose_area  -->
    <div class="chose_area ">
        <div class="container">
            <div class="features_main_wrap">
                <div class="row  align-items-center">
                    <div class="col-xl-5 col-lg-5 col-md-6">
                        <div class="about_image">
                            <img src="<%=request.getContextPath()%>/resources/img/about/about.png" alt="">
                        </div>
                    </div>
                    <div class="col-xl-6 col-lg-6 col-md-6">
                        <div class="features_info">
                            <h3>Why Choose Us?</h3>
                            <p>Lorem ipsum dolor sit amet, consectetur adipiscing elit sed do eiusmod tempor incididunt.
                            </p>
                            <ul>
                                <li> Apartments frequently or motionless. </li>
                                <li> Duis aute irure dolor in reprehenderit in voluptate. </li>
                                <li> Voluptatem quia voluptas sit aspernatur.</li>
                            </ul>

                            <div class="about_btn">
                                <a class="boxed-btn3-line" href="about.html">About Us</a>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <!--/ chose_area  -->

    <!-- counter_area  -->
    <div class="counter_area">
        <div class="container">
            <div class="offcan_bg">
                <div class="row">
                    <div class="col-xl-3 col-md-3">
                        <div class="single_counter text-center">
                            <h3> <span class="counter">42</span> <span>+</span> </h3>
                            <p>Countries Covered</p>
                        </div>
                    </div>
                    <div class="col-xl-3 col-md-3">
                        <div class="single_counter text-center">
                            <h3> <span class="counter">97</span> <span>+</span> </h3>
                            <p>Business Success</p>
                        </div>
                    </div>
                    <div class="col-xl-3 col-md-3">
                        <div class="single_counter text-center">
                            <h3> <span class="counter">2342</span></h3>
                            <p>Happy Client</p>
                        </div>
                    </div>
                    <div class="col-xl-3 col-md-3">
                        <div class="single_counter text-center">
                            <h3> <span class="counter">3245</span></h3>
                            <p>Business Done</p>
                        </div>
                    </div>
                </div>
            </div>

        </div>
    </div>
    <!-- /counter_area  -->
	
	<footer>
		<jsp:include page="/pages/include/bottom.jsp"></jsp:include>	
	</footer>

<!-- Button trigger modal -->
  
  <!-- Modal -->
  <div class="modal fade custom_search_pop" id="exampleModalCenter" tabindex="-1" role="dialog" aria-labelledby="exampleModalCenterTitle" aria-hidden="true">
    <div class="modal-dialog modal-dialog-centered" role="document">
      <div class="modal-content">
        <div class="serch_form">
            <input type="text" placeholder="search" >
            <button type="submit">search</button>
        </div>
      </div>
    </div>
  </div>
  
  <!-- 회원가입 모달 -->
	<div class="join_modal hidden" id="join_modal">
		<div class="bg"></div>
		<div class="modalBox">
			<p>세진은행의 회원이 아니시군요 ! <br>
			   회원가입 하시겠습니까?</p>
		<button type="button" onclick="location.href='<%=request.getContextPath()%>/member/join.do'">회원가입</button>
		<button class="join_modal_close">취소</button>
		</div>
	</div>
	
	<!-- 회원가입 실패 -->
	<div class="join_modal hidden" id="join_false_modal">
		<div class="bg" id="bg_join_false"></div>
		<div class="modalBox">
			<p id="joinResultType"></p>
		<button class="join_false_modal_close">확인</button>
		</div>
	</div>
	
	<!-- 계좌 생성 여부 -->
	<div class="join_modal hidden" id="account_create_modal">
		<div class="bg" id="bg_account_create"></div>
		<div class="modalBox">
			<p id="accountCreateResultMsg"></p>
		<button class="account_create_modal_close">확인</button>
		</div>
	</div>

    <!-- JS here -->
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
				  location.href = '<%=request.getContextPath()%>/member/loginPro.do?kakao_id=' + kakao_id
		          
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
		  
		//모달 close 
	  const close = () => { //회원가입 할거니 ?
  		   document.querySelector("#join_modal").classList.add("hidden");
 		}
	  
	  const closeJoinFalse = () => { //회원가입 실패
 		   document.querySelector("#join_false_modal").classList.add("hidden");
		}
	  
	  const closeAccountCreate = () => { //계좌 생성 여부
		   document.querySelector("#account_create_modal").classList.add("hidden");
		}
	  
	   document.querySelector(".join_modal_close").addEventListener("click", close);
	   document.querySelector(".bg").addEventListener("click", close);
	   
	   document.querySelector(".join_false_modal_close").addEventListener("click", closeJoinFalse);
	   document.querySelector("#bg_join_false").addEventListener("click", closeJoinFalse);
	   
	   document.querySelector(".account_create_modal_close").addEventListener("click", closeAccountCreate);
	   document.querySelector("#bg_account_create").addEventListener("click", closeAccountCreate);
	   
    </script>
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