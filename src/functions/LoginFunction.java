package functions;


import java.sql.*;

import javax.security.auth.message.callback.PrivateKeyCallback.Request;
import javax.servlet.http.HttpSession;

import com.mysql.fabric.Response;


public class LoginFunction {

	public void deleteUser(String name) {
		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
		    Connection connection = DriverManager.getConnection("jdbc:mysql:///userdb?autoReconnect=true&useSSL=false",
		    		"root", "guojutao");
		    Statement stmt = connection.createStatement();
		    int retID = stmt.executeUpdate("delete from userdata where id = 'name'"); //error
		    } catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public boolean checkReg(String username) {
		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			Connection connection = DriverManager.getConnection("jdbc:mysql:///userdb?autoReconnect=true&useSSL=false",
					"root", "guojutao");
			Statement select = connection.createStatement();
			ResultSet result = select.executeQuery("select * from userdata");
			
			while(result.next()) {
				if(username.equals(result.getObject("id"))) {
					System.out.println("Account already exist!");
					return true;
				}
			}
			
			}catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public void addUser(String username, String password) {
		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			Connection connection = DriverManager.getConnection("jdbc:mysql:///userdb?autoReconnect=true&useSSL=false",
						"root", "guojutao");
			Statement stmt = connection.createStatement();
				
		        String sql = "insert into userdata (id, psw) values('"+username+"', '"+password+"')";
		        System.out.println(sql);
		        stmt.executeUpdate(sql);
		        
		        stmt.close();
				System.out.println("Successfully");
			    }catch (Exception e) {
				e.printStackTrace();
			}
	}

	public boolean checkLog(String username, String password){
		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			Connection connection = DriverManager.getConnection("jdbc:mysql:///userdb?autoReconnect=true&useSSL=false",
							"root", "guojutao");
			Statement select = connection.createStatement();
			ResultSet result = select.executeQuery("select * from userdata");
			while(result.next()) {
				if(username.equals(result.getObject("id"))) {
					if(password.equals(result.getObject("psw"))){
						return true;
					}
					else {
						return false;
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
			return false;
	}

}
