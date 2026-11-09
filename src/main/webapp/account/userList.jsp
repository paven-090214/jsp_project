<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    import="java.util.*"
    import="review.servlet.model.UserDTO"
    %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<%@ include file="./../common/top.jsp" %>
	
	<h1>유저 목록</h1> <hr />
	<%
		ArrayList<UserDTO> userList = new ArrayList<UserDTO>();
		userList = (ArrayList<UserDTO>)session.getAttribute("userList");
		session.removeAttribute("userList");
		if (userList.size() == 0) {
			%>
			<h1>목록이 비어있습니다.</h1>
			<%
		} else {
			for (UserDTO user : userList) {
			%>
			<form method="get" action="../userManage.do">
				<input type="hidden" name="userId" value="<%= user.getId() %>" />
			   <h4><%= user.getId() %></h4>
			   <p><%= user.getNickname() %></p>
			   <p><%= user.getPassword() %></p>
			   <p><%= user.getRole() %></p>
				<button 
					type="submit"
					name="action"
					value="editUser"
					>
					정보 변경
				</button>
				<hr />
			</form>
			<%
			}
		}
	%>
</body>
</html>