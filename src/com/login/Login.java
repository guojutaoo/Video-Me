package com.login;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String username = request.getParameter("email");
		String psw = request.getParameter("password");
	    String msg = "";
		
		LoginDao handledb = new LoginDao();               //call function which handle data to database
		if(handledb.checkLog(username, psw)) {
			response.sendRedirect("movielist.html");
			return;
		}
		else {
			msg = "Login failed!";
			request.getSession().setAttribute("msg", msg);
			response.sendRedirect("login.html");
			return;
		}
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
