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

/**
 * Servlet implementation class SignupController
 */
@WebServlet("/signup.do")
public class SignupController extends HttpServlet {
	//서블릿은 HttpServlet을 항상 상속 받아야 함
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String userId = request.getParameter("userId");
		String UserNickname = request.getParameter("userNickname");
		String userPassword = request.getParameter("userPassword");
		HttpSession session = request.getSession();
		session.setAttribute("userId", userId);
		
		ViewResolver viewResolver = new ViewResolver("/", ".jsp");
		
		UserDTO dto = new UserDTO();
		dto.setId(userId);
		dto.setNickname(UserNickname);
		dto.setPassword(userPassword);
		dto.setRole("user");
		
		UserDAO dao = new UserDAO();
		UserDTO result = dao.signup(dto);
		
		if (result != null) {
			ServletContext application = request.getServletContext();
			application.setAttribute("loginCheck", "true");
			RequestDispatcher dispatcher = request.getRequestDispatcher(viewResolver.resolve("index"));
			dispatcher.forward(request, response);
		}
		else {
			session.setAttribute("signupErr", "불가능한 항목이 포함되어있습니다.");
			response.sendRedirect(viewResolver.resolve("community/account/signup"));
		}
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
