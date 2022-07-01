<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <title>updateBoardForm</title>
  <link rel="stylesheet" type="text/css" href="css/board.css">
  <script src="script/board.js"></script>
</head>
<body>
  <div id="wrap" align="center">
    <h1>게시글 수정</h1>
    <form name="frm" method="post" action="board.do?command=updateBoard" enctype="multipart/form-data">
      <input type="hidden" name="num" value="${board.num}">
      <table>
        <tr>
          <th>작성자</th>
          <td>
            ${board.userid}
            <input type="hidden" name="userid" value="${loginUser.userid}">
          </td>
        </tr>
        <tr>
          <th>비밀번호</th>
          <td><input type="password" name="pass" size="20">*</td> 
        </tr>
        <tr>
          <th>이메일</th>
          <td><input type="text" name="email" size="20" value="${board.email}"></td> 
        </tr>
        <tr>
          <th>제목</th>
          <td><input type="text" name="title" size="70" value="${board.title}">*</td> 
        </tr>
        <tr>
          <th>본문</th>
          <td><textarea cols="70" rows="15" name="content">${board.content}</textarea>*</td> 
        </tr>
        <tr>
          <th>이미지</th>
          <td>
            <c:choose>
              <c:when test="${empty board.imgfilename}">
                <img src="upload/noname.jpg" height="80">
              </c:when>
              <c:otherwise>
                <img src="upload/${board.imgfilename}" height="80">
              </c:otherwise>
            </c:choose> 
            &nbsp;&nbsp;
            <input type="file" name="newFile">
            <input type="hidden" name="oldFile" value="${board.imgfilename}">
          </td> 
        </tr>
      </table>  
      <br>
      <input type="submit" value="수정" onClick="return boardCheck()">
      <input type="reset"  value="다시 작성"> 
	  <input type="button" value="돌아가기" 
	  		onClick="location.href='board.do?command=boardViewNoCount&num=${board.num}'">
    </form>
  </div>
</body>
</html>