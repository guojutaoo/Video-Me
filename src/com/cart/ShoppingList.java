package com.cart;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONObject;

import com.mysql.jdbc.Statement;


public class ShoppingList extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		response.setContentType("application/json");
		PrintWriter out = response.getWriter();
		
		JSONObject data = new JSONObject();
		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
		    Connection connection = DriverManager.getConnection("jdbc:mysql:///cartdb?autoReconnect=true&useSSL=false",
					"root","guojutao");
		    Statement state = (Statement) connection.createStatement();
		    String sql = "select id, title, director from movie";
		    ResultSet list = state.executeQuery(sql);
		    while(list.next()) {
		    	data.append("id", list.getObject(1));
		    	data.append("title", list.getObject(2));
		    	data.append("director", list.getObject(3));
		    }
		    System.out.println(data);
		} catch (Exception e) {
			e.printStackTrace();
		}
		out.write(data.toString());
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
