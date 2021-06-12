---
typora-copy-images-to: images
---



## css

- **exam11.html**

  ```html
  <!DOCTYPE html>
  <html>
  <head>
  	<meta charset="UTF-8">
  	<title>Insert title here</title>
  	<style type="text/css">
  		input[type=password] { /*input태그 안에 type속성 중 password만 적용*/
  			background-color: #F08080
  		}
  		
  		img { /*$가 '~로 끝나면'이라는 의미임*/
  			width: 100px;
  			height: 100px;
  			border: 2px solid #4B0082;
  		}
  		
  		img[src$='jpg'] { /*$가 '~로 끝나면'이라는 의미임*/
  			border-color: #FF69B4;
  		}
  		
  		img[src^='images'] { /*images로 시작한다면*/
  			border-color: green;
  		}
  		
  		img[src*='영희']{ /*src가 가지고 있는 값이 cat을 포함한다면*/
  			border-color: #800000;
  		}
  		
  		/* #menu li 과 #menu > li 의 차이
  			- (후손) #menu li는 #menu 밑에 있는 모든 li는 모두 포함
  			- (자식) #menu > li는 #menu 바로 밑에 있는 레벨의 li만 포함
  		 */
  		
  	</style>
  </head>
  <body>
  	<form>
  		아이디 : <input type="text"> <br>
  		비밀번호 : <input type="password"> <br>
  	</form>
  	<br>
  	<img alt="" src="../images/고영희.jpg">
  	<img alt="" src="../images/달.jpg">
  	<img alt="" src="../images/맛있겠다.jpg">
  	<img alt="" src="../images/마라샹궈.jpg">
  	<img alt="" src="../images/안녕.jpg">
  	<img alt="" src="../images/짱구.gif">
  	<img alt="" src="../images/허걱.png">
  </body>
  </html>
  ```



- **exam12.html**

  ```html
  <!DOCTYPE html>
  <html>
  <head>
  	<meta charset="UTF-8">
  	<title>Insert title here</title>
  	<style type="text/css">
  		h1 + h2 { /* +는 인접연산자이다. h1다음에 바로 h2가 나오면 h2의 스타일이 바뀜 */
  			color: #DB7093;
  		}
  		h1 ~ h2 { /* ~는 h1의 오른쪽을 기준으로 h2가 있으면 h2의 스타일이 바뀜*/
  			color: #808000;
  		}
  	</style>
  </head>
  <body>
  	<h2>Header2-1</h2>
  	<h1>Header1-1</h1>
  	<h3>Header3-1</h3>
  	<h2>Header2-2</h2>
  	<h2>Header2-3</h2>
  	<h2>Header2-4</h2>
  	<h2>Header2-5</h2>
  	<h1>Header1-2</h1>
  	<h2>Header2-6</h2>
  	<h2>Header2-7</h2>
  </body>
  </html>
  ```

  

- **exam13**

  ```html
  <!DOCTYPE html>
  <html>
  <head>
  	<meta charset="UTF-8">
  	<title>Insert title here</title>
  	<style type="text/css">
  		#numberlist	li{
  			float: left;
  			margin: 20px;
  			font-size: 16pt;
  			list-style-type: none;
  		}
  		
  		li:first-child { 
  			color : green
  		}
  		
  		li:last-child {
  			color : red;
  		}
  		
  		li:nth-child(2n-1) {
  			color : orange;
  		}
  		
  		p {
  			clear : both;
  		}
  		
  		a:link { /*a태그에 링크가 잡혀있는 경우*/
  			color : black;
  			text-decoration: none; /*underline 없애기*/
  		}
  		
  		a:visited { /*링크를 클릭한적이 있는 경우에도 black으로 나오게*/
  			color: black;
  		}
  		
  		a:hover { /*마우스를 링크에 올리는 순간의 스타일*/
  			background-color: yellow;
  			text-decoration: underline;
  		}
  		
  		a:active { /*태그가 활성화 되어있는 경우 즉, 클릭했을 때*/
  			color: pink;
  			font-weight: bold;
  		}
  		
  		p.text::first-letter { /* ::가 2개*/
  			font-size :4em;
  			color : red;
  		}
  		
  		p.text::selection { /*드래그하면 노란색*/
  			background-color: yellow;
  		}
  		
  		p.text::first-line { /*첫 번째 줄을 line*/
  			text-decoration: underline;
  		}
  		
  		h1:first-of-type { /*같은 레벨에서 가장 먼저 나오는 h1에 스타일 적용*/
  			color: red;
  		}
  		
  		h2:last-of-type { /*같은 레벨에서 가장 나중에 나오는 h2에 스타일 적용*/
  			color : green;
  		}
  		
  		h3:only-of-type { /*같은 레벨에서 혼자 h3라면 스타일 적용*/
  			background-color: pink;
  		}
  		
  		h1:only-of-type { /*같은 레멜에서 h1이 혼자라면 스타일 적용*/
  			background-color: green;
  		}
  		
  		h2:only-child { /*자식이 1명만 있는데 그게 h2이면 스타일 적용(div에 자식이 2명있어서 적용 안 됨)*/
  			background-color: gray;
  		}
  	</style>
  </head>
  <body>
  	<ul id="numberlist">
  		<li>ONE</li>
  		<li>TWO</li>
  		<li>THREE</li>
  		<li>FOUR</li>
  		<li>FIVE</li>
  		<li>SIX</li>
  		<li>SEVEN</li>
  		<li>EIGHT</li>
  		<li>NINE</li>
  		<li>TEN</li>
  	</ul> 
  	<p>
  		<a href="http://www.naver.com">naver</a>
  		<a href="http://www.daum.net">daum</a>
  	</p>
  	<p class="text">
  		ABCDEFGHIJK <br>
  		LMNOPQRST <br>
  		UVWXYZ <br>
  	</p>
  	<p class="text">
  		abcdefghijk <br>
  		lmnopqrst <br>
  		uvwxyz <br>
  	</p>
  	
  	<h1>Header1</h1>
  	<h3>Header3</h3>
  	<h2>Header2</h2>
  	<h4>Header4</h4>
  	<h1>Header1</h1>
  	<h2>Header2</h2>
  	<h6>Header6</h6>
  	<div>
  		<h2>Sub2</h2>
  		<h1>Sub1</h1>
  	</div>
  </body>
  </html>
  ```



- **exam14**

  ```html
  <!DOCTYPE html>
  <html>
  <head>
  	<meta charset="UTF-8">
  	<title>Insert title here</title>
  	<style type="text/css">
  	
  		body {
  			width: 900px;
  			/*margin을 사용해서 가운데로 오게 만듦*/
  			margin-left: auto; 
  			margin-right: auto;
  		}
  		header {
  			background-color: #B0E0E6;
  			height: 100px;
  		}
  		
  		aside {
  			background-color: #DDA0DD;
  			height: 600px;
  			width: 30%; 
  			float: left;
  		}
  		
  		section {
  			background-color: pink;
  			height: 600px;
  			width: 70%; 
  			float: right;
  		}
  		
  		footer {
  			background-color: #FFEFD5;
  			height: 100px;
  			clear : both;
  		}
  	</style>
  </head>
  <body>
  	<header>헤더부분</header>
  	<aside>aside부분</aside>
  	<section>section부분</section>
  	<footer>footer 부분</footer>
  </body>
  </html>
  ```



## 자바스크립트

- **document**

  - Document 객체
    - Document 객체는 **웹 페이지 그 자체**를 의미한다.
    - 웹 페이지에 존재하는 HTML 요소에 접근하고자 할 때는 반드시 Document 객체부터 시작해야 한다.
  - Document 메소드
    -  HTML 요소의 선택
    - HTML 요소의 생성
    - HTML 이벤트 핸들러 추가
    - HTML 객체의 선택

  > 참고 : http://tcpschool.com/javascript/js_dom_document



- 













