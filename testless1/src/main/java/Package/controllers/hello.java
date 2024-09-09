package Package.controllers;

import java.io.IOException;
import java.io.PrintWriter;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = {"/home", "/trangchu"})
public class hello extends HttpServlet {
	
	private static final long serialVersionUID = 148295057574120606L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		resp.setContentType("text/html");
		String ten1 = req.getParameter("ten");
		String ho1 = req.getParameter("ho");
		
		//dua du lieu ra view (web)
		req.setAttribute("fname", ten1);
		req.setAttribute("lname", ho1);
		
		
//		RequestDispatcher dispatcher = req.getRequestDispatcher("webapp/web/home.html");
//		dispatcher.forward(req, resp);
		PrintWriter printW = resp.getWriter();
		printW.println(ho1+ " "+ten1);
		printW.close();
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		super.doPost(req, resp);
	}

}