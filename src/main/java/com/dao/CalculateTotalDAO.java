package com.dao;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CalculateTotalDAO extends HttpServlet {

	private static final long serialVersionUID = 1L;
	
	//--------------START------get due amount---------------
	/**
	*
	* @author: R Ananthakrishna
	* @Date: 19-07-2023
	* @version : Java JDK 8
	* @purpose :Get total due amount from gas dues table
	* @Exception : IOException
	* @param : request, response
	* @return: Nothing
	*/

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
		
		PrintWriter out=response.getWriter();
		String s=request.getParameter("flId");
		int flId=Integer.parseInt(s);
		
		try{
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/luminar_nesthomes","root","Luminar@2023");
			PreparedStatement pst=conn.prepareStatement("SELECT gd_due_amount FROM gas_dues WHERE fl_id=?");
			pst.setInt(1,flId);
			ResultSet rs=pst.executeQuery();

			while(rs.next()){
				out.print(rs.getDouble(1));
			}
			conn.close();
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	//--------------END------get due amount---------------

}
