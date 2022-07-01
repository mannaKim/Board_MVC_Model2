<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<c:if test="${empty loginUser}">
  <jsp:forward page="../board.do?command=loginForm"/>
</c:if>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <title>main</title>
  <link rel="stylesheet" type="text/css" href="css/board.css">
</head>
<body>
  <div id="wrap" align="center">
    <h1>게시글 리스트</h1>
    <table>
      <tr>
      	<td colspan="5" style="border:white;">
      	  <div style="float:left;">
      	  	${loginUser.name}(${loginUser.userid})님 로그인
      	    <input type="button" value="회원정보수정" 
      	    	onClick="location.href='board.do?command=updateMemberForm'">
      	    <input type="button" value="로그아웃" 
      	    	onClick="location.href='board.do?command=logout'">
      	  </div>
      	  <div style="float:right;">
      	  	<a href="board.do?command=boardWriteForm">게시글 등록</a>
      	  </div>
      	</td>
      </tr>
      
      <!-- 게시글 목록 -->
      <tr>
        <th>번호</th><th>제목</th><th>작성자</th><th>작성일</th><th>조회</th>
      </tr>
      <c:forEach items="${bList}" var="board">
        <tr align="center">
          <td width="100">${board.num}</td>
          <td align="left">
            <a href="board.do?command=boardView&num=${board.num}">${board.title}</a>
            <c:if test="${board.replycnt>0}">
              <span style="color:green; font-weight:bold;">[${board.replycnt}]</span>
            </c:if>
            <c:if test="${not empty board.imgfilename}">
              <span style="color:yellowgreen; font-weight:bold;">[img]</span>
            </c:if>
          </td>
          <td width="100">${board.userid}</td>
          <td width="200">${board.writedate}</td>
          <td width="100">${board.readcount}</td>
        </tr>
      </c:forEach>
    </table>
    <br><br>
    
    <!-- 여기서부터 페이지 표시 -->
    <div id="paging" style="margin:0 auto; font-size:110%; font-weight:bold">
    
      <!-- 페이지가 클릭될떄마다 이동할 링크 기본경로를 JSTL 변수에 저장 -->
      <c:url var="action" value="board.do?command=main"/>
      
      <!-- prev표시 : 전달된 paging의 prev 변수가 true이면 표시, false이면 표시하지 않음 -->
      <c:if test="${paging.prev}">
        <a href="${action}&page=${paging.beginPage-1}">◀</a>&nbsp;
        <!-- 링크되는 주소 : board.do?command=main&page=?? -->
        <!-- 맨 왼쪽 페이지(beginPage보다 1 작은 페이지)로 이동 -->
      </c:if>
      
      <!-- beginPage부터 endPage까지 페이지 표시 & 해당 페이지에 링크 -->
      <c:forEach begin="${paging.beginPage}" end="${paging.endPage}" var="index">
        <c:choose>
          <c:when test="${paging.page==index}">
            <span style="color:yellowgreen">${index}&nbsp;</span>
          </c:when>
          <c:otherwise>
            <a href="${action}&page=${index}">${index}</a>&nbsp;
          </c:otherwise>
        </c:choose>
      </c:forEach>
      
      <!-- next표시 : 전달된 paging의 next 변수가 true이면 표시, false이면 표시하지 않음 -->
      <c:if test="${paging.next}">
        <a href="${action}&page=${paging.endPage+1}">▶</a>
        <!-- 맨 오른쪽 페이지(endPage보다 1 큰 페이지)로 이동 -->
      </c:if>
      
    </div>
  </div>
</body>
</html>