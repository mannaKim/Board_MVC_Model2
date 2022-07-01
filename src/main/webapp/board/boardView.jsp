<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <title>boardView</title>
  <link rel="stylesheet" type="text/css" href="css/board.css">
  <script src="script/board.js"></script>
</head>
<body>
  <div id="wrap" align="center">
    <h1>게시글 상세보기</h1>
    <table>
      <tr>
        <th width="100">작성자</th><td width="330">${board.userid}</td>
        <th width="100">이메일</th><td>${board.email}</td>
      </tr>
      <tr>
        <th>작성일</th><td><fmt:formatDate value="${board.writedate}"/></td>
        <th>조회수</th><td>${board.readcount}</td>
      </tr>
      <tr>
        <th>제목</th><td colspan="3">${board.title}</td>
      </tr>
      <tr>
        <th>내용</th>
        <td colspan="2"><pre>${board.content}</pre></td>
        <td align="center">
          <c:choose>
            <c:when test="${empty board.imgfilename}">
              <img src="upload/noname.jpg" height="300">
            </c:when>
            <c:otherwise>
              <img src="upload/${board.imgfilename}" height="300">
            </c:otherwise>
          </c:choose>
        </td>
      </tr>
    </table>
    <br>
    <input type="button" value="목록으로" onClick="location.href='board.do?command=main'">
    <input type="button" value="수정" onClick="checkPass('${board.num}','update');">
    <input type="button" value="삭제" onClick="checkPass('${board.num}','delete');">
    <!-- checkPass의 두번째 전달인수 'update'와 'delete'는 추후에 오픈될 팝업창의 이름입니다.
    게시물의 비밀번호를 체크하는건 수정과 삭제가 같고, 현재 수정 또는 삭제 중 어떤걸 클릭해서
    팝업창이 열렸는지, 전달인수(설정한 팝업창의 이름)으로 구분합니다. -->
    
    <br><br><!-- 댓글 작성 영역 -->
    <c:set var="now" value="<%=new java.util.Date()%>"></c:set>
    <form name="frm_reply" method="get" action="board.do">
      <input type="hidden" name="command" value="addReply">
      <input type="hidden" name="boardnum" value="${board.num}">
      <table>
        <tr>
          <th width="100">작성자</th>
          <th width="100">작성일시</th>
          <th>내용</th>
          <th width="100">추가/삭제</th>
        </tr>
        <tr align="center">
          <td>${loginUser.userid}<input type="hidden" name="userid" value="${board.userid}"></td>
          <td><fmt:formatDate value="${now}" pattern="MM/dd HH:mm"/></td>
          <td><input type="text" name="reply" size="80"></td>
          <td><input type="submit" value="댓글 작성" onClick="return reply_check();"></td>
        </tr>
        <c:forEach items="${replyList}" var="reply">
          <tr align="center">
            <td>${reply.userid}</td>
            <td><fmt:formatDate value="${reply.writedate}" pattern="MM/dd HH:mm"/></td>
            <td align="left">&nbsp;${reply.content}</td>
            <td>
              <c:if test="${reply.userid==loginUser.userid}">
                <input type="button" value="삭제" onClick="location.href='board.do?command=delRep&replynum=${reply.replynum}&boardnum=${reply.boardnum}'">
              </c:if><!-- 로그인 한 유저가 쓴 댓글만 삭제할 수 있게 버튼을 표시합니다. -->
            </td>
          </tr>
        </c:forEach>
        
        
      </table>
    </form>
  </div>
</body>
</html>