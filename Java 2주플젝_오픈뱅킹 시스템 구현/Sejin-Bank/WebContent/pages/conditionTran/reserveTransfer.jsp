<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<title>이체</title>
	
	<link rel="stylesheet" href="<%=request.getContextPath()%>/resources/css/board/bootstrap.css">
	<link rel="stylesheet" href="<%=request.getContextPath()%>/resources/css/board/myCss.css">
	<link rel="stylesheet" href="<%=request.getContextPath()%>/resources/css/signUpCss.css">	
	<link rel="stylesheet" href="<%=request.getContextPath()%>/resources/css/account/account.css">
	<script type="text/javascript" src="<%=request.getContextPath()%>/resources/js/board/bootstrap.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/resources/js/qna.js"></script>
	
	<script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
	<script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
		
	<script src="<%=request.getContextPath()%>/resources/js/jquery-3.6.0.min.js"></script>
	<script type="text/javascript">
		$(document).ready(function(){
			//조회계좌번호에 선택한 계좌 넣기
			if(${! empty account_number}) { //계좌번호가 비어있지 않으면
				//value값으로 seleted 넣기
				$('#select_account_number').val('${account_number}').attr('selected', 'selected')
			}
			
			getAccountInfo()
			
			//선택된 계좌 번호에 따라서 그 계좌의 디테일 정보 조회
			$('#select_account_number').change(function(){
				
				getAccountInfo()
			})
			
			
			//해당 계좌의 정보 가져오기(세진은행)
			function getAccountInfo() {
				let selected_account_number = $('#select_account_number').val()
				
				$.ajax({
					type: "POST",
					url : "<%=request.getContextPath()%>/account/accountInfoAjax.do",
					method : 'post',
					data : {
						"account_number" : $('#select_account_number').val()
					},
					datatype : 'json',
					success : function(data) {
				        var obj = JSON.parse(data)
				        console.log(obj.id)
				        document.getElementById('info_balance').innerText = obj.balance
					},
					error: function (request, status, error){
						var msg = "ERROR : " + request.status + "<br>"
						msg += + "내용 : " + request.responseText + "<br>" + error;
						console.log(msg);
						
					}

				})
			}
			
			
			
		}) //$(document).ready()
		
		
		
		function transferFormCheck() {
			var ajax_pw = '';
			//계좌 비번 가져오기
			$.ajax({
				type: "POST",
				url : "<%=request.getContextPath()%>/account/getAccountPassword.do",
				method : 'post',
				data : {
					"account_number" : $('#select_account_number').val()
				},
				async: false,
				success : function(data) {
					ajax_pw = $.trim(data)
				},
				error: function (request, status, error){
					var msg = "ERROR : " + request.status + "<br>"
					msg += + "내용 : " + request.responseText + "<br>" + error;
					console.log(msg);
				}

			})
			
			let bank_code = $('#select_bank_code').val()
			let receive_account_number = $('#receive_account_number').val()
			let result = availTransfer(ajax_pw, bank_code, receive_account_number)
			return result;
		}
		
		
	</script>
</head>
<body>
	<!-- header -->
	<header>
		<jsp:include page="/pages/include/topMenu.jsp" ></jsp:include>
	</header>
	<div class="qna-write-container">
		<h3>세진은행</h3>
		<br>
		<h4>출금정보</h4>
		<br>
		<form action="<%=request.getContextPath()%>/account/reserveTranPro.do" method="post" onsubmit="return transferFormCheck()">	
		<input type="hidden" name="send_bank_code" value="S">
		<table class="table ">
			<tbody>
				
				<tr>
					<th class="text-center">조회계좌번호</th>
					<td>
						<select id="select_account_number" name="send_account_number">
							<c:forEach items="${accountInfoList}" var="accountInfo" varStatus="status">
								<c:if test="${status.first }">
									<option selected="selected">${accountInfo.account_number}</option>
								</c:if>
								<c:if test="${! status.first }">
									<option>${accountInfo.account_number}</option>
								</c:if>
							</c:forEach>
						</select>
					</td>
				</tr>
				
				<tr>
					<th class="text-center" >계좌잔액</th>
					<td id="info_balance" ></td>
				</tr>
				<tr>
					<th class="text-center" >계좌비밀번호</th>
					<td><input type="password"  id="account_password" class="input-text-box" required>
					<span class="account_password_check" id="account_password_check"></span></td>
				</tr>
				
			</tbody>
		</table>
			
		
		<br><br>
		<h4>예약정보</h4>
		<table class="table">
			<tbody>
				<tr>
					<th class="text-center">예약날짜</th>
					<td><input type="date" name="condition_tran_date" id="condition_tran_date"></td>
					
				</tr>
				<tr>
					<th class="text-center">예약시간</th>
					<td><input type="time" name="condition_tran_time" id="condition_tran_time"></td>
					
				</tr>
			</tbody>
		</table>
		
		<!-- 선택된 계좌의 상세 정보 -->
		<h4>입금정보</h4>
		<br>	
		
			<table class="table">
				<tbody>
					<tr>
						<th class="text-center">입금은행</th>
						<td>
							<select name="receive_bank_code" id="select_bank_code">
								<option value="S">세진은행</option>
								<option value="J">종범은행</option>
								<option value="D">소영은행</option>
								<option value="Y">현석은행</option>
							</select>
						</td>
					</tr>
					<tr>
						<th class="text-center" >입금 계좌번호</th>
						<td ><input type="text" name="receive_account_number" id="receive_account_number" class="input-text-box" required>
							<span class="account_password_check" id="account_receive_account_check"></span>
						</td>
					</tr>
					<tr>
						<th class="text-center">이체금액</th>
						<td><input type="text" name="tran_amt" class="input-text-box" required></td>
					</tr>
					<tr>
						<th class="text-center" >받는 통장 메모</th>
						<td><input type="text" class="input-text-box" name ="receive_content"></td>
					</tr>
					<tr>
						<th class="text-center" >내 통장 메모</th>
						<td><input type="text" class="input-text-box" name ="my_content"></td>
					</tr>
					
				</tbody>
			</table>
			
			<br><br>
			<hr>
			
			<div class=" qna-write-btn">
				<input type="submit" class="btn" value="이체하기" >
			</div>
		</form>
		
	</div>
	<footer>
		<jsp:include page="/pages/include/bottom.jsp"></jsp:include>	
	</footer>
	<script src="<%=request.getContextPath()%>/resources/js/account/account.js"></script>
</body>
</html>