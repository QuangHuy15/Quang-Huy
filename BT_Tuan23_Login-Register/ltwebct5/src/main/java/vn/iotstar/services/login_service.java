package vn.iotstar.services;

import java.io.IOException;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import vn.iotstar.services.impl.UserService;

@WebServlet(urlPatterns = { "/home", "/trangchu" })
public class login_service extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		resp.setContentType("text/html");
		String user = req.getParameter("user_name");
		String psw = req.getParameter("password");
		UserService UserSer = new UserService();
		String inform = UserSer.login(user, psw);
		req.setAttribute("inform", inform);
		RequestDispatcher rd = req.getRequestDispatcher("/view/login.jsp");
		rd.forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(req, resp);
	}
}
