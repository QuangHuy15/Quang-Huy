package vn.iotstar.services;

import java.io.IOException;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import vn.iotstar.dao.impl.UserDaoImpl;
import vn.iotstar.models.UsersModel;

@WebServlet(urlPatterns = { "/Register", "/dangki" })
public class register_service extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		resp.setContentType("text/html");
		String user = req.getParameter("user");
		String fullname = req.getParameter("fullname");
		String email = req.getParameter("email");
		String password = req.getParameter("psw");
		String alertMsg = "";
		RequestDispatcher rd;

		UserDaoImpl userDao = new UserDaoImpl();
		if (userDao.CheckUserExist(user) == true) {
			alertMsg = "Username exist! Please choose another Username.";
			req.setAttribute("alert", alertMsg);
			rd = req.getRequestDispatcher("/view/register.jsp");
			rd.forward(req, resp);
			return;

		}

		if (userDao.CheckEmailExist(email) == true) {
			alertMsg = "Email exist! Please choose another Email.";
			req.setAttribute("alert", alertMsg);
			rd = req.getRequestDispatcher("/view/register.jsp");
			rd.forward(req, resp);
			return;
		}

		userDao.insert(new UsersModel(user, email, password, fullname, "null"));
		rd = req.getRequestDispatcher("/view/form_login.html");
		rd.forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(req, resp);
	}

}
