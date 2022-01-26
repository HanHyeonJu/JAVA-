<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
  <head>
    <meta charset="UTF-8" />
    <title>AJAX 연습</title>
  </head>
  <body>
    <h1>1부터 빈칸에 적은 숫자까지 총합은?</h1>
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
        let url = 'sum.jsp?val=' + v; // 요청할 서버의 jsp 페이지 주소(파라메터는 무조건 문자열로 받음, 그래서 계산해주는 페이지에서 파라메터 val을 int형으로 변환해줌)
        request.open('GET', url, true);
        request.send();
        request.onreadystatechange = function () {
          if (request.readyState == 4 && request.status == 200) { // 요청한 주소와 ajax의 객체가 잘 연결되었다는 뜻
            let val = request.responseText;
            console.log(val);
            output.textContent = val;
          }
        };
      }
    </script>
  </body>
</html>
