<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<title>Q&A</title>
	
	<link rel="stylesheet" href="<%=request.getContextPath()%>/resources/css/board/bootstrap.css">
	<link rel="stylesheet" href="<%=request.getContextPath()%>/resources/css/board/myCss.css">
	<script type="text/javascript" src="<%=request.getContextPath()%>/resources/js/board/bootstrap.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/resources/js/qna.js"></script>
	
	<script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
	<script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
	<link rel="stylesheet" href="<%=request.getContextPath()%>/resources/css/signUpCss.css">	
	<script src="<%=request.getContextPath()%>/resources/js/jquery-3.6.0.min.js"></script>
	<script type="text/javascript">
		function fileCheck() {
			//확장자 체크
			var result = doWrite();
			
			return result
		}
	</script>
</head>
<body>
	<!-- header -->
	<header>
		<jsp:include page="/pages/include/topMenu.jsp" ></jsp:include>
	</header>
	<div class="qna-write-container">
		<h2 class="text-center">Q&A 작성하기 </h2>
		<form enctype="multipart/form-data" method="post" action="<%=request.getContextPath()%>/qna/writePro.do" name="writeForm" onsubmit="return fileCheck()">
			<input type="hidden" name="type" value="${type}">
			<input type="hidden" name="re_step" value="${re_step}">
			<input type="hidden" name="re_level" value="${re_level}">
			<input type="hidden" name="reference" value="${reference}">
			
			<table class="table ">
				<tbody>
					<tr>
						<th class="text-center">제목</th>
						<td><input class="form-control" type="text" name="title" required></td>
						
					</tr>
					<tr>
						<th class="text-center" >작성자</th>
						<td><c:out value="${memberVO.name}" /></td>
					</tr>
					<tr>
						<th class="text-center" >내용</th>
						<td><textarea class="form-control" name="content" rows="20" required></textarea></td>
					</tr>
					<tr>
						<th class="text-center" >파일첨부 <br>(최대 3개까지 가능)<br><br></th>
						
						<td>
							<input type="file" name="attachfile1">
							<span class="error_next_box"></span>
							<br>
							<input type="file" name="attachfile2">
							<span class="error_next_box"></span>
							<br>
							<input type="file" name="attachfile3">
							<span class="error_next_box"></span>
						</td>
					</tr>
				</tbody>
			</table>
		<hr>
		<div class=" qna-write-btn">
			
			<input type="submit" class="btn" value="질문 등록하기"> 
			
			<input type="button" class="btn" value="목록">
		</div>
		</form>
	</div>
	<footer>
		<jsp:include page="/pages/include/bottom.jsp"></jsp:include>	
	</footer>
</body>
</html>