<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<title>예약이체 정보 조회</title>
	
	<link rel="stylesheet" href="<%=request.getContextPath()%>/resources/css/board/bootstrap.css">
	<script type="text/javascript" src="<%=request.getContextPath()%>/resources/js/board/bootstrap.js"></script>
	<!-- 합쳐지고 최소화된 최신 CSS -->
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">
	
	<!-- 부가적인 테마 -->
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap-theme.min.css">
	
	<!-- 합쳐지고 최소화된 최신 자바스크립트 -->
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script>
	
	<script src="<%=request.getContextPath()%>/resources/js/jquery-3.6.0.min.js"></script>
	
</head>
<script type="text/javascript">
	$('document').ready(function(){
		if('${reserveResult}' == 'success'){
			console.log("click open");
			$('#modalBox').modal('show');
		}
		
	})
	


</script>
<body>
	<!-- header -->
	<header>
		<jsp:include page="/pages/include/topMenu.jsp"></jsp:include>
	</header>
	<div class="container qna-board-container">
		<h2 class="text-center">예약이체 정보 조회</h2>
		
		<table class="table table-hover">
			<thead>
			<tr>
				<th>보내는 계좌</th>
				<th>받는 계좌</th>
				<th>금액</th>
				<th>보내는 날짜</th>
				<th>보내는 시간</th>
				<th>이체 상태</th>
			</tr>
			</thead>
			<tbody>
				<c:forEach items="${accountInfoList}" var="accountInfo" varStatus="status">
					<tr> 
						<td>${accountInfo.account_number }</td>
						<td>${accountInfo.nickname }</td>
						<td>${accountInfo.reg_date }</td>
						<td><fmt:formatNumber value="${accountInfo.balance }" pattern="#,###" /></td>
						<td><a class="qna-board-btn btn-default" href="<%=request.getContextPath()%>/account/detail.do?account_number=${accountInfo.account_number}">조회</a>
							<a class="qna-board-btn btn-default" href="<%=request.getContextPath()%>/account/transfer.do?account_number=${accountInfo.account_number}">이체</a>
						</td>
					</tr>
				</c:forEach>
				
			</tbody>
		</table>
		<hr>
		
	</div>
	<footer>
		<jsp:include page="/pages/include/bottom.jsp"></jsp:include>	
	</footer>
	<!-- 모달 영역 -->
	<div id="modalBox" class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
		<div class="modal-dialog" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">×</span></button>
					<h4 class="modal-title" id="myModalLabel">모달 타이틀</h4>
				</div>
				<div class="modal-body">
				내용
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-primary" id="closeModalBtn">확인</button>
				</div>
			</div>
		</div>
	</div>
	<script type="text/javascript">
		// 모달 안의 취소 버튼에 이벤트를 건다.
		$('#closeModalBtn').on('click', function(){
			$('#modalBox').modal('hide');
			console.log("click close");
		});
	
	</script>

</body>
</html>