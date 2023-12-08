<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="user.UserDAO" %>
<%@ page import="java.io.PrintWriter" %> <!-- js 문법 사용하기 위한  -->
<% request.setCharacterEncoding("UTF-8"); %> <!-- 건너오는 데이터를 모두 utf-8로 만들기 위한   -->
<jsp:useBean id="user" class="user.User" scope="page"/>  <!-- User 클래스를 자바빈즈로 사용, 이 페이지에서만 사용   -->
<jsp:setProperty name="user" property="userID"/>  <!-- 로그인페이지에서 넘겨준 유저아이드를 그대로 받아서 한명의 사용자의 user id 에 넣음 -->
<jsp:setProperty name="user" property="userPassword"/> 
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>jsp 게시판 웹 사이트</title>
</head>
<body>
	<%
		UserDAO userDAO = new UserDAO();
		int result = userDAO.login(user.getUserID(), user.getUserPassword()); // 6라인 javabeans의 user 
		
		if (result == 1) {
			PrintWriter script = response.getWriter();
			script.println("<script>");
			script.println("location.href='main.jsp'");
			script.println("</script>");
		}
		else if(result == 0) {
			PrintWriter script = response.getWriter();
			script.println("<script>");
			script.println("alert('비밀번호가 틀립니다.');");
			script.println("history.back()");
			script.println("</script>");
		}
		else if(result == -1) {
			PrintWriter script = response.getWriter();
			script.println("<script>");
			script.println("alert('아이디가 존재하지 않습니다.');");
			script.println("history.back()");
			script.println("</script>");
		}
		else if(result == -2) {
			PrintWriter script = response.getWriter();
			script.println("<script>");
			script.println("alert(db 오류입니다.')");
			script.println("history.back()");
			script.println("</script>");
		}
	%>


</body>
</html>
