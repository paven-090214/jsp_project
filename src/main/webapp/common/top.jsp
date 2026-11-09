<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<%
	String currentStatus = (String)application.getAttribute("loginCheck");
%>
<body>
	<h2>
		<a href="${pageContext.request.contextPath }/index.jsp">
			MAINPAGE
		</a>
		<%
		if ("true".equals(currentStatus)) {
		%>
		<form action="${pageContext.request.contextPath}/logout.do" method="post">
			<input type="submit" value="로그아웃" />
		</form>
		<form action="${pageContext.request.contextPath}/userManage.do" method="get">
		    <button type="submit" value="editMe" name="action">마이페이지</button>
		</form>
		<%
		}
		else {
		%>
		<a href="${pageContext.request.contextPath }/account/login.jsp">
			로그인
		</a>
		<a href="${pageContext.request.contextPath }/account/signup.jsp">
			회원가입
		</a>
		<%
		}
		%>
	</h2>
	<br>
</body>
</html>