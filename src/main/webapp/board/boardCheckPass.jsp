<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <title>boardCheckPass</title>
  <link rel="stylesheet" type="text/css" href="css/board.css">
  <script src="script/board.js"></script>
</head>
<body>
  <div align="center">
    <h1>비밀번호 확인</h1>
    <form name="frm" method="get" action="board.do">
      <input type="hidden" name="command" value="boardCheckPass">
      <input type="hidden" name="num" value="${param.num}"> <!-- 현재 게시글 번호를 히든으로 전달 -->
      <table style="width:80%">
	    <tr>
	      <th>수정/삭제 비밀번호</th>
	      <td><input type="password" name="pass" size="20"></td>
	    </tr>    
      </table>
      <br>
      <input type="submit" value="확인" onClick="return passCheck()">
      <!-- submit을 누르면 hidden으로 숨어있는 현재 게시물의 게시물 번호(num)와 사용자가 수정삭제하고자 입력한
      비밀번호(pass)를 갖고 비교하러 이동(command=boardCheckPass)합니다. -->
      <br><br>
      ${message}
    </form>
  </div>
</body>
</html>