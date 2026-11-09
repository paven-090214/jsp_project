package review.servlet.controller;

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
 * Servlet implementation class MypageController
 */
@WebServlet("/userManage.do")
public class MypageController extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String action = request.getParameter("action");
		UserDTO dto = null;
		UserDAO dao = new UserDAO();
		HttpSession session = request.getSession();
		
		ViewResolver viewResolver = new ViewResolver("/", ".jsp");
		
		if ("editMe".equals(action)) {
			String userId = (String) session.getAttribute("userId");
			dto = dao.selectUser(userId);
		}
		
		else if ("editUser".equals(action)) {
			String userId = request.getParameter("userId");
			dto = dao.selectUser(userId);	
		}
		
		if (dto != null) {
			session.setAttribute("userInfo", dto);
		} else {
			session.removeAttribute("userInfo");
		}
		
		response.sendRedirect(viewResolver.resolve("community/account/mypage"));
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		UserDAO dao = new UserDAO();
		UserDTO dto = new UserDTO();
		String action = request.getParameter("action");
		String userId = request.getParameter("userId");
		
		ViewResolver viewResolver = new ViewResolver("/", ".jsp");
		
		if ("editUser".equals(action)) {
			String userNickname = request.getParameter("userNickname");
			String userPassword = request.getParameter("userPassword");
			String userRole = request.getParameter("userRole");	
			
			dto.setNickname(userNickname);
			dto.setPassword(userPassword);
			dto.setRole(userRole);
			dto.setId(userId);
		
			dao.editUser(dto);		
		}
		else if ("delUser".equals(action)) {
			dao.delUser(userId);
		}
		response.sendRedirect(viewResolver.resolve("community/index"));
	}
}
