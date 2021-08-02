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
		function fileDownload(name) {
			location.href='<%=request.getContextPath()%>/qna/fileDownload.do?name=' + name
		}
	</script>
</head>
<body>
	<!-- header -->
	<header>
		<jsp:include page="/pages/include/topMenu.jsp" ></jsp:include>
	</header>
	<div class="qna-write-container">
		<h2 class="text-center">Q&A</h2>
		<form method="post" name="writeForm" onsubmit="return fileCheck()">
			<input type="hidden" name="re_step" value="${board.re_step}">
			<input type="hidden" name="re_level" value="${board.re_level}">
			<input type="hidden" name="reference" value="${board.reference}">
			<input type="hidden" name="id" value="${board.id}" >
			<input type="hidden" name="title" value="${board.title}">
			<input type="hidden" name="name" value="${board.name}">
			<input type="hidden" name="content" value="${board.content}">
			
			
			<input type="hidden" name="type" value="reply">
			
			<table class="table ">
				<tbody>
					<tr>
						<th class="text-center">제목</th>
						<td><c:out value="${board.title}"/></td>
						
					</tr>
					<tr>
						<th class="text-center" >작성자</th>
						<td><c:out value="${board.name}" /></td>
					</tr>
					<tr>
						<th class="text-center" >조회수</th>
						<td><c:out value="${board.view_cnt}" /></td>
					</tr>
					<tr>
						<th class="text-center" >내용</th>
						<td><c:out value="${board.content}"/></td>
					</tr>
					<tr>
						<th class="text-center" >작성일</th>
						<td><c:out value="${board.reg_date}"/></td>
					</tr>
					<tr>
						<th class="text-center" >첨부파일 <br><br><br></th>
						
						<td>
							<c:forEach items="${fileList}" var="file">
								<a href="/qnaImages/${file.file_chan_name}">${file.orgn_file_name}</a>
								<span>[${file.file_size}]</span>
								<input type="button" onclick="fileDownload('${file.file_chan_name}')" value="다운로드"> 
								<br>
							</c:forEach>
						</td>
					</tr>
				</tbody>
			</table>
		<hr>
		<div class=" qna-write-btn">
			<c:if test="${board.re_step eq 0}">
				<input type="submit" class="btn" value="답글" onclick="javascript: form.action='<%=request.getContextPath()%>/qna/write.do'"> 
			</c:if>
			<input type="submit" class="btn" value="목록" onclick="javascript: form.action='<%=request.getContextPath()%>/qna/qnaList.do'">
			<c:if test="${memberVO.id eq board.member_id }">
				<input type="submit" class="btn" onclick="javascript: form.action='<%=request.getContextPath()%>/qna/modify.do'" value="수정">
			</c:if>
		</div>
		</form>
	</div>
	<footer>
		<jsp:include page="/pages/include/bottom.jsp"></jsp:include>	
	</footer>
</body>
</html>