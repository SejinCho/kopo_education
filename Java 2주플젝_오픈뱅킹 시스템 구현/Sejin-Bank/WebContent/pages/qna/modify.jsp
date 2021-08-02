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
	
</head>
<body>
	<!-- header -->
	<header>
		<jsp:include page="/pages/include/topMenu.jsp" ></jsp:include>
	</header>
	<div class="qna-write-container">
		<h2 class="text-center">Q&A 작성하기 </h2>
		<form enctype="multipart/form-data" method="post"  name="writeForm" >
			<input name="id" value="${id}">
			
			<table class="table ">
				<tbody>
					<tr>
						<th class="text-center">제목</th>
						<td><input class="form-control" type="text" name="title" value="${board.title }"></td>
						
					</tr>
					<tr>
						<th class="text-center" >작성자</th>
						<td><c:out value="${name}" /></td>
					</tr>
					<tr>
						<th class="text-center" >내용</th>
						<td><textarea class="form-control" name="content" rows="20" >${content}</textarea></td>
					</tr>
					
				</tbody>
			</table>
		<hr>
		<div class=" qna-write-btn">
			
			<input type="submit" class="btn" value="수정하기" onclick="javascript form.action='<%=request.getContextPath()%>/qna/modifyPro.do'"> 
			
			<input type="submit" class="btn" value="목록" onclick="javascript form.action='<%=request.getContextPath()%>/qna/qnaList.do'">
		</div>
		</form>
	</div>
	<footer>
		<jsp:include page="/pages/include/bottom.jsp"></jsp:include>	
	</footer>
</body>
</html>