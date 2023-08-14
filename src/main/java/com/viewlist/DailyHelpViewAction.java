package com.viewlist;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.bean.DailyHelpBean;
import com.bean.DailyHelpCategoryBean;
import com.dao.DailyHelpDAO;

public class DailyHelpViewAction extends HttpServlet {
	

	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
		
		PrintWriter out=response.getWriter();
		
		HttpSession session=request.getSession(false);
		String uName=(String)session.getAttribute("uName");
	
		out.print("<!DOCTYPE html>"
				+ "<html lang='en'>"
				+ "<head>"
				+ "    <meta charset='UTF-8'>"
				+ "    <meta http-equiv='X-UA-Compatible' content='IE=edge'>"
				+ "    <meta name='viewport' content='width=device-width, initial-scale=1.0'>"
				+ "    <title>Daily Help</title>"
				+ "    <link rel='stylesheet' href='./css/dailyhelp.css' type='text/css'>"
				+ "    <link rel='stylesheet' href='https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css'>"
				+ "	   <script src='./js/daily-help.js' type='text/javascript'></script>"
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
				+ "    <div id='form-header'>"
				+ "        <h1>Daily-Help</h1>"
				+ "    </div>"
				+ "    <div class='daily-help'>"
				+ "        <form id='dHelp' name='dHelp' method='post' action='DailyHelp'>"
				+ "            <label>Name</label>"
				+ "            <input type='text' placeholder='Enter the name' id='uName' name='uName'>"
				+ "            <br><br><br>"
				+ "            <label>Category</label>"
				+ "            <select id='category' name='category'>"
				+ "                <option value='select'>Select-One</option>");
				
		ArrayList<DailyHelpCategoryBean> categoryList =null;
				
		try {

			categoryList=DailyHelpDAO.listOnlyCategory();
					
		}catch(SQLException e) {
			e.printStackTrace();
		}
				
		for(DailyHelpCategoryBean dhc : categoryList) {
			
			out.print("                <option value='"+dhc.getDhcCategory()+"'>"+dhc.getDhcCategory()+"</option>");
		}
				
				
		
		out.print("            </select>"
				+ "            <br><br><br>"
				+ "            <div class='textarea'>"
				+ "              <label>Address</label>"
				+ "              <textarea placeholder='Enter the address'  id='address' name='address' rows='5' cols='60'></textarea>"
				+ "            </div>"
				+ "            <br><br><br>"
				+ "            <label>Contact No.</label>"
				+ "            <input type='tel' placeholder='Enter the contact no.' id='contact' name='contact' pattern='[6-9]{1}[0-9]{9}'>"
				+ "            <br><br><br><br>"
				+ "            <input type='submit' name='button' id='button' value='Save' onclick='return valForm()'>"
				+ "        </form>"
				+ "    </div>"
				+ "	   <table>"
				+ "      <tr>"
				+ "        <th>Sl No</th>"
				+ "        <th>User Name</th>"
				+ "        <th>Category</th>"
				+ "		   <th>Address</th>"
				+ "		   <th>Contact No</th>"
				+ "        <th>Edit</th>"
				+ "        <th>Delete</th>"
				+ "      </tr>");
		
		int i=1;
		
		ArrayList<DailyHelpBean> dailyHelp=null;
		
		try {

			dailyHelp=DailyHelpDAO.listDailyHelp();
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
		
		
		for(DailyHelpBean dh : dailyHelp) {
			out.print("<tr>"
					+ "	<td>"+i+"</td>"
					+ " <td>"+dh.getDhName()+"</td>"
					+ " <td>"+dh.getDhCategory()+"</td>"
					+ " <td>"+dh.getDhAddress()+"</td>"
					+ " <td>"+dh.getDhContact()+"</td>"
					+ "	<td><a href='DailyHelpUpdate?dhId="+dh.getDhId()+"'>Edit</a></td>"
					+ "	<td><a href='DailyHelpDelete?dhId="+dh.getDhId()+"'>Delete</a></td>"
					+ "</tr>");
			
			i++;
				
		}
		
		out.print("</table>"
				+ "</body>"
				+ "</html>");
		
	}
	
	
	
}


	
	


