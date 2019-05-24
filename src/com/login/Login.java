package com.login;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import functions.LoginFunction;


public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		HttpSession session = request.getSession();
		
		String username = request.getParameter("email");
		String psw = request.getParameter("password");
		String recaptcha = request.getParameter("recaptcha");
	    String msg = "";

		LoginFunction handledb = new LoginFunction();               //call function which handle data to database
		if(handledb.checkLog(username, psw)&&recaptcha.equals("true")) {
			session.setAttribute("login", "True");
			response.sendRedirect("movielist.html");
			return;
		}
		else {
			msg = "Login failed!";
			session.setAttribute("msg", msg);
			session.setAttribute("login", "False");
			response.sendRedirect("login.html");
			return;
		}
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
