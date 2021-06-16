---
typora-copy-images-to: images
---





## 자바스크립트

- **배열**

  ```html
  <!DOCTYPE html>
  <html>
  <head>
  	<meta charset="UTF-8">
  	<title>Insert title here</title>
  	<script type="text/javascript">
  		//자바스크립트에서 배열은 다양한 데이터 타입을 넣을 수 있다.
  		var array = ['A', 12, true, undefined, 'hello', null, 12.34]
  		
  		document.write(`* array[0] : ${array[0]} <br>`)
  		document.write(`* array[1] : ${array[1]} <br>`)
  		document.write(`* array[2] : ${array[2]} <br>`)
  		document.write(`* array[3] : ${array[3]} <br>`)
  		document.write(`* array[4] : ${array[4]} <br>`)
  		
  		document.write(`* array : ${array} <br>`) //배열안의 정보가 출력(A,12,true,,hello,,12.34)
  		document.write(`* array 배열의 원소 개수 : ${array.length} 개 <br>`)
  		
  		array[7] = 'add'
  		document.write(`* add삽입 후 배열의 원소 개수 : ${array.length} 개 <br>`)
  		
  		//7번지까지 데이터가 있는 배열에 10번지에 데이터를 삽입했다면?
  		array[10] = 'end'
  		document.write(`* end삽입 후 array : ${array}<br>`) //8, 9 번지에 null값이 들어간다.
  		
  		//그렇다면 맨 마지막에 데이터를 삽입하고 싶다면?
  		array[array.length] = '추가...'
  		document.write(`* array : ${array}<br>`)
  		
  		//이렇게 매번 array.length를 쓰기는 번거로우니 함수를 사용
  		array.push('추가..2')
  		document.write(`* array : ${array}<br>`)
  		
  		//push와 pop은 stack이라는 메모리 구조와 관련이 있다.
  		//맨 뒤의 데이터를 삭제하고 싶을 때
  		var temp = array.pop()
  		document.write('* 삭제된 데이터 : ' + temp + '<br>')
  		document.write(`* array : ${array}<br>`)
  		
  		document.write(`* type : ${typeof(array)} <br>`) //배열은 객체로 인식 (type : object)
  		
  		var str = "hello world!"
  		document.write(`* str length : ${str.length} <br>`)
  		
  	</script>
  </head>
  <body>
  	
  </body>
  </html>
  ```



- **배열과 반복문**

  ```html
  <!DOCTYPE html>
  <html>
  <head>
  	<meta charset="UTF-8">
  	<title>Insert title here</title>
  	<script type="text/javascript">
  		let names = ['윤길동', '홍길동', '강길동', '박길동', '구길동']
  		document.write(`* names : ${names} <br>`)
  		
  		document.write('<hr>')
  		document.write('<br>***반복문*** <br>')
  		for(let index = 0 ; index < names.length ; ++index){
  			document.write(`* name : ${names[index]} <br>`)
  			console.log(names[index])
  		}
  		
  		document.write('<hr>')
  		document.write('<br>***반복문*** <br>')
  		for(let i in names){
  			document.write(`* name : ${names[i]} <br>`)
  		}
  		
  		document.write('<hr>')
  		document.write('<br>***반복문*** <br>')
  		for(let name of names){ 
  			document.write(`* name : ${name} <br>`)
  		}
  	</script>
  </head>
  <body>
  	
  </body>
  </html>
  ```

- **function**

  - 오버로딩을 지원하지 않음
  - return값과 매개변수의 type을 지정하지 않아도 된다. 

  ```html
  <!DOCTYPE html>
  <html>
  <head>
  	<meta charset="UTF-8">
  	<title>Insert title here</title>
  	<script type="text/javascript">
  		var aaa = function () {
  			alert('익명함수 aaa() 호출...')
  		}
  		function aaa() {
  			alert('선언적 aaa() 호출...')
  		}
  		
  		function callbackFunc(f) {
  			f()
  		}
  		
  		//callbackFunc은 함수를 매개변수로 받고 그 함수를 실행
  				
  		//***익명함수***
  		var func = function test() {
  			alert(`test()...`)
  		}
  		
  		//총 합 구하기
  		function getSum(a,b){
  			let s = 0;
  			while(a <= b) {
  				s += a ++;
  			}
  			return s;
  		}
  		
  		function printSum(a,b){
  			//alert(`${a} ~ ${b} 총 합 : getSum(a,b)`)
  			function sum(a,b) {
  				let s = 0;
  				while(a <= b) {
  					s += a ++;
  				}
  				return s;
  			}
  			var s = sum(a,b)
  		alert(`${a} ~ ${b} 총 합 : ${s}`)
  		}
  		printSum(3,4)
  		
  		//매개변수에 익명함수를 작성하여 넘겨줌
  		callbackFunc(function() {
  			alert('익명함수 호출...')
  		}) 
  		
  		callbackFunc(func)
  		
  		
  	</script>
  </head>
  <body>
  
  </body>
  </html>
  ```

  

- **callback 함수**

  - 비동기 처리 방식에 의해 야기될 수 있는 문제점을 callback함수를 사용하여 해결

  - 비유로 이해하는 콜백 함수 동작 방식

    > https://joshua1988.github.io/web-development/javascript/javascript-asynchronous-operation/

    - 콜백 함수의 동작 방식은 일종의 식당 자리 예약과 같습니다. 일반적으로 맛집을 가면 사람이 많아 자리가 없습니다. 그래서 대기자 명단에 이름을 쓴 다음에 자리가 날 때까지 주변 식당을 돌아다니죠. 만약 식당에서 자리가 생기면 전화로 자리가 났다고 연락이 옵니다. 그 전화를 받는 시점이 여기서의 콜백 함수가 호출되는 시점과 같습니다. 손님 입장에서는 자리가 날 때까지 식당에서 기다리지 않고 근처 가게에서 잠깐 쇼핑을 할 수도 있고 아니면 다른 식당 자리를 알아볼 수도 있습니다.
    - 자리가 났을 때만 연락이 오기 때문에 미리 가서 기다릴 필요도 없고, 직접 식당 안에 들어가서 자리가 비어 있는지 확인할 필요도 없습니다. 자리가 준비된 시점, 즉 데이터가 준비된 시점에서만 저희가 원하는 동작(자리에 앉는다, 특정 값을 출력한다 등)을 수행할 수 있습니다.

  ```html
  <!DOCTYPE html>
  <html>
  <head>
  	<meta charset="UTF-8">
  	<title>Insert title here</title>
  	<script type="text/javascript">
  		var aaa = function () {
  			alert('익명함수 aaa() 호출...')
  		}
  		function aaa() {
  			alert('선언적 aaa() 호출...')
  		}
  		
  		function callbackFunc(f) {
  			f()
  		}
  		
  		//callbackFunc은 함수를 매개변수로 받고 그 함수를 실행
  				
  		//***익명함수***
  		var func = function test() {
  			alert(`test()...`)
  		}
  		
  		
  		//매개변수에 익명함수를 작성하여 넘겨줌
  		callbackFunc(function() {
  			alert('익명함수 호출...')
  		}) 
  		
  		callbackFunc(func)
  		
  		
  	</script>
  </head>
  <body>
  
  </body>
  </html>
  ```

  

- arguments

  ```html
  <!DOCTYPE html>
  <html>
  <head>
  	<meta charset="UTF-8">
  	<title>Insert title here</title>
  	<script type="text/javascript">
  		
  		//매개변수를 정의하지 않아도 함수가 실행된다.
  		//그럼 매개변수를 정의하지 않았을 때 
  		//함수 내부적으로 변수를 받을 수 있는 방법은?
  		//arguments
  		
  		function func() {
  			//alert('call()')
  			console.log(arguments) 
  			switch(arguments.length) {
  			case 0 :
  				alert('call...')
  				break;
  			case 1 :
  				alert('data : ' + arguments[0])
  				break;
  			case 2 :
  				alert(`no : ${arguments[0]} , msg : ${arguments[1]}`)
  				break;
  			}
  		}
  		 
  		func() //Arguments(0)
  		func('A')
  		func(12, 'hello')
  	</script>
  </head>
  <body>
  
  </body>
  </html>
  ```



- 초기화

  ```html
  <!DOCTYPE html>
  <html>
  <head>
  	<meta charset="UTF-8">
  	<title>Insert title here</title>
  	<script type="text/javascript">
  		function func(num1, num2, num3) {
  			alert(`num1 : ${num1} , num2 : ${num2} , num3 : ${num3}`)
  		}
  		
  		//func() 
  		//func(1)
  		//func(10,20)
  		//func(100,200,300)
  		
  		//정의하지 않은 매개변수는 undefined가 나온다.
  		//그래서 undefined 말고 0을 출력하게 만드는 방법은?
  		//자바스크립트에서는 0이 아닌 다른 정수는 모두 true로 인식 
  		//그리고 undefined, null을 false로 인식
  		
  		function func(num1, num2, num3) {
  			if(!num1) num1 = 0
  			if(!num2) num2 = 0
  			if(!num3) num3 = 0
  			alert(`num1 : ${num1} , num2 : ${num2} , num3 : ${num3}`)
  		}
  		
  		//func() 
  		//func(1)
  		//func(10,20)
  		//func(100,200,300)
  		
  		//다른 방법
  		function func(num1, num2, num3) {
  			
  			num1 = num1 || 0 
  			num2 = num2 || 0
  			num3 = num3 || 0
  			
  			alert(`num1 : ${num1} , num2 : ${num2} , num3 : ${num3}`)
  		}
  		
  		//func() 
  		//func(1)
  		//func(10,20)
  		//func(100,200,300)
  		
  		//다른 방법
  		function func(num1 = 0, num2 = 0, num3 = 0) {
  			//매개변수가 들어오지 않았을 때 0으로 초기화하라
  			//한 번 초기화가 되면 뒤에 있는 인자들도 초기화가 되어야 한다.
  			//초기화하지 않으면 에러 발생
  			alert(`num1 : ${num1} , num2 : ${num2} , num3 : ${num3}`)
  		}
  		func(10,20)
  	</script>
  </head>
  <body>
  
  </body>
  </html>
  ```

  

- **클로저**

  - 리턴값으로 함수를 넘기는 것

  ```html
  <!DOCTYPE html>
  <html>
  <head>
  	<meta charset="UTF-8">
  	<title>Insert title here</title>
  	<script type="text/javascript">
  	
  		//리턴 값으로 익명함수 넘기기 
  		function returnFunction() {
  			return function() {
  			alert('return function()...')
  			}
  		}
  		
  		//let ret = returnFunction()
  		//ret()
  		//위의 두 줄과 같은 말
  		returnFunction()()
  		
  		function test(name) {
  			var output = 'Hello' + name + '!!!'
  		}
  		
  		//test() 안에 있는 output 안에 있는 변수를 잠시 유지시키게 만들고 싶을 때
  		//Closure (클로저) 사용
  		
  		function test(name) {
  			var output = 'Hello' + name + '!!!'
  			return function () {
  				alert(output)
  			}
  		}
  		
  		//test()안에 있는 익명함수가 끝날 때까지 output 변수를 유지시켜준다.
  		f = test('홍길동')
  		f()
  		//위의 두 줄이랑 같은 의미 
  		test('홍길동')()
  	</script>
  </head>
  <body>
  
  </body>
  </html>
  ```

  

- 타이머 함수

  ```html
  <!DOCTYPE html>
  <html>
  <head>
  	<meta charset="UTF-8">
  	<title>Insert title here</title>
  	<script type="text/javascript">
  		/*
  			자바스크립트 내장함수
  			1. 타이머
  				- setTimeout(function(), ms) : 일정 시간이 지나면 어떤 행위를 하도록 만듦
  											 - ms : 밀리세컨드
  				- setInterval(function(), ms) : 일정 시간마다 함수를 실행 
  				- clearInterval(id) :  setInterval 실행 취소
  			2. 코드실행함수
  				eval(String)
  			3. 숫자변환함수
  				parseInt(String)
  				parseFloat(String)
  		
  		*/
  		
  		setTimeout(function() {
  			//alert('일어나!!!')
  			clearInterval(pid)
  		}, 3000)
  		
  		var pid = setInterval(function() {
  			document.write(`현재시간 : ${new Date()} <br>`)
  		}, 1000)
  	</script>
  </head>
  <body>
  
  </body>
  </html>
  ```

  

- 코드실행함수 eval

  ```html
  <!DOCTYPE html>
  <html>
  <head>
  	<meta charset="UTF-8">
  	<title>Insert title here</title>
  	<script type="text/javascript">
  		//코드실행함수
  		
  		let str = 'alert("Hello");'
  		str += 'var number = 10;'
  		str += 'alert(number);'
  		str += 'let array = [10, 12.34, "ABC", true];'
  		str += 'alert("배열의 원소 개수 : " + array.length);'
  		
  		eval(str)
  		
  		number += 100
  		alert(number) //110
  		//즉, eval로 실행한 문법이 계속 유지된 것을 확인할 수 있다. 
  	</script>
  </head>
  <body>
  
  </body>
  </html>
  ```



- 숫자변환함수

  ```html
  <!DOCTYPE html>
  <html>
  <head>
  	<meta charset="UTF-8">
  	<title>Insert title here</title>
  	<script type="text/javascript">
  	//3. 숫자변환함수
  		let won = '1000'
  		let dollar = '1.5$'
  		
  		alert(Number(won) + 500)
  		alert(parseInt(won) + 500)
  		
  		//이 둘의 차이점
  		won = '1000원'
  		alert(Number(won) + 500) //NaN (not a number)
  		alert(parseInt(won) + 500) //1500
  		//Number() 안에 넣는 값은 무조건 숫자문자
  		//parseInt()는 숫자문자까지만 가져와서 값을 변경
  		
  		alert(parseInt(dollar)) //1
  		alert(parseFloat(dollar)) //1.5
  		
  	</script>
  </head>
  <body>
  
  </body>
  </html>
  ```

  

- 람다

  ```html
  <!DOCTYPE html>
  <html>
  <head>
  	<meta charset="UTF-8">
  	<title>Insert title here</title>
  	<script>
  		/*
  			function() {} ==> () => {}
  		*/
  		
  		let multiply = function(){
  			return a * b
  		}
  		
  		//위와 같은 표현
  		const multiply = (a,b) => {return a * b}
  		
  		//위와 같은 표현
  		const multiply = (a,b) => a*b
  		
  		alert(multiply(10,20))
  	</script>
  </head>
  <body>
  
  </body>
  </html>
  ```



- **매개변수**

  ```html
  <!DOCTYPE html>
  <html>
  <head>
  	<meta charset="UTF-8">
  	<title>Insert title here</title>
  	<script>
  		function func01() {
  			
  			alert(arguments[0] + ":" + arguments[1] + ":" + arguments[2])
  		}
  		
  		function func02(...numbers){
  			console.log(numbers)
  			alert(numbers[0] + ":" + numbers[1] + ":" + numbers[2])
  		}
  		
  		func01(1,2,3)
  		func02(1,2,3)
  		func02(1) //2번째와 3번째는 undefined가 나온다.
  		
  		//매개변수로 숫자 2개는 무조건 들어가기
  		function func03(a, b, ...numbers) {
  			alert(`a : ${a} , b : ${b}, number : ${numbers}`)
  			alert('numbers type : '+typeof(numbers) ) //object
  		}
  		
  		func03(1,2,3,4,5,6)
  		
  		
  		let array = [10,20,30]
  		func01(array) //10,20,30, undefined, undefined
  		//왜냐하면 array도 하나의 인자일 수 있으므로
  		//argument[0]에 array가 들어간 것이다.
  		//그래서
  		func01(array[0], array[1], array[2]) //10:20:30
  		//를 사용해야 한다.
  		
  		//만약 배열의 수가 많다면? 
  		func01.apply(null,array) //10:20:30
  		//func01을 실행하는데 array가 가지고 있는 요소들을 각각 분리시켜서
  		//arguments에 넣어라
  		
  		//다른 방법(위와 같은 결과)
  		func01(...array) //func01(10,20,30)
  		
  		func03(1,2,...array) // = func03(1,2,10,20,30)
  		func03(...array, ...array) //func
  		
  	</script>
  </head>
  <body>
  
  </body>
  </html>
  ```

  

















