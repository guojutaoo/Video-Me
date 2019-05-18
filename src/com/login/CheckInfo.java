package com.login;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.login.LoginDao;


public class CheckInfo extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		
		String Username = request.getParameter("username");
		String psw1 = request.getParameter("psw1");
		String psw2 = request.getParameter("psw2");
		String msg="";
		
		session.setAttribute("Username", Username);
		session.setAttribute("Password", psw1);
		
		if(Username==""||Username==null||Username.length()>10) {
			msg = "Invalid Username!";
			request.getSession().setAttribute("msg", msg);
			System.out.println(msg);
			response.sendRedirect("register.html");
			return;
		}
		if(psw1==""||psw1==null||psw1.length()>10||psw1.length()<3) {
			msg = "Invalid Password!";
			request.getSession().setAttribute("msg", msg);
			System.out.println(msg);
			response.sendRedirect("register.html");
			return;
		}
		if(!psw1.equals(psw2)) {
			msg = "Your passwords should be the same!";
			request.getSession().setAttribute("msg", msg);
			System.out.println(msg);
			response.sendRedirect("register.html");
			return;
		}
		LoginDao handledb = new LoginDao();
		if(handledb.checkReg(Username)) {
			response.sendRedirect("register.html");
		}
        handledb.addUser(Username, psw1);
        response.sendRedirect("login.html");
	      
	}

}
