---
typora-copy-images-to: images
---





## jQuery

- **실습**

  ```html
  <!DOCTYPE html>
  <html>
  <head>
  <meta charset="UTF-8">
  <title>Insert title here</title>
  <script src="js/jquery-3.6.0.min.js"></script>
  <script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
  <script type="text/javascript">
  	$(document).ready(function(){
  		alert('!!!')
  		//$('tr').css('background-color','yellow')
  		$('tr:odd').css('background-color','yellow')
  		
  		$('h2').dblclick(function(){
  			$(this).hide()
  		})
  		
  		$('h2').hover(function(){
  			$(this).css('background-color', 'pink')
  		}, function(){
  			$(this).css('background-color', 'white')
  		})
  		
  		/*  hover과 같은 의미
  		$('h2').mouseenter(function(){
  			$(this).css('background-color', 'pink')
  		})
  		
  		$('h2').mouseleave(function(){
  			$(this).css('background-color', 'white')
  		})
  		*/
  	})
  	
  </script>
  
  </head>
  <body>
  	<h2>회원명부</h2>
  	<table border="1">
  		<tr>
  			<th>이름</th>
  			<th>전화번호</th>
  		</tr>
  		<tr>
  			<td>양현석</td>
  			<td>010-2222-2222</td>
  		</tr>
  		<tr>
  			<td>양현석</td>
  			<td>010-2222-2222</td>
  		</tr>
  		<tr>
  			<td>양현석</td>
  			<td>010-2222-2222</td>
  		</tr>
  		<tr>
  			<td>양현석</td>
  			<td>010-2222-2222</td>
  		</tr>
  		<tr>
  			<td>양현석</td>
  			<td>010-2222-2222</td>
  		</tr>
  		<tr>
  			<td>양현석</td>
  			<td>010-2222-2222</td>
  		</tr>
  		<tr>
  			<td>양현석</td>
  			<td>010-2222-2222</td>
  		</tr>
  		<tr>
  			<td>양현석</td>
  			<td>010-2222-2222</td>
  		</tr>
  		<tr>
  			<td>양현석</td>
  			<td>010-2222-2222</td>
  		</tr>
  		<tr>
  			<td>양현석</td>
  			<td>010-2222-2222</td>
  		</tr>
  		<tr>
  			<td>양현석</td>
  			<td>010-2222-2222</td>
  		</tr>
  		<tr>
  			<td>양현석</td>
  			<td>010-2222-2222</td>
  		</tr>
  		<tr>
  			<td>양현석</td>
  			<td>010-2222-2222</td>
  		</tr>
  	</table>
  </body>
  </html>
  ```



- **실습**

  ```html
  <!DOCTYPE html>
  <html>
  <head>
  <meta charset="UTF-8">
  <title>Insert title here</title>
  <script src="js/jquery-3.6.0.min.js"></script>
  <script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
  <script type="text/javascript">
  	$(document).ready(function(){
  		$('input').on({ //Attach an event handler function for one or more events to the selected elements.
  			'keydown' : function() {
  				$(this).css('color','orange')
  			},
  			'keyup' : function() {
  				$(this).css('color','black')
  			},
  			'focus' : function() {
  				$(this).css('border','2px dotted green')
  				$(this).css('background-color','red')
  			},
  			'blur' : function() {
  				$(this).css({//json객체로 사용할 수 있다.
  					'border' : '2px dotted green',
  					'background-color' : '#7FFFD4'
  				}) 
  			}
  		})
  		
  		/*
  		$('input').keydown(function(){
  			$(this).css('color','orange')
  		})
  		
  		$('input').keyup(function(){ 
  			$(this).css('color','black')
  		})
  		
  		$('input').focus(function(){ //포커스 
  			$(this).css('border','2px dotted green')
  			$(this).css('background-color','red')
  		})
  		
  		$('input').blur(function(){ //포커스를 잃을 때
  			$(this).css('border','2px dotted green')
  			$(this).css('background-color','#7FFFD4')
  		})
  		*/
  	})
  	
  </script>
  
  </head>
  <body>
  	이름 : <input type="text"><br>
  	전화번호 : <input type="text"><br>
  </body>
  </html>
  ```



- **실습**

  ```html
  <!DOCTYPE html>
  <html>
  <head>
  <meta charset="UTF-8">
  <title>Insert title here</title>
  <script src="js/jquery-3.6.0.min.js"></script>
  <script type="text/javascript">
  	$(document).ready(function(){
  		$(window).scroll(function(){
  			console.log('scroll..')
  		})
  		
  		$(window).resize(function(){
  			console.log('resize...')
  		})
  		
  	})
  </script>
  </head>
  <body>
  	<h1>hello</h1>
  	<h1>hello</h1>
  	<h1>hello</h1>
  </body>
  </html>
  ```

  

- **실습**

  ```html
  <!DOCTYPE html>
  <html>
  <head>
  <meta charset="UTF-8">
  <title>Insert title here</title>
  <style type="text/css">
  	div{
  		width: 100px;
  		height: 100px;
  		background-color: red;
  	}
  </style>
  
  <script src="js/jquery-3.6.0.min.js"></script>
  <script type="text/javascript">
  	$(document).ready(function(){
  		//$('div').hide(1000) 
  		//$('div').show(1000)  
  		
  		//$('div').toggle(1000) //hide인 상태이면 show를 실행하고 
  		//$('div').toggle(1000) //show인 상태이면 hide를 실행한다.
  		
  		$('div').fadeOut(1000) //사라지기
  		$('div').fadeIn(1000) //보여주기
  		
  		$('div').fadeTo(1000, 0.4) //사라지면서 0.4의 투명도를 가지기
  	})
  </script>
  
  </head>
  <body>
  	<div>
  	
  	</div>
  </body>
  </html>
  ```



- **실습**

  ```html
  <!DOCTYPE html>
  <html><head>
  <meta charset="UTF-8">
  <title>Insert title here</title>
  <style>
  	#content {
  		height: 50px;
  		background-color: pink;
  	}
  	
  	#menu {
  		height: 100px;
  		background-color: yellow;
  		margin: 0 20px;
  		padding: 20px;
  		display: none;
  	}
  </style>
  <script src="js/jquery-3.6.0.min.js"></script>
  <script>
  	$(document).ready(function() {
  		$('#content').mouseenter(function() {
  			$('#menu').slideDown(2000)
  			$('#menu').slideUp(2000)
  			$('#menu').slideToggle(2000)
  			$('#menu').slideToggle(2000)
  			
  		})
  	})
  </script>
  </head>
  <body>
  	<div id="content">마우스를 접근시켜 보세요</div>
  	<div id="menu" style="display: none;">메뉴화면입니다</div>
  </body>
  </html>
  ```

  

- **실습**

  ```html
  <!DOCTYPE html>
  <html><head>
  <meta charset="UTF-8">
  <title>Insert title here</title>
  <style>
  	div {
  		position : absolute;
  		width: 100px;
  		height: 100px;
  		background-color: #5F9EA0;
  	}
  </style>
  <script src="js/jquery-3.6.0.min.js"></script>
  <script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
  <script>
  	$(document).ready(function() {
  		/*
  		$('div').animate({
  			'width': '+=200px', //현재 가지고 있는 크기에서 200px을 더한 값
  			'height': '500px',
  			'left' : '100px',
  			'top' : '50px',
  			'opacity' : '0.6' //투명도 설정
  		}, 2000)
  		*/
  		
  		/*
  		//단계별 수행
  		$('div').animate({
  			width : '200px'
  		})
  		
  		$('div').animate({
  			left : '100px',
  			opacity : '0.3'
  		},200)
  		
  		$('div').animate({
  			width : '100px',
  			height : '3000px',
  			opacity : '0.7'
  		},300)
  		
  		$('div').animate({
  			height : '200px',
  			left : '0px',
  			top : '100px',
  			opacity : '1',
  			backgroundColor : 'blue' //백그라운드컬러는 적용되지 않음 aimate에서
  		}, function(){ //animate가 다 끝나면 이 함수를 실행시켜라
  			$(this).css('background-color', 'blue')
  		})
  		*/
  		
  		// animate가 실행되는 중간에 백그라운드 컬러를 변경하고 싶을 때는
  		// jquery-ui를 사용해야 한다. 스크립트 추가
  		$('div').animate({
  			width : '200px'
  		})
  		
  		$('div').animate({
  			left : '100px',
  			opacity : '0.3'
  		},200)
  		
  		$('div').animate({
  			width : '100px',
  			height : '3000px',
  			opacity : '0.7'
  		},300)
  		
  		$('div').animate({
  			height : '200px',
  			left : '0px',
  			top : '100px',
  			opacity : '1',
  			backgroundColor : 'blue' 
  		})
  	})
  </script>
  </head>
  <body>
  	<div style="width: 300px; height: 200px;"></div>
  </body></html>
  ```



- **실습**

  ```html
  <!DOCTYPE html>
  <html>
  <head>
  <meta charset="UTF-8">
  <title>Insert title here</title>
  <style type="text/css">
  	h1 {
  		display: none
  	}
  </style>
  <script src="js/jquery-3.6.0.min.js"></script>
  <script type="text/javascript">
  	$(document).ready(function(){
  		//3초동안 첫번째문장이 화면에 보여준 후 "complete" 내용의 경고창을 보여주기
  		/*
  		$('h1').slideDown(3000)
  		alert('complete')
  		*/
  		//위의 코드처럼 h1태그를 띄우기 전에 코드만 실행되면 바로 실행된다.
  		//그래서 alert창이 먼저 띄워지고 h1 태그가 슬라이드 다운되는 상태로 보인다.
  		
  		//그래서 첫 번째 문장을 다 띄우고 난 후에 alert창을 띄워야 한다. 
  		/*
  		$('h1').slideDown(3000,function(){ //슬라이드 다운이 끝나고 나면 콜백 함수로 alert창을 띄운다.
  			alert('complete')
  		})
  		*/
  		
  		//3초에 걸쳐 첫 번째 문장을 화면에 보여준 후 , 글자색을 파란색으로 변경한 후 
  		//슬라이드 효과로 문장을 올라갔다 내려갔다 수행한 후 배경색을 노란색으로 변경
  		/*
  		$('h1').fadeIn(3000,function(){
  			$('h1').css('color','blue')
  			$('h1').toggle(1000)
  			$('h1').toggle(1000, function(){
  				$('h1').css('background-color','yellow')
  			})
  		})
  		*/
  		
  		//2번째 방법(함수 체이닝) - 이벤트 함수가 아니기때문에 사용할 수 있다.
  		$('h1').fadeIn(3000, function() {
  			$(this).css('color', 'blue').slideUp(2000).slideDown(2000, function() {
  				$(this).css('background-color', 'yellow')
  			})
  		})
  	})
  </script>
  </head>
  <body>
  	<h1>첫 번째 문장</h1>
  </body>
  </html>
  ```



- **실습**

  ```html
  <!DOCTYPE html>
  <html>
  <head>
  <meta charset="UTF-8">
  <title>Insert title here</title>
  <script src = "js/jquery-3.6.0.min.js"></script>
  <script>
  	
  	let starFunc = function() {
  		$('h1')[0].innerText += '*'
  	}
  
  	$(document).ready(function() {
  		
  		$('h1').click(function(e, data='*') {
  			
  	//		data = data || '*'
  			
  //			$(this)[0].innerText += '*'
  			$(this)[0].innerText += data
  		})
  			
  		
  		let pid = setInterval(function() {
  		//	starFunc()
  		//	$('h1').click()
  		//	$('h1').trigger('click', '#')
  			$('h1').trigger('click')
  		}, 1000)
  		
  		setTimeout(function() {
  			clearInterval(pid)
  		}, 2000)
  		
  	})
  </script>
  </head>
  <body>
  	<h1>start : </h1>
  </body>
  </html>
  ```



- **실습**

  ```html
  <!DOCTYPE html>
  <html>
  <head>
  <meta charset="UTF-8">
  <title>Insert title here</title>
  <script src = "js/jquery-3.6.0.min.js"></script>
  <script>
  	$(document).ready(function() {
  		
  		let pTags = document.getElementsByTagName("p")
  		//alert('innerHTML : ' + pTags[0].innerHTML)
  		//alert('innserText : ' + pTags[0].innerText)
  		
  		let html = $('p').html() //p 태그가 여러 개면 0번지의 데이터 하나만 추출
  		let text = $('p').text() //p 태그가 여러 개면 모든 데이터를 추출
  		
  		alert('추출된 HTML : ' + html)
  		alert('추출된 HTML : ' + text)
  		
  		$('button').click(function(){
  			let name = $('#name').val()
  			alert('이름' + name)
  		})
  		
  		//a 태그 속성값 추출
  		let addr = $('a').attr('href')
  		alert('추출된 주소 :' +addr)
  	})
  </script>
  </head>
  <body>
  	<p>내 이름은 <strong>홍길동</strong>이고, 별명은 <em>의적</em>입니다. </p>
  	<p>내 이름은 <strong>홍길순</strong>이고, 별명은 <em>의적2</em>입니다. </p>
  	이름 : <input type="text" id="name">
  	<button>입력완료</button>
  	<a href = "http://www.naver.com">네이버</a>
  </body>
  </html>
  ```



- **실습**

  ```html
  <!DOCTYPE html>
  <html>
  <head>
  <meta charset="UTF-8">
  <title>Insert title here</title>
  <script src = "js/jquery-3.6.0.min.js"></script>
  <script>
  	$(document).ready(function() {
  		$('#btn01').click(function(){
  			$('p').text('문장이 변환되었습니다.')
  		})
  		
  		$('#btn02').click(function(){
  			$('p').html('<mark>문장이 변환되었습니다.</mark>')
  		})
  		$('#btn03').click(function(){
  			//document.querySelector('a').setAttribute('href', 'http://www.naver.com')
  			//document.querySelector('a').href = 'http://www.naver.com'
  			$('a').attr('href', 'http://www.naver.com' )
  			$('a').attr('target','_blank')
  		})
  	})
  </script>
  </head>
  
  <body>
  	<a href = "http://www.daum.net">사이트 이동</a>
  	<p>첫 번째 문장입니다.</p>
  	<p>두 번째 문장입니다.</p>
  	<button id="btn01">text변환</button>
  	<button id="btn02">html변환</button>
  	<button id="btn03">주소변환</button>
  </body>
  </html>
  ```





- **실습**

  ```html
  <!DOCTYPE html>
  <html>
  <head>
  <meta charset="UTF-8">
  <title>Insert title here</title>
  <script src = "js/jquery-3.6.0.min.js"></script>
  <script>
  	$(document).ready(function() {
  		$('#btn01').click(function(){
  			//자바스크립트
  			/*
  			let h1Tag = document.createElement('h1')
  			let text = document.createTextNode('세 번재 문장')
  			h1Tag.appendChile(text)
  			document.querySelector('div').appendChild(h1Tag)
  			*/
  			//jquery
  			$('div').append('<h1>세 번째 문장</h1>')
  		})
  		
  		$('#btn02').click(function(){
  			$('ul').append('<li>검정</li>')
  			$('ul').prepend('<li>오잉</li>') //앞에 붙음
  		})
  		
  		$('#btn03').click(function(){
  			//$('body').prepend('<h4>추가된 문장</h4>')
  			//$('body').prepend('<h3>또 추가된 문장</h3>')
  			//$('body').prepend('<h6>마지막으로 추가된 문장</h6>')
  			
  			//추가할 문장이 여러개인 경우에
  			$('body').prepend('<h4>추가된 문장</h4>','<h3>또 추가된 문장</h3>', '<h6>마지막으로 추가된 문장</h6>')
  			//적은 순서대로 들어간다.(위의 결과와 다르게 나온다.)
  			
  			
  		})
  		
  		$('#btn04').click(function(){
  			$('img').after('<b>뒤에추가</b>')
  			$('img').before('<i>앞에추가</i>','img앞 추가')
  		})
  	})
  </script>
  </head>
  <body>
  	<div>
  		<h1>첫번째 문장</h1>
  		<h1>두번째 문장</h1>
  		<!-- <h1>세번째 문장</h1> -->
  	</div>
  	<h2>색상표</h2>
  	<ul>
  		<li>빨강</li>
  		<li>노랑</li>
  		<li>파랑</li>
  		<li>초록</li>
  	</ul>
  	
  	<h1>Hello</h1>
  	<img src="https://t1.daumcdn.net/daumtop_chanel/op/20200723055344399.png">
  	<h2>good-bye</h2>
  	
  	<button id="btn01">문장추가</button>
  	<button id="btn02">색상추가</button>
  	<button id="btn03">본문추가</button>
  	<button id="btn04">형제추가</button>
  </body>
  </html>
  ```





- **실습**

  ```html
  <!DOCTYPE html>
  <html><head>
  <meta charset="UTF-8">
  <title>Insert title here</title>
  <style>
  	div {
  		border: 1px solid red
  	}
  </style>
  <script src="js/jquery-3.6.0.min.js"></script>
  <script>
  	$(document).ready(function() {
  		
  		$('button').click(function() {
  		//	$('p').remove()
  		
  		//	$('div').remove()
  		//	$('div').empty()
  		
  		//	$('p.c01').remove()
  		//	$('p').remove('.c01')
  		
  		//	$('p.c01').remove()
  		//	$('p#id02').remove() 두 코드가 같은 결과를 나타냄
  			$('p').remove('.c01, #id02') //여러 개를 지울 때 사용 
  		})
  		
  	})
  </script>
  </head>
  <body>
  	<div>
  		<p id="id01">나는 홍길동이다</p>
  		<mark class="c01">나는 김길동이다</mark><br>
  		나는 고길동이다<br>
  		<p class="c01">나는 한길동이다</p>
  	</div>
  	<p id="id02">나는 박길동이다</p>
  	<button>삭제</button>
  
  </body></html>
  ```



- **실습**

  ```html
  <!DOCTYPE html>
  <html>
  <head>
  <meta charset="UTF-8">
  <title>Insert title here</title>
  <style>
  	div {
  		border: 1px solid red
  	}
  	
  	.red {
  		color : red
  	}
  	
  	.blue {
  		color : blue
  	}
  	
  	.backGray {
  		background-color: gray
  	}
  	
  </style>
  <script src="js/jquery-3.6.0.min.js"></script>
  <script>
  	$(document).ready(function() {
  		
  		$('p').addClass('red') //class를 추가
  		$('#id01').addClass('blue backGray') //css에서 정의한 .red, .blue의 순서에 따라서 반영 됨
  		//동시에 2개도 가능
  		$('.c01').removeClass('red')
  		
  		
  	})
  </script>
  </head>
  <body>
  	<div>
  		<p id="id01" class="red blue backGray">나는 홍길동이다</p>
  		<mark class="c01">나는 김길동이다</mark><br>
  		나는 고길동이다<br>
  		<p class="c01">나는 한길동이다</p>
  	</div>
  	<p id="id02" class="red">나는 박길동이다</p>
  	<button>삭제</button>
  
  </body>
  </html>
  ```





- **실습**

  ```html
  
  ```





- **실습**

  ```html
  
  ```





- **실습**

  ```html
  
  ```





- **실습**

  ```html
  
  ```





- **실습**

  ```html
  
  ```





- **실습**

  ```html
  
  ```





- **실습**

  ```html
  
  ```





- **실습**

  ```html
  
  ```

















