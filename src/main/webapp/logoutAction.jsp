<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>jsp 게시판 웹 사이트</title>
</head>
<body>
	<%	
		session.invalidate(); // 이 페이지에 접속한 회원의 세션 뺏기 
	%>
	<script>
		location.href='main.jsp';
	</script>

</body>
</html>