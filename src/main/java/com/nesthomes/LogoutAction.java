package com.nesthomes;

import java.io.IOException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LogoutAction extends HttpServlet {

	private static final long serialVersionUID = 1L;
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
		
		HttpSession session = request.getSession(false);
		
		response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");// HTTP 1.1. 
		response.setHeader("Pragma", "no-cache"); // HTTP 1.0.
		response.setDateHeader("Expires", 0); // Proxies.
		
		if(session!=null) {
			
			session.invalidate();
			
			response.sendRedirect("index.html");
		}else {
			
			response.sendRedirect("error.html");
		}
		
		
		
	}

}
