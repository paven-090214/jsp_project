package review.servlet.controller;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import review.servlet.model.UserDAO;
import review.servlet.model.UserDTO;
import review.servlet.view.ViewResolver;

import java.io.IOException;

@WebServlet("/login.do")
public class LoginController extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String userId = request.getParameter("userId");
		String userPassword = request.getParameter("userPassword");
		HttpSession session = request.getSession();
		session.setAttribute("userId", userId);
		UserDTO dto = new UserDTO();
		dto.setId(userId);
		dto.setPassword(userPassword);
		ViewResolver viewResolver = new ViewResolver("/",".jsp");
		
		UserDAO dao = new UserDAO();
		UserDTO result = dao.login(dto);
		if (result != null) {
			session.setAttribute("role", result.getRole());
			ServletContext application = request.getServletContext();
			application.setAttribute("loginCheck", "true");
			response.sendRedirect(viewResolver.resolve("community/index"));
		}
		else {
			session.setAttribute("loginError", "아이디 또는 비밀번호가 틀렸습니다.");
			response.sendRedirect(viewResolver.resolve("community/account/login"));
		}
}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}
