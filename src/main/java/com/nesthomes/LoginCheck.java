package com.nesthomes;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoginCheck extends HttpServlet {

	private static final long serialVersionUID = 1L;
	
	final String driver="com.mysql.cj.jdbc.Driver";
	final String url="jdbc:mysql://localhost:3306/luminar_nesthomes";
	final String user="root";
	final String password="Luminar@2023";
	
	Connection conn=null;
	PreparedStatement pst=null;
	ResultSet rs=null;
	RequestDispatcher dis=null;
	
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException,IOException {
	
		try {
			
			
			response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");// HTTP 1.1. 
			response.setHeader("Pragma", "no-cache"); // HTTP 1.0.
			response.setDateHeader("Expires", 0); // Proxies.
			 
			
			Class.forName(driver);
			conn=DriverManager.getConnection(url,user,password);
			String uName=request.getParameter("uName");
			String pass=request.getParameter("pass");
			String uType="";
			
			boolean flag=false;
			
			pst=conn.prepareStatement("SELECT * FROM user_login WHERE u_username=? and u_password=?");
			pst.setString(1, uName);
			pst.setString(2, pass);
			rs=pst.executeQuery();
			
			response.setContentType("text/html");
			
			while(rs.next()) {
				
				uType=rs.getString(4);
				
				flag=true;
			}
			
			if(flag) {
				
				if(uType.equalsIgnoreCase("ADMIN")) {
					response.sendRedirect("AdminHomeView?uName="+uName+" ");
				}else if(uType.equalsIgnoreCase("ACCOUNTANT")) {
					response.sendRedirect("AccountantHomeView?uName="+uName+" ");
				}
				
			}else {
				response.sendRedirect("index.html");
				
			}
			conn.close();
			
		}catch(ClassNotFoundException e) {
			e.printStackTrace();
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}
	
}
