package com.viewlist;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class AdminHomeView extends HttpServlet {

	private static final long serialVersionUID = 1L;
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
		
		PrintWriter out=response.getWriter();
		
		String uName=request.getParameter("uName");
		
		response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");// HTTP 1.1. 
		response.setHeader("Pragma", "no-cache"); // HTTP 1.0.
		response.setDateHeader("Expires", 0); // Proxies.
		
		
		HttpSession session=request.getSession();
		session.setAttribute("uName",uName);
		  
		 
		
		out.print("<!DOCTYPE html>"
				+ "<html lang='en'>"
				+ "<head>"
				+ "    <meta charset='UTF-8'>"
				+ "    <meta http-equiv='X-UA-Compatible' content='IE=edge'>"
				+ "    <meta name='viewport' content='width=device-width, initial-scale=1.0'>"
				+ "    <title>Home-Admin</title>"
				+ "    <link rel='stylesheet' type='text/css' href='./css/adminstyle.css'>"
				+ "    <link rel='stylesheet' href='https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css'>"
				+ "</head>"
				+ "<body>"
				+ "  "
				+ "    <nav class='navbar'>"
				+ "      "
				+ "      <a href='AdminHomeView?uName="+uName+"'>Home"
				+ "        <i class='fa fa-home fa-fw'></i>"
				+ "      </a>"
				+ "      <div class='dropdown'>"
				+ "        <button class='dropbtn'>Owner "
				+ "        </button>"
				+ "        <div class='dropdown-content'>"
				+ "          <a href='OwnerDetailsView'>Owner Details</a>"
				+ "          <a href='OwnershipView'>Ownership</a>"
				+ "        </div>"
				+ "      </div>"
				+ "      <div class='dropdown'>"
				+ "        <button class='dropbtn'>Tenant "
				+ "        </button>"
				+ "        <div class='dropdown-content'>"
				+ "          <a href='TenantDetailsView'>Tenant Details</a>"
				+ "          <a href='RentalView'>Rental</a>"
				+ "        </div>"
				+ "      </div>"
				+ "      <a href='FacilityManagerView'>Facility Manager</a>"
				+ "      <a href='DailyHelpView'>Daily Help</a>"
				+ "      <div class='dropdown'>"
				+ "        <button class='dropbtn'>Society Dues "
				+ "        </button>"
				+ "          <div class='dropdown-content'>"
				+ "            <a href='MonthlyGasUsageView'>Monthly Gas Usage</a>"
				+ "            <a href='PaymentView'>Payment &nbsp;<i class='fa fa-money fa-fw'></i></a>"
				+ "          </div>"
				+ "      </div> "
				+ "      <div class='dropdown'>"
				+ "        <button class='dropbtn'>Settings"
				+ "          <i class='fa fa-cog fa-fw'></i>"
				+ "        </button>"
				+ "        <div class='dropdown-content'>"
				+ "          <a href='UserDetailsView'>User&nbsp;<i class='fa fa-user fa-fw'></i></a>"
				+ "          <a href='FlatView'>Flats&nbsp;<i class='fa fa-building fa-fw'></i></a>"
				+ "          <a href='DailyHelpCategoryView'>Daily Help Category</a>"
				+ "          <a href='GasUnitRateView'>Gas Unit Rate</a>"
				+ "        </div>"
				+ "      </div>"
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
