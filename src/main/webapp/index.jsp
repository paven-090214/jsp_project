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
<h1>INDEX</h1>
	<%@ include file="./common/top.jsp" %>
	<hr />
	<h3>\${pageContext.request.contextPath }</h3>
	<p>현재웹의 컨텍스트 경로를 가져옴. 현재의 프로젝트의 경우 /community</p>
	<%
		String role = (String) session.getAttribute("role");
		ArrayList<UserDTO> list = 
				(ArrayList<UserDTO>) request.getAttribute("userList");
		if ("admin".equals(role)) {
	%>
	<h2>유저 정보 확인 버튼</h2>
	<form method="post" action="usersView.do">
		<input type="submit" name="action" value="currentPage" />
	</form>
	<form method="post" action="usersView.do">
		<input type="submit" name="action" value="newPage" />
	</form>
	<%
			if (list != null) {
				for (UserDTO user : list) {
					%>
					<form method="get" action="userManage.do">
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
		}
	%>
	
</body>
</html>