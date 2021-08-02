<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
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
		$(document).ready(function(){
			
			//조회계좌번호에 선택한 계좌 넣기
			if('${account_number}' != '') { 
				$('#select_bank_name').val('${bank_code}').attr('selected', 'selected')
				accountInfoList()
				$('#select_account_number').val('${account_number}').attr('selected', 'selected')
			}else{  //전체로 선택되어 있어서 전체 계좌 리스트 가져오기
				$('#select_account_number').append('<option>선택</option>')
				$('#select_account_number').append('<c:forEach items="${accountInfoList}" var="accountInfo">')
					$('#select_account_number').append('<option>${accountInfo.account_number}</option>')
				$('#select_account_number').append('</c:forEach>')
				
			}
			
			getAccountInfo()
			
			//조회 기간에 현재 시간 넣기
			Date.prototype.toDateInputValue = (function() {
			    var local = new Date(this);
			    local.setMinutes(this.getMinutes() - this.getTimezoneOffset());
			    return local.toJSON().slice(0,10);
			});
			$('#start_date').val(new Date().toDateInputValue());
			$('#end_date').val(new Date().toDateInputValue());
			
			
			//선택된 은행에 따라서 계좌번호 리스트 가져오기
			$('#select_bank_name').change(function(){
				if($('#select_bank_name').val() == 'all' ) {
					$("select#select_account_number option").remove();
					$('#select_account_number').append('<option>선택</option>')
					$('#select_account_number').append('<c:forEach items="${accountInfoList}" var="accountInfo">')
						$('#select_account_number').append('<option>${accountInfo.account_number}</option>')
					$('#select_account_number').append('</c:forEach>')
				}else {
					accountInfoList()
				}
			})
			
			
			//해당 은행 계좌번호 리스트 가져오기
			function accountInfoList() {
				$.ajax({
					type: "POST",
					url : "<%=request.getContextPath()%>/openBanking/accountInfoListAjax.do",
					method : 'post',
					data : {
						"bank_code" : $('#select_bank_name').val()
					},
					dataType : 'JSON',
					async: false,
					success : function(data) {
				        transferList = eval(data)
				        $("select#select_account_number option").remove();
						$('#select_account_number').append('<option>선택</option>')
				        for(let i = 0; i < transferList.length; ++i ) {
				        	let info = transferList[i]
							$('#select_account_number').append('<option>' + info.account_number + '</option>')
				        	
				        }
				        
					},
					error: function (request, status, error){
						var msg = "ERROR : " + request.status + "<br>"
						msg += + "내용 : " + request.responseText + "<br>" + error;
						console.log(msg);
						
					}
	
				})
			}
			
			
			//선택된 계좌 번호에 따라서 그 계좌의 디테일 정보 조회
			$('#select_account_number').change(function(){
				getAccountInfo()
			})
			
			
			//해당 계좌의 정보 가져오기
			function getAccountInfo() {
				let selected_account_number = $('#select_account_number').val()
				$.ajax({
					type: "POST",
					url : "<%=request.getContextPath()%>/openBanking/accountInfoAjax.do",
					method : 'post',
					data : {
						"account_number" : $('#select_account_number').val(),
						"bank_code" : $('#select_bank_name').val(),
					},
					datatype : 'json',
					async: false,
					success : function(data) {
				        var obj = JSON.parse(data)
				        document.getElementById('info_nickname').innerText = obj.nickname
				        document.getElementById('info_account_number').innerText = obj.account_number
				        document.getElementById('info_balance').innerText = obj.balance
				        document.getElementById('info_reg_date').innerText = obj.reg_date
				        
					},
					error: function (request, status, error){
						var msg = "ERROR : " + request.status + "<br>"
						msg += + "내용 : " + request.responseText + "<br>" + error;
						console.log(msg);
						
					}

				})
			}
			
		}) //$(document).ready()
		
		//해당 계좌의 거래내역 가져오기(조건에 따라서)
		let start_date = $('#start_date').val()
		let end_date = $('#end_date').val()
		let inout_type = $('input:radio[name="inout_type"]:checked').val()
		let order = $('input:radio[name="order"]:checked').val() 
		
		function transferInfo() {
				
			$.ajax({
				type: "POST",
				url : "<%=request.getContextPath()%>/openBanking/transferInfoAjax.do",
				method : 'post',
				data : {
					"account_number" : $('#select_account_number').val(),
					"start_date" : $('#start_date').val(),
					"end_date" : $('#end_date').val(),
					"inout_type" : $('input:radio[name="inout_type"]:checked').val(),
					"order" : $('input:radio[name="order"]:checked').val() ,
					"bank_code" :   $('#select_bank_name').val()
				},
				datatype : 'json',
				success : function(data) {
			        var transferList = JSON.parse(data)
			        $('#transInfo > tbody').empty()
			        for(let i = 0; i < transferList.length; ++i ) {
			        	let info = transferList[i]
			        	console.log(info.content)
			        	
			        	$('#transInfo > tbody').append('<tr>')
			        		$('#transInfo > tbody').append('<td>'+ info.tran_date +'</td>')
			        		$('#transInfo > tbody').append('<td>'+ info.tran_time +'</td>')
			        		
			        		if(info.inout_type == 'O' || info.inout_type == '출금' || info.inout_type == 'M') { //출금
			        			$('#transInfo > tbody').append('<td>'+ info.tran_amt +'</td>')
			        			$('#transInfo > tbody').append('<td></td>')
			        			if(info.content == null || info.content == ''){
			        				info.content = '출금'
			        			}
			        		} else if(info.inout_type == 'O' || info.inout_type == '입금' || info.inout_type == 'A') { //입금
			        			$('#transInfo > tbody').append('<td></td>')
			        			$('#transInfo > tbody').append('<td>'+ info.tran_amt +'</td>')
				        			if(info.content == null || info.content == ''){
				        				info.content = '입금'
				        			}
			        		} 
			        		
			        		$('#transInfo > tbody').append('<td>'+ info.content +'</td>')
			        	$('#transInfo > tbody').append('</tr>')
			        }
			        
				},
				error: function (request, status, error){
					var msg = "ERROR : " + request.status + "<br>"
					msg += + "내용 : " + request.responseText + "<br>" + error;
					console.log(msg);
					
				}

			})
		}
	</script>
</head>
<body>
	<!-- header -->
	<header>
		<jsp:include page="/pages/include/topMenu.jsp" ></jsp:include>
	</header>
	<div class="qna-write-container">
		<h4>계좌조회</h4>
		<br>	
		<table class="table ">
			<tbody>
				<tr>
					<th class="text-center">은행</th>
					<td>
						<select id="select_bank_name" name="bank_code">
							<option value="all">전체</option>
							<option value="J">JBMorgan</option>
							<option value="D">DONJO</option>
							<option value="Y">YGBank</option>
						</select>
					</td>
				</tr>
				<tr>
					<th class="text-center">조회계좌번호</th>
					<td>
						<select id="select_account_number">
							<option>선택</option>
							<c:forEach items="${accountInfoList}" var="accountInfo">
								<option>${accountInfo.account_number}</option>
							</c:forEach>
							
						</select>
					</td>
				</tr>
				<tr>
					<th class="text-center" >조회기간</th>
					<td><input type="date" id="start_date">&nbsp;&nbsp; ~ &nbsp;&nbsp;<input type="date" id="end_date"></td>
				</tr>
				<tr>
					<th class="text-center" >조회내용</th>
					<td>
						<input type="radio" name="inout_type" value="inout" checked>&nbsp; 전체내역 &nbsp;
						<input type="radio" name="inout_type" value="in"> &nbsp;입금내역 &nbsp;
						<input type="radio" name="inout_type" value="out"> &nbsp;출금내역 &nbsp;
					</td>
				</tr>
				<tr>
					<th class="text-center" >조회결과순서</th>
					<td> 
						<input type="radio" name="order" value="desc" checked>&nbsp; 최근거래순 &nbsp;
						<input type="radio" name="order" value="asc"> &nbsp; 과거거래순 &nbsp;
					</td>
				</tr>
				
			</tbody>
		</table>
			
		<hr>
		<div class=" qna-write-btn">
			
			<input type="button" class="btn" value="조회" onclick="transferInfo()">
			
		</div>
		<br><br>
		
		
		<!-- 선택된 계좌의 상세 정보 -->
		<h4>계좌정보</h4>
		<br>	
		<table class="table">
			<tbody>
				<tr>
					<th class="text-center">계좌명(계좌별명)</th>
					<td id="info_nickname"></td>
				</tr>
				<tr>
					<th class="text-center" >계좌번호</th>
					<td id="info_account_number"></td>
				</tr>
				<tr>
					<th class="text-center" >계좌잔액</th>
					<td id="info_balance"></td>
				</tr>
				<tr>
					<th class="text-center" >신규일자</th>
					<td id="info_reg_date"></td>
				</tr>
				
			</tbody>
		</table>
		<br><br>
		
		<!-- 거래내역 조회 -->
		<h4>거래내역</h4>
		<table class="table table-hover" id="transInfo">
			<thead>
			<tr>
				<th>거래일자</th>
				<th>거래시간</th>
				<th>출금(원)</th>
				<th>입금(원)</th>
				<th>내용</th>
			</tr>
			</thead>
			<tbody>
				
			</tbody>
		</table>
	</div>
	<footer>
		<jsp:include page="/pages/include/bottom.jsp"></jsp:include>	
	</footer>
	<script src="<%=request.getContextPath()%>/resources/js/account/account.js"></script>
</body>
</html>