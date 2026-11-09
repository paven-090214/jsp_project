<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<%
	String loginError = (String) session.getAttribute("loginError");
%>
	<h1>LOGIN</h1>
	<form method="post" action="./../login.do">
		아이디 : <input type="text" name="userId"/> <br />
		비밀번호 : <input type="password" name="userPassword"/> <br />
		<input type="submit" value="로그인"/>
		<input type="reset" value="리셋"/>
	</form>
	<a href="${pageContext.request.contextPath }/index.jsp">
		<button>메인메뉴</button>
	</a>
	<%
	if (loginError != null) {
		%>
		<p><%= loginError %></p>
		<%
	}
	session.removeAttribute("loginError");
	%>
</body>
</html>