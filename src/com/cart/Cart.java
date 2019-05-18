package com.cart;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONObject;

import com.mysql.jdbc.Statement;


public class Cart extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
				
		String method = request.getParameter("method").toString();
		String movieid = request.getParameter("id").toString();
		String title = request.getParameter("title").toString();
		String director = request.getParameter("director").toString();
		
		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
		    Connection connection = DriverManager.getConnection("jdbc:mysql:///cartdb?autoReconnect=true&useSSL=false",
					"root","guojutao");
		    Statement state_1 = (Statement) connection.createStatement();
		    if(method.equals("1")) {
		    	String sql = "insert into movie (id, title, director) values('" + movieid + "', '" + title + "', '" + director + "')";
		    	state_1.executeUpdate(sql);
		    }else if(method.equals("-1")) {
		    	String sql = "delete from movie WHERE id LIKE " + "'" + movieid + "'";
		    	state_1.executeUpdate(sql);
		    }else if(method.equals("0")) {
		    	String sql = "TRUNCATE TABLE movie";
		    	state_1.execute(sql);
		    }
		    response.sendRedirect("cart.html");
		    state_1.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		doGet(request, response);
	}

}
