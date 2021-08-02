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
	<script src="<%=request.getContextPath()%>/resources/js/jquery-3.6.0.min.js"></script>
	
	<!-- 합쳐지고 최소화된 최신 CSS -->
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">
	
	<!-- 부가적인 테마 -->
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap-theme.min.css">
	
	<!-- 합쳐지고 최소화된 최신 자바스크립트 -->
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script>
	
	
</head>
<script type="text/javascript">
	$('document').ready(function(){
		if('${reserveResult}' == 'success'){
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
				<th>예약 날짜</th>
				<th>예약 시간</th>
				<th>이체 상태</th>
			</tr>
			</thead>
			<tbody>
				<c:forEach items="${conditionTranList}" var="condition" varStatus="status">
					<tr> 
						<td>${condition.send_account_number }</td>
						<td>${condition.receive_account_number }</td>
						<td><fmt:formatNumber value="${condition.tran_amt }" pattern="#,###" /></td>
						<td>${condition.condition_tran_date}</td>
						<td>${condition.condition_tran_time}</td>
						<c:if test="${condition.tran_state == 'S'}">
							<td>이체 완료</td>
						</c:if>
						<c:if test="${condition.tran_state == 'R' }">
							<td>이체 대기</td>
						</c:if>
					</tr>
				</c:forEach>
				
			</tbody>
		</table>
		<hr>
		<!-- 모달 영역 -->
		<div id="modalBox" class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
			<div class="modal-dialog" role="document">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">×</span></button>
						<h4 class="modal-title" id="myModalLabel">세진은행 예약이체</h4>
					</div>
					<div class="modal-body">
					예약이체를 설정했습니다.
					</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-primary" id="closeModalBtn">확인</button>
					</div>
				</div>
			</div>
		</div>
	</div>
	<footer>
		<jsp:include page="/pages/include/bottom.jsp"></jsp:include>	
	</footer>
	
	<script type="text/javascript">
		// 모달 안의 취소 버튼에 이벤트를 건다.
		$('#closeModalBtn').on('click', function(){
			$('#modalBox').modal('hide');
			console.log("click close");
		});
	
	</script>

</body>
</html>