<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>checkSuccess</title>
</head>
<body>
  <script type="text/javascript">
    if(window.name=='update'){
    	window.opener.location.href = "board.do?command=updateBoardForm&num=${param.num}";
    } else if(window.name=='delete'){
    	var bool = confirm("삭제하시겠습니까?");
    	if(bool) 
    		window.opener.location.href = "board.do?command=deleteBoard&num=${param.num}";
    }
    self.close(); //팝업창 close
  </script>
</body>
</html>