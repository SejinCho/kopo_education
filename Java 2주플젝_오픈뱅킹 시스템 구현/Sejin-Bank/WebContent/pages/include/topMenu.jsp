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
    <link rel="stylesheet" href="https://ajax.googleapis.com/ajax/libs/jqueryui/1.11.2/themes/smoothness/jquery-ui.css">

    <link rel="stylesheet" href="<%=request.getContextPath()%>/resources/css/style.css">
    <!-- <link rel="stylesheet" href="css/responsive.css"> -->
</head>

<body>
    <!--[if lte IE 9]>
            <p class="browserupgrade">You are using an <strong>outdated</strong> browser. Please <a href="https://browsehappy.com/">upgrade your browser</a> to improve your experience and security.</p>
        <![endif]-->

    <!-- header-start -->
    <header>
        <div class="header-area ">
            <div class="header-top_area d-none d-lg-block">
                <div class="container">
                    <div class="row align-items-center">
                        <div class="col-xl-4 col-lg-4">
                            <div class="logo">
                                <a href="<%=request.getContextPath()%>/index.do">
                                    <img src="<%=request.getContextPath()%>/resources/img/7.png" alt="">
                                </a>
                            </div>
                        </div>
                        <div class="col-xl-8 col-md-8">
                            <div class="header_right d-flex align-items-center">
                                <div class="short_contact_list">
                                    <ul>
                                    	<c:if test="${empty memberVO}">
                                        	<li><a href="<%=request.getContextPath()%>/member/login.do">Sign in</a></li>
                                        	<li><a href="<%=request.getContextPath()%>/member/join.do">Sign up</a></li>
                                        </c:if>
                                        <c:if test="${! empty memberVO}">
                                        	<li><a href="<%=request.getContextPath()%>/member/logout.do">Sign Out</a></li>
                                        </c:if>
                                    </ul>
                                </div>

                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div id="sticky-header" class="main-header-area">
                <div class="container">
                    <div class="header_bottom_border">
                        <div class="row align-items-center">
                            <div class="col-12 d-block d-lg-none">
                                <div class="logo">
                                    <a href="index.html">
                                        <img src="" alt="">
                                    </a>
                                </div>
                            </div>
                            <div class="col-xl-9 col-lg-9">
                                <div class="main-menu  d-none d-lg-block">
                                    <nav>
                                        <ul id="navigation">
                                            <li><a  href="<%=request.getContextPath() %>/index.do">home</a></li>
                                            
                                            <li><a href="#">조회 <i class="ti-angle-down"></i></a>
                                                <ul class="submenu">
                                                    <li><a href="<%=request.getContextPath() %>/account/lookUp.do">계좌조회</a></li>
                                                    <li><a href="<%=request.getContextPath()%>/account/detail.do">거래내역조회</a></li>
                                                    <li><a href="<%=request.getContextPath()%>/openBanking/accountInfoList.do">오픈뱅킹</a></li>
                                                </ul>
                                            </li>
                                            <li><a href="#">이체<i class="ti-angle-down"></i></a>
                                                <ul class="submenu">
                                                    <li><a href="<%=request.getContextPath() %>/account/transfer.do">계좌이체</a></li>
                                                    <li><a href="<%=request.getContextPath() %>/openBanking/transfer.do">다른은행 계좌이체(오픈뱅킹)</a></li>
                                                    <li><a href="<%=request.getContextPath() %>/account/reserveTran.do">예약이체</a></li>
                                                    <li><a href="single-blog.html">자동이체</a></li>
                                                </ul>
                                            </li>
                                            <li><a href="<%=request.getContextPath() %>/account/create.do">비대면 계좌 개설</a></li>
                                            <li><a  href="<%=request.getContextPath()%>/qna/qnaList.do">고객센터</a></li>
                                            <li><a href="#">마이페이지<i class="ti-angle-down"></i></a>
                                            	<ul class="submenu">
                                                    <li><a href="blog.html"></a></li>
                                                    <li><a href="<%=request.getContextPath()%>/my/reserveTranInfo.do">예약이체 내역</a></li>
                                                    <li><a href="<%=request.getContextPath()%>/my/nickName.do">별칭 수정</a></li>
                                                    <li><a href="single-blog.html">내 정보</a></li>
                                                </ul>
                                            </li>
                                        </ul>
                                    </nav>
                                </div>
                            </div>
                            <div class="col-xl-3 col-lg-3 d-none d-lg-block">
                                <div class="Appointment justify-content-end">
                                    <div class="search_btn">
                                        <a data-toggle="modal" data-target="#exampleModalCenter" href="#">
                                            <i class="ti-search"></i>
                                        </a>
                                    </div>
                                </div>
                            </div>
                            <div class="col-12">
                                <div class="mobile_menu d-block d-lg-none"></div>
                            </div>
                        </div>
                    </div>

                </div>
            </div>
        </div>
    </header>
    <!-- header-end -->