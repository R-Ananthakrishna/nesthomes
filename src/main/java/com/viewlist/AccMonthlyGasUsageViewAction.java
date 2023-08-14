package com.viewlist;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.bean.FlatBean;
import com.dao.MonthlyGasUsageDAO;

public class AccMonthlyGasUsageViewAction extends HttpServlet {
	

	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
		
		PrintWriter out=response.getWriter();
		
		HttpSession session=request.getSession(false);
		String uName=(String)session.getAttribute("uName");
		
		response.setHeader("Cache-Control", "max-age=0, no-cache, no-store, must-revalidate, proxy-revalidate");// HTTP 1.1
		response.setHeader("Pragma", "no-cache"); // HTTP 1.0.
		response.setDateHeader("Expires", 0); // Proxies. 
		
		out.print("<!DOCTYPE html>"
				+ "<html lang='en'>"
				+ "<head>"
				+ "    <meta charset='UTF-8'>"
				+ "    <meta http-equiv='X-UA-Compatible' content='IE=edge'>"
				+ "    <meta name='viewport' content='width=device-width, initial-scale=1.0'>"
				+ "    <title>Monthly Gas Usage</title>"
				+ "    <link rel='stylesheet' href='./css/monthly-gas-usage.css' type='text/css'>"
				+ "    <link rel='stylesheet' href='https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css'>"
				+ "    <script src='./js/monthly-gas-usage.js' type='text/javascript'></script>"
				+ "</head>"
				+ "<body>"
				+ "    <nav class='navbar'>"
				+ "        <a href='AccountantHomeView?uName="+uName+"'>Home"
				+ "          <i class='fa fa-home fa-fw'></i>"
				+ "        </a>"
				+ "        <div class='dropdown'>"
				+ "          <button class='dropbtn'>Society Dues"
				+ "          </button>"
				+ "            <div class='dropdown-content'>"
				+ "              <a href='AccMonthlyGasUsageView'>Monthly Gas Usage</a>"
				+ "              <a href='AccPaymentView'>Payment &nbsp;<i class='fa fa-money fa-fw'></i></a>"
				+ "            </div>"
				+ "        </div> "
				+ "        <a id='logout' href='Logout'>Logout"
				+ "          <i class='fa fa-sign-out fa-fw'></i>"
				+ "        </a>"
				+ "      <h2 id='welcome' class='welcome'>Welcome "+uName+"</h2>"
				+ "    </nav>"
				+ "		<div id='form-header'>"
				+ "        <h1>Monthly Gas Usage</h1>"
				+ "    </div>"
				+ "    <div class='monthly-gas'>"
				+ "        <form id='gasUsage' name='gasUsage' method='post' action='AccMonthlyGasUsage'>"
				+ "            <label>Flat</label>"
				+ "            <select id='flat' name='flat'>"
				+ "                <option value='select'>Select-One</option>");
		
		
		ArrayList<FlatBean> flatList =null;
		
		try {

			flatList=MonthlyGasUsageDAO.listOnlyFlat();
					
		}catch(SQLException e) {
			e.printStackTrace();
		}
								
		for(FlatBean f : flatList) {
							
			out.print("           <option value='"+f.getFlId()+"'>"+f.getFlNo()+" "+f.getFlTower()+"</option>");
		}
		
		
		out.print("            </select>"
				+ "            <br><br><br>"
				+ "            <label>Unit</label>"
				+ "            <input type='number' placeholder='Enter gas unit' id='unit' name='unit' min='0' step='0.01'>"
				+ "            <br><br><br><br>"
				+ "            <input type='submit' name='button' id='button' value='Save' onclick='return valForm()'>"
				+ "        </form>"
				+ "    </div>"
				+ "</body>"
				+ "</html>");
		
	}
	
	
	
}