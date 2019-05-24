package api;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.websocket.Session;

import org.json.JSONObject;


public class RenewSession extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		PrintWriter outPrintWriter = response.getWriter();
        HttpSession session = request.getSession();
        
        int page = 0;   //initial page number
        int rvs_page = 0;    
        int c_page = 0; 
        System.out.println(session.getAttribute("current_page"));

        if(request.getParameter("page")!="") {
        	c_page = Integer.parseInt(request.getParameter("page"));
        }else {
        	c_page = Integer.parseInt(session.getAttribute("current_page").toString());
        }
     	
        if(request.getParameter("rvs_page")!="") {
            rvs_page = Integer.parseInt(request.getParameter("rvs_page"));
            c_page += rvs_page;    //need to set up lower-bound
        }
        String orderby =  request.getParameter("orderby"); //alphabet or genre or rating or year
        String genre = request.getParameter("genre");     //action 
        String asc = request.getParameter("asc");
        
        session.setAttribute("current_page", Integer.toString(c_page));              //Store changes in session, after reloading html page it will be used in Movie.java
        if(orderby!="") {
        	 session.setAttribute("s_orderby", orderby);
        }
        if(genre!="") {
        	session.setAttribute("s_genre", genre);
        }
        if(asc!="") {
        	session.setAttribute("s_asc", asc);
        }
        
        JSONObject data = new JSONObject();
        data.append("status", "success");
        outPrintWriter.write(data.toString());
	}


	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
