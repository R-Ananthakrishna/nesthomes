package com.update;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.bean.UserDetailsBean;
import com.dao.UserDetailsDAO;

public class UserDetailsUpdate extends HttpServlet {

	private static final long serialVersionUID = 1L;
	
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException{
		
		PrintWriter out=response.getWriter();
		
		HttpSession session=request.getSession(false);
		String uName=(String)session.getAttribute("uName");
		
		int uId=Integer.parseInt(request.getParameter("uId"));
		
		ArrayList<UserDetailsBean> userDetailsFetch =null;
		
		try {
			
			userDetailsFetch=UserDetailsDAO.fetchUserById(uId);
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
		
		
		out.print("<!DOCTYPE html>"
				+ "<html lang='en'>"
				+ "<head>"
				+ "    <meta charset='UTF-8'>"
				+ "    <meta http-equiv='X-UA-Compatible' content='IE=edge'>"
				+ "    <meta name='viewport' content='width=device-width, initial-scale=1.0'>"
				+ "    <title>User Details Update</title>"
				+ "    <link rel='stylesheet' href='./css/user-details.css' type='text/css'>"
				+ "    <link rel='stylesheet' href='https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css'>"
				+ "    <script src='./js/user-details.js' type='text/javascript'></script>"
				+ "</head>"
				+ "<body>"
				+ "    <nav class='navbar'>"
				+ "        <a href='AdminHomeView?uName="+uName+"'>Home"
				+ "          <i class='fa fa-home fa-fw'></i>"
				+ "        </a>"
				+ "        <div class='dropdown'>"
				+ "          <button class='dropbtn'>Owner"
				+ "          </button>"
				+ "          <div class='dropdown-content'>"
				+ "            <a href='OwnerDetailsView'>Owner Details</a>"
				+ "            <a href='OwnershipView'>Ownership</a>"
				+ "          </div>"
				+ "        </div>"
				+ "        <div class='dropdown'>"
				+ "          <button class='dropbtn'>Tenant"
				+ "          </button>"
				+ "          <div class='dropdown-content'>"
				+ "            <a href='TenantDetailsView'>Tenant Details</a>"
				+ "            <a href='RentalView'>Rental</a>"
				+ "          </div>"
				+ "        </div>"
				+ "        <a href='FacilityManagerView'>Facility Manager</a>"
				+ "        <a href='DailyHelpView'>Daily Help</a>"
				+ "        <div class='dropdown'>"
				+ "          <button class='dropbtn'>Society Dues"
				+ "          </button>"
				+ "            <div class='dropdown-content'>"
				+ "              <a href='MonthlyGasUsageView'>Monthly Gas Usage</a>"
				+ "              <a href='PaymentView'>Payment &nbsp;<i class='fa fa-money fa-fw'></i></a>"
				+ "            </div>"
				+ "        </div> "
				+ "        <div class='dropdown'>"
				+ "          <button class='dropbtn'>Settings"
				+ "            <i class='fa fa-cog fa-fw'></i>"
				+ "          </button>"
				+ "          <div class='dropdown-content'>"
				+ "            <a href='UserDetailsView'>User&nbsp;<i class='fa fa-user fa-fw'></i></a>"
				+ "            <a href='FlatView'>Flats&nbsp;<i class='fa fa-building fa-fw'></i></a>"
				+ "            <a href='DailyHelpCategoryView'>Daily Help Category</a>"
				+ "            <a href='GasUnitRateView'>Gas Unit Rate</a>"
				+ "          </div>"
				+ "        </div>"
				+ "        <a id='logout' href='index.html'>Logout"
				+ "          <i class='fa fa-sign-out fa-fw'></i>"
				+ "        </a>"
				+ "      <h2 id='welcome' class='welcome'>Welcome "+uName+"</h2>"
				+ "    </nav>"
				+ "	   <div id='form-header'>"
				+ "        <h1>User Details</h1>"
				+ "    </div>"
				+ "    <div class='user-details'>"
				+ "        <form id='user' name='user' method='post' action='UserDetailsUpdateAction'>");
		
		for(UserDetailsBean user: userDetailsFetch) {
			
			out.print("            <input type='hidden' id='uId' name='uId' value='"+user.getuId()+"'><br>"
					+ "			   <label>User Name</label>"
					+ "            <input type='text' id='uName' name='uName' value='"+user.getuName()+"'>"
					+ "            <br><br><br>"
					+ "            <label>Password</label>"
					+ "            <input type='text' id='pass' name='pass' value='"+user.getPass()+"'>"
					+ "            <br><br><br>"
					+ "            <label>User Type</label>"
					+ "            <select id='uType' name='uType'>"
					+ "                <option value='select'>Select-One</option>"
					+ "                <option value='ADMIN'>Admin</option>"
					+ "                <option value='ACCOUNTANT'>Accountant</option>"
					+ "            </select>"
					+ "            <br><br><br><br>"
					+ "            <input type='submit' name='button' id='button' value='Update' onclick='return valForm()'>");
		}
		
		
		out.print("        </form>"
				+ "    </div>"
				+ "	   <table>"
				+ "      <tr>"
				+ "        <th>Sl No</th>"
				+ "        <th>User Name</th>"
				+ "		   <th>Password</th>"
				+ "		   <th>User Type</th>"
				+ "        <th>Edit</th>"
				+ "        <th>Delete</th>"
				+ "      </tr>");
		
		int i=1;
		
		ArrayList<UserDetailsBean> userDetails =null;
		
		try {
			
			userDetails=UserDetailsDAO.listUser();
		}catch(SQLException e) {
			e.printStackTrace();
		}
		
		
		for(UserDetailsBean user: userDetails) {
			
			out.print("<tr>"
					+ "	<td>"+i+"</td>"
					+ " <td>"+user.getuName()+"</td>"
					+ " <td>"+user.getPass()+"</td>"
					+ " <td>"+user.getuType()+"</td>"
					+ "	<td><a href='UserDetailsUpdate?uId="+user.getuId()+"'>Edit</a></td>"
					+ "	<td><a href='UserDetailsDelete?uId="+user.getuId()+"'>Delete</a></td>"
					+ "</tr>");
			
			i++;
		}
		
		out.print("</table>"
				+ "</body>"
				+ "</html>");
		
		
		
	}
		

}
