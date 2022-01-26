<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
  <head>
    <meta charset="UTF-8" />
    <title>AJAX 연습</title>
  </head>
  <body>
    <h1>아이디 입력 시 DB검색해 결과 출력</h1>
    <!-- onkeyup이벤트 속성을 안 쓰면 addEventListner쓰면 됨 -->
    아이디 입력 : <input onkeyup="sendServer()" type="text" /> 
    <!-- 계산결과  -->
    <div id="output"></div> 
    <script>
      const input = document.querySelector('input[type="text"]'); // 타입 태그로 찾음
      const output = document.getElementById('output');
      const request = new XMLHttpRequest(); // ajax의 객체 생성

      // input.addEventListener('keyup', sendServer);
      
      function sendServer() {
    	//input태그에 아이디를 입력하면 이벤트 keyup 일때 AJAX 서버로 GET 요청 (id)
        let id = input.value;
        let url = 'getDB.jsp?id=' + id; // 요청할 서버의 jsp 페이지 주소
        request.open('GET', url, true);
        request.send();
        request.onreadystatechange = getInfo;
      }
      
      function getInfo() {
          if (request.readyState == 4 && request.status == 200) {
            let info = request.responseText; // info?
            output.textContent = info;
          } // innterHTML은 div 안에 있는 HTMl 전체 내용을 가져오기 때문에 태그 사용가능, textContent는 태그는 안 들어가지고 텍스트값만 그대로 읽고 출력함
        };
    </script>
  </body>
</html>
