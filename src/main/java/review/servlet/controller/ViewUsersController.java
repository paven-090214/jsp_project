package review.servlet.controller;

import jakarta.servlet.RequestDispatcher;
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
import java.util.ArrayList;

/**
 * Servlet implementation class ViewUsersController
 */
@WebServlet("/usersView.do")
public class ViewUsersController extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("action");
		ViewResolver viewResolver = new ViewResolver("/", ".jsp");
		HttpSession session = request.getSession();
		
		UserDAO dao = new UserDAO();
		
		ArrayList<UserDTO> list = new ArrayList<UserDTO>();
		list = dao.allUser();
		if ("currentPage".equals(action)) {
			request.setAttribute("userList", list);
			RequestDispatcher dispatcher = request.getRequestDispatcher(viewResolver.resolve("index"));
			dispatcher.forward(request, response);
		}
		else if ("newPage".equals(action)){
			session.setAttribute("userList", list);
			response.sendRedirect(viewResolver.resolve("community/account/userList"));
		}
}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
