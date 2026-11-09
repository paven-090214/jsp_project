<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>SIGNUP</h1>
	<%@ include file="./../common/top.jsp" %>
	
	<form action="${pageContext.request.contextPath}/signup.do" method="post">
		아이디 : <input type="text" name="userId"/> <br />
		닉네임 : <input type="text" name="userNickname"/> <br />
		비밀번호 : <input type="password" name="userPassword"/> <br />
		<input type="submit" value="회원가입" />
		<input type="reset" value="리셋" />
	</form>
	<a href="${pageContext.request.contextPath }/index.jsp">
		<button>메인메뉴</button>
	</a>
	<%
		String signup = (String) session.getAttribute("signupErr");
		if (signup != null) {
			%>
			<p><%=signup %></p>
			<%
		}
		session.removeAttribute("signup");
	%>
</body>
</html>