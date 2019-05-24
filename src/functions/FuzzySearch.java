package functions;

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


public class FuzzySearch extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setContentType("application/json");
		PrintWriter out = response.getWriter();
		
		JSONObject data = new JSONObject();

		String keyword = (String) request.getParameter("kwd");
		
		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
		    Connection connection = DriverManager.getConnection("jdbc:mysql:///moviedb?autoReconnect=true&useSSL=false",
					"root","guojutao");
		    Statement state = (Statement) connection.createStatement();
		    String sql = "select id, title, year from movies WHERE title LIKE '%" + keyword + "%' LIMIT 5";
		    
		    ResultSet result = state.executeQuery(sql);
		    
		    while(result.next()) {
		    	data.append("id", result.getObject(1));
		    	data.append("title", result.getObject(2));
		    	data.append("year", result.getObject(3));
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
