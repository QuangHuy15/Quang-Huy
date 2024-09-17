package vniot.star.controllers;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import vniot.star.dao.impl.UserDaoImpl;
import vniot.star.models.UserModel;

@WebServlet(urlPatterns = { "/forget" })
public class ForgetController extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	UserDaoImpl userDao = new UserDaoImpl();
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		resp.setContentType("text/html");
		resp.setCharacterEncoding("UTF-8");
		req.setCharacterEncoding("UTF-8");

		String username = req.getParameter("username");
		String email = req.getParameter("email");
		String alertMsg = "";

		UserModel oject = userDao.findByUsername(username);

		if ((userDao.CheckEmailDuplicate(oject.getEmail(), email) == false) || (oject == null)) {
			alertMsg = "User hoặc Email chưa chính xác";
			req.setAttribute("alert", alertMsg);
			req.getRequestDispatcher("/views/forget.jsp").forward(req, resp);
			return;
		}
		HttpSession session = req.getSession(true);
		session.setAttribute("account", oject);
		req.getRequestDispatcher("/views/newpass.jsp").forward(req, resp);

	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		resp.setContentType("text/html");
		resp.setCharacterEncoding("UTF-8");
		req.setCharacterEncoding("UTF-8");
		
		HttpSession session = req.getSession();
		UserModel user = (UserModel) session.getAttribute("account");
		String username = user.getUsername();
		String password = req.getParameter("password");
		userDao.UpdatePassword(username,password);
		req.getRequestDispatcher("/views/login.jsp").forward(req, resp);
	}

}
