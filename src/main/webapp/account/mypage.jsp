<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    import="review.servlet.model.UserDTO"
    %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<%
	UserDTO targetuser = (UserDTO) session.getAttribute("userInfo");
%>
<body>
	<h1>유저 정보</h1>
	<form method="post" action="../userManage.do">
	<input type="hidden" value="<%=targetuser.getId() %>" name="userId"/>
	<input type="hidden" value="<%=targetuser.getRole() %>" name="userRole"/>
		<p> 아이디 : <%=targetuser.getId() %></p>
		닉네임 : <input type="text" name="userNickname" value="<%=targetuser.getNickname() %>" /> <br />
		비밀번호 : <input type="text" name="userPassword" value="<%=targetuser.getPassword() %>" /> <br />
		<p>권한 : <%=targetuser.getRole() %></p>
		<button type="submit" value="editUser" name="action">변경</button>
		<input type="reset" value="리셋" />
		<button type="submit" value="delUser" name="action">계정 삭제</button>
	</form>
	<a href="../index.jsp">
		<button>돌아가기</button>
	</a>
</body>
</html>