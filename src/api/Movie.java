package api;

import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Type;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.naming.spi.DirStateFactory.Result;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.eclipse.jdt.internal.compiler.ast.AND_AND_Expression;
import org.json.JSONArray;
import org.json.JSONObject;

import com.mysql.jdbc.Statement;


public class Movie extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("you are in Movie.java");
		
		response.setContentType("application/json");
		
		HttpSession session = request.getSession();

		String current_page = (String) session.getAttribute("current_page");  //Get changed attributes from Session
		String orderby = (String) session.getAttribute("s_orderby");
		String genre = (String) session.getAttribute("s_genre");
		String asc =  (String) session.getAttribute("s_asc");
		System.out.println("asc="+asc+"orderby="+orderby);
		if(asc==null) {
			asc = "0";
		}
		if(current_page==null) {               //initialize the variables 
			current_page = "1";
			session.setAttribute("current_page", current_page);
		}
		if(orderby==null) {
			orderby = "all";
		}
		if(genre==null||genre.equals("0")) {
			genre = "all";
		}
		
		int offset = (Integer.parseInt(current_page) - 1) * 20;   //items will be shown

		JSONObject Json_data = new JSONObject();     //create JSONobject
		
		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
		    Connection connection = DriverManager.getConnection("jdbc:mysql:///moviedb?autoReconnect=true&useSSL=false",
					"root","guojutao");
		    Statement state = (Statement) connection.createStatement();
		    
		    String sql;
		  
		if(genre.equals("all")) {                  //check genre name in session
	    if(orderby.equals("rating")) {             //check orderby name in session
	    	if(asc.equals("1")) {                  //check display order in session
	    		sql = "select movieId, rating from ratings ORDER BY rating ASC LIMIT 20 OFFSET " + offset;
	    	}else{
	    		sql = "select movieId, rating from ratings ORDER BY rating DESC LIMIT 20 OFFSET " + offset;
	    	}
	    	ResultSet result_rating = state.executeQuery(sql);
	    	while(result_rating.next()) {
	    		Json_data.append("movie_id", result_rating.getObject(1));
	    		Json_data.append("rating", result_rating.getObject(2));
	    		String sql_add_rating = "select id, title, year, director from movies where id LIKE "+'"'+result_rating.getObject(1)+'"';
	    		Statement statement_add = (Statement) connection.createStatement();
	    		ResultSet result_add = statement_add.executeQuery(sql_add_rating);
	    		while(result_add.next()) {
			    	Json_data.append("movie_id", result_add.getObject(1));
			    	Json_data.append("name", result_add.getObject(2));
			    	Json_data.append("year", result_add.getObject(3));
			    	Json_data.append("director", result_add.getObject(4));	    
			    	
			    	}
	    		statement_add.close();
	    		}
	    	state.close();
	    }else if(orderby.equals("year")) {
	    	if(asc.equals("1")) {
	    		sql = "select id, title, year, director from movies ORDER BY year ASC LIMIT 20 OFFSET " + offset;
	    	}else{
	    	    sql = "select id, title, year, director from movies ORDER BY year DESC LIMIT 20 OFFSET "  + offset;
	    	}
		    ResultSet result_year = state.executeQuery(sql);
		    System.out.println("year:" + sql);
		    
		    while(result_year.next()) {
		    	Json_data.append("movie_id", result_year.getObject(1));
		    	Json_data.append("name", result_year.getObject(2));
		    	Json_data.append("year", result_year.getObject(3));
		    	Json_data.append("director", result_year.getObject(4));
		    	String sql_add_year = "select rating from ratings where movieId like " + '"' + result_year.getObject(1)+'"';
			    Statement state_rating = (Statement) connection.createStatement();
			    ResultSet result_add = state_rating.executeQuery(sql_add_year);
			    while(result_add.next()) {
			    	Json_data.append("rating",result_add.getObject(1));
			    }
			    state_rating.close();
		    }
		    state.close();
	    }else {
	    	    sql = "select id, title, year, director from movies LIMIT 20 OFFSET " + offset;
	    	    ResultSet result_common = state.executeQuery(sql);
		    	System.out.println("general:" + sql);
	    	    while(result_common.next()) {
	    	    	Json_data.append("movie_id", result_common.getObject(1));
			    	Json_data.append("name", result_common.getObject(2));
			    	Json_data.append("year", result_common.getObject(3));
			    	Json_data.append("director", result_common.getObject(4));
			    	String sql_add_year = "select rating from ratings where movieId like " + "'" + result_common.getObject(1) + "'";
			    	Statement state_rating = (Statement) connection.createStatement();
				    ResultSet result_add = state_rating.executeQuery(sql_add_year);
				    while(result_add.next()) {
				    	Json_data.append("rating", result_add.getObject(1));
				    }
				    state_rating.close();
	    	    }
	    	    }}
	    
		if(!genre.equals("all")) {
			sql = "select movieId from genres_in_movies WHERE genreId LIKE " + genre ;
			ResultSet genreSet = state.executeQuery(sql);
			while(genreSet.next()) {
				Json_data.append("movie_id", genreSet.getObject(1));
				String sql_add_genre = "select id, title, year, director from movies where id LIKE "+'"'+genreSet.getObject(1)+'"';
	    		Statement statement_add = (Statement) connection.createStatement();
	    		ResultSet result_add = statement_add.executeQuery(sql_add_genre);
	    		while(result_add.next()) {
			    	Json_data.append("name", result_add.getObject(2));
			    	Json_data.append("year", result_add.getObject(3));
			    	Json_data.append("director", result_add.getObject(4));
			    	String sql_add_year = "select rating from ratings where movieId like " + '"' + result_add.getObject(1)+'"';
				    Statement state_rating = (Statement) connection.createStatement();
				    ResultSet result_add_rating = state_rating.executeQuery(sql_add_year);
				    while(result_add_rating.next()) {
				    	Json_data.append("rating",result_add_rating.getObject(1));
				    }
				    state_rating.close();
	    		}
			}
		}
	        
		    PrintWriter out = response.getWriter();
		    out.write(Json_data.toString());
		} catch (Exception e) {

			e.printStackTrace();
		}

	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		doGet(request, response);
	}

}

