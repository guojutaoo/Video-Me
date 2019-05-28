package session;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.swing.plaf.basic.BasicInternalFrameTitlePane.MoveAction;


public class SingleMovieSession extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    
	    String method = request.getParameter("search");
	    String id = request.getParameter("id");
	    String artist = request.getParameter("artist");
	    String genre = request.getParameter("genre");
            String moviename = request.getParameter("wd");
	    
	    HttpSession session = request.getSession();
	    
	    session.setAttribute("S_id", id);
	    session.setAttribute("S_method", method);
	    session.setAttribute("S_artist", artist);
	    session.setAttribute("S_genre", genre);
	    session.setAttribute("S_wd", moviename);
	    System.out.println("moviename = "+ moviename);
	    System.out.println("method = "+ method);

	    if(method.equals("1")||method.equals("2")) {         //redirect to different pages depending on method
		    response.sendRedirect("singlemovie.html");	    	
	    }else if(method.equals("0")) {
	    	response.sendRedirect("artist.html");
	    }else if(method.equals("-1")) {
	    	response.sendRedirect("movielist.html");
	    }

	}


	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		doGet(request, response);
	}

}
