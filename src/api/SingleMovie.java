package api;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLClientInfoException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONObject;

import com.mysql.jdbc.PreparedStatement;
import com.mysql.jdbc.Statement;


public class SingleMovie extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		response.setContentType("application/json");
		HttpSession session = request.getSession();
		PrintWriter out = response.getWriter();
		
		String id = null, method = null, artist = null, moviename = null;
		
		JSONObject data = new JSONObject();
		if(!(session.getAttribute("S_id")==null)) {
			id = session.getAttribute("S_id").toString();			
		}
		if(!(session.getAttribute("S_method")==null)) {
		    method = session.getAttribute("S_method").toString();
		}
		if(!(session.getAttribute("S_artist")==null)) {
		    artist = session.getAttribute("S_artist").toString();
		}
		if(!(session.getAttribute("S_wd")==null)) {
		    moviename = session.getAttribute("S_wd").toString();
		}
		System.out.println("You are in singlemovie.java");
		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
		    Connection connection = DriverManager.getConnection("jdbc:mysql:///moviedb?autoReconnect=true&useSSL=false",
					"root","guojutao");
		  
		    if(moviename!=null) {
		    	 String sql = "select id, title, year, director from movies WHERE title LIKE " + "'" + moviename + "'";
				    Statement stmt =(Statement) connection.createStatement();
				    ResultSet movieResultSet = stmt.executeQuery(sql);
				    while(movieResultSet.next()) {
			    		data.append("id", movieResultSet.getObject(1));
				    	data.append("title", movieResultSet.getObject(2));
				    	data.append("year", movieResultSet.getObject(3));
				    	data.append("director", movieResultSet.getObject(4));
				    	String sql_genre = "select genreId from genres_in_movies where movieId LIKE "+"'" +
				    	movieResultSet.getObject(1) +"'";
			    		Statement state_2 = (Statement) connection.createStatement();
			    		ResultSet genreSet = state_2.executeQuery(sql_genre);
			    		System.out.println("next = "+genreSet.next());
			    		if(!genreSet.next()) {
			    			data.append("genre", "No Info");
			    		}
			    		while(genreSet.next()) {
			    			if(genreSet.getObject(1)!="") {
			    				String sql_get_genre = "select name from genres WHERE id LIKE"+ "'" + 
			    			genreSet.getObject(1) +"'";
			    				Statement state_3 = (Statement) connection.createStatement();
			    				ResultSet result_get_genre = state_3.executeQuery(sql_get_genre);
			    				while(result_get_genre.next()) {
			    					data.append("genre", result_get_genre.getObject(1));
			    					}
			    				}
			    			}
			    		}
				    session.setAttribute("S_wd", null);
		    }
		    
		    Statement state_1 = (Statement) connection.createStatement();
		    if(method.equals("1")) {
		    	String sql = "select id, title, year, director from movies WHERE id LIKE " + "'" + id +"'";
		    	ResultSet movieSet = state_1.executeQuery(sql);
		    	while(movieSet.next()) {
		    		data.append("id", movieSet.getObject(1));
			    	data.append("title", movieSet.getObject(2));
			    	data.append("year", movieSet.getObject(3));
			    	data.append("director", movieSet.getObject(4));
			    	String sql_genre = "select genreId from genres_in_movies where movieId LIKE "+"'" +
			    	movieSet.getObject(1) +"'";
		    		Statement state_2 = (Statement) connection.createStatement();
		    		ResultSet genreSet = state_2.executeQuery(sql_genre);
		    		System.out.println("next = "+genreSet.next());
		    		if(!genreSet.next()) {
		    			data.append("genre", "No Info");
		    		}
		    		while(genreSet.next()) {
		    			if(genreSet.getObject(1)!="") {
		    				String sql_get_genre = "select name from genres WHERE id LIKE"+ "'" + 
		    			genreSet.getObject(1) +"'";
		    				Statement state_3 = (Statement) connection.createStatement();
		    				ResultSet result_get_genre = state_3.executeQuery(sql_get_genre);
		    				while(result_get_genre.next()) {
		    					data.append("genre", result_get_genre.getObject(1));
		    				    }
		    				}
		    			
		    			}
		    		}
		    	}
		    if(method.equals("0")) {
		    	String sql =  "select director from movies where id LIKE " + "'" + id +"'";
		    	data.append("id", id);
		    	System.out.println("id = "+ id);
		    	ResultSet result_name = state_1.executeQuery(sql);
		    	while(result_name.next()) {
		    		String sql_artist = "select id, name, birthyear from stars WHERE name LIKE"+ "'" +
		    	result_name.getObject(1) + "'";
		    		Statement state_2 = (Statement) connection.createStatement();
		    		ResultSet result_info = state_2.executeQuery(sql_artist);
		    		while(result_info.next()) {
		    			System.out.println(result_info.getObject(1).toString());
		    			data.append("artist_id", result_info.getObject(1));
		    			data.append("artist", result_info.getObject(2));
		    			data.append("birthyear", result_info.getObject(3));
		    		}
		    	}		    			
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
