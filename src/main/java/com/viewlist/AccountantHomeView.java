package com.viewlist;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class AccountantHomeView extends HttpServlet {

	private static final long serialVersionUID = 1L;
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
		
		PrintWriter out=response.getWriter();
		
		String uName=request.getParameter("uName");
		
		HttpSession session=request.getSession();
		session.setAttribute("uName",uName);
		  
		response.setHeader("Cache-Control", "max-age=0, no-cache, no-store, must-revalidate, proxy-revalidate");// HTTP 1.1
		response.setHeader("Pragma", "no-cache"); // HTTP 1.0.
		response.setDateHeader("Expires", 0); // Proxies. 
		
		out.print("<!DOCTYPE html>"
				+ "<html lang='en'>"
				+ "<head>"
				+ "    <meta charset='UTF-8'>"
				+ "    <meta http-equiv='X-UA-Compatible' content='IE=edge'>"
				+ "    <meta name='viewport' content='width=device-width, initial-scale=1.0'>"
				+ "    <meta http-equiv='cache-control' content='max-age=0'>"
				+ "    <meta http-equiv='cache-control' content='no-cache'>"
				+ "    <meta http-equiv='cache-control' content='no-store'>"
				+ "    <meta http-equiv='cache-control' content='must-revalidate'>"
				+ "    <meta http-equiv='expires' content='0'>"
				+ "	   <meta http-equiv='pragma' content='no-cache'>"
				+ "    <title>Home-Accountant</title>"
				+ "    <link rel='stylesheet' type='text/css' href='./css/accountants.css'>"
				+ "    <link rel='stylesheet' href='https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css'>"
				+ "</head>"
				+ "<body>"
				+ "  "
				+ "    <nav class='navbar'>"
				+ "      "
				+ "      <a href='AccountantHomeView?uName="+uName+"'>Home"
				+ "        <i class='fa fa-home fa-fw'></i>"
				+ "      </a>"
				+ "      <div class='dropdown'>"
				+ "        <button class='dropbtn'>Society Dues "
				+ "        </button>"
				+ "          <div class='dropdown-content'>"
				+ "            <a href='AccMonthlyGasUsageView'>Monthly Gas Usage</a>"
				+ "            <a href='AccPaymentView'>Payment &nbsp;<i class='fa fa-money fa-fw'></i></a>"
				+ "          </div>"
				+ "      </div> "
				+ "      <a id='logout' href='Logout'>Logout"
				+ "        <i class='fa fa-sign-out fa-fw'></i>"
				+ "      </a>"
				+ "      <h2 id='welcome' class='welcome'>Welcome "+uName+"</h2>"
				+ "    </nav>"
				+ "    <h1>"
				+ "      <span>Nest Homes </span>Home is where happiness is!"
				+ "    </h1>"
				+ "    <img src='images/bgimage_7.jpg'>"
				+ "</body>"
				+ "</html>");
		
		
	}

}
