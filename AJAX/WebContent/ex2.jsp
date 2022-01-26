<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
  <head>
    <meta charset="UTF-8" />
    <title>AJAX 연습</title>
  </head>
  <body>
    <h1>원하는 숫자의 구구단</h1>
    <input type="text" />
    <button onclick="sendServer();">계산하기</button>
    <!-- 계산결과  -->
    <div id="output"></div> 
    <script>
      const input = document.querySelector('input[type="text"]'); // 타입 태그로 찾음
      const output = document.getElementById('output');
      const request = new XMLHttpRequest(); // ajax의 객체 생성

      function sendServer() {
        //버튼을 누르면 ajax로 서버로 http 요청
        let v = input.value;
        let url = 'gugu.jsp?val=' + v; // 요청할 서버의 jsp 페이지 주소
        request.open('GET', url, true);
        request.send();
        request.onreadystatechange = function () {
          if (request.readyState == 4 && request.status == 200) {
            let val = request.responseText;
            console.log(val);
            output.innerHTML = val;
          } // innterHTML은 div 안에 있는 HTMl 전체 내용을 가져오기 때문에 태그 사용가능, textContent는 태그는 안 들어가지고 텍스트값만 그대로 읽고 출력함
        };
      }
    </script>
  </body>
</html>
