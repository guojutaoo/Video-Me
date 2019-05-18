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

import com.mysql.fabric.Response;
import com.mysql.jdbc.PreparedStatement;
import com.mysql.jdbc.Statement;


public class CheckOut extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		response.setContentType("application/json");
		PrintWriter out = response.getWriter();
		
		String email = request.getParameter("email");
		String psw = request.getParameter("psw");
		
		JSONObject data = new JSONObject();
		
		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
		    Connection connection = DriverManager.getConnection("jdbc:mysql:///moviedb?autoReconnect=true&useSSL=false",
					"root","guojutao");
		    String sql = "select password from customers WHERE email = ?";
		    PreparedStatement stmt = (PreparedStatement) connection.prepareStatement(sql);
		    stmt.setString(1, email);
		    ResultSet pswResultSet = stmt.executeQuery();
		    if(pswResultSet.next()) {
		    	if(pswResultSet.getObject(1).equals(psw)) {
		    		data.append("status", "success");
		    	}else {
		    		data.append("status", "fail");
		    	}
		    }else {
		    	data.append("status", "fail");
		    }
		    out.write(data.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		doGet(request, response);
	}

}
