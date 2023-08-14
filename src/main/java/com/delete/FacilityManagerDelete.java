package com.delete;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.bean.FacilityManagerBean;
import com.dao.FacilityManagerDAO;

public class FacilityManagerDelete extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
		
		PrintWriter out=response.getWriter();
		
		HttpSession session=request.getSession(false);
		String uName=(String)session.getAttribute("uName");
		
		int fmId=Integer.parseInt(request.getParameter("fmId"));
		
		ArrayList<FacilityManagerBean> facilityManagerFetch=null;
		
		try {
			
			facilityManagerFetch = FacilityManagerDAO.fetchFacilityManagerById(fmId);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
	
		out.print("<!DOCTYPE html>"
				+ "<html lang='en'>"
				+ "<head>"
				+ "    <meta charset='UTF-8'>"
				+ "    <meta http-equiv='X-UA-Compatible' content='IE=edge'>"
				+ "    <meta name='viewport' content='width=device-width, initial-scale=1.0'>"
				+ "    <title>Facility Manager Delete</title>"
				+ "    <link rel='stylesheet' href='./css/facility-manager.css' type='text/css'>"
				+ "    <link rel='stylesheet' href='https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css'>"
				+ "	   <script src='./js/facility-manager.js' type='text/javascript'></script>"
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
				+ "        <h1>Facility Manager</h1>"
				+ "    </div>"
				+ "    <div class='facility-manager'>"
				+ "        <form id='fManager' name='fManager' method='post' action='FacilityManagerDeleteAction'>");
		
		for(FacilityManagerBean fm: facilityManagerFetch) {
			
			out.print("			   <input type='hidden' id='fmId' name='fmId' value='"+fm.getFmId()+"'>"
					+ "            <label>User Name</label>"
					+ "            <input type='text' id='uName' name='uName' readonly='true' value='"+fm.getFmName()+"'>"
					+ "            <br><br><br>"
					+"			   <label>Designation</label>"
					+ "            <input type='text' id='designation' name='designation' readonly='true' value='"+fm.getFmDesignation()+"'>"
					+ "            <br><br><br>"
					+ "            <div class='textarea'>"
					+ "              <label>Duties</label>"
					+ "              <textarea id='duties' name='duties' readonly='true' rows='5' cols='60'>"+fm.getFmDuties()+"</textarea>"
					+ "            </div>"
					+ "            <br><br><br>"
					+ "            <label>Contact No.</label>"
					+ "            <input type='tel' id='contact' name='contact' readonly='true' value='"+fm.getFmContact()+"' pattern='[6-9]{1}[0-9]{9}'>"
					+ "            <br><br><br><br>"
					+ "            <input type='submit' name='button' id='button' value='Delete'>");
		}
		
		
		out.print("        </form>"
				+ "    </div>"
				+ "	   <table>"
				+ "      <tr>"
				+ "        <th>Sl No</th>"
				+ "        <th>User Name</th>"
				+ "		   <th>Designation</th>"
				+ "		   <th>Duties</th>"
				+ "		   <th>Contact No</th>"
				+ "        <th>Edit</th>"
				+ "        <th>Delete</th>"
				+ "      </tr>");

		
		int i=1;
		
		ArrayList<FacilityManagerBean> facilityManager=null;
		
		try {

			facilityManager=FacilityManagerDAO.listFacilityManager();
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
		
		
		for(FacilityManagerBean fm : facilityManager) {
			out.print("<tr>"
					+ "	<td>"+i+"</td>"
					+ " <td>"+fm.getFmName()+"</td>"
					+ " <td>"+fm.getFmDesignation()+"</td>"
					+ " <td>"+fm.getFmDuties()+"</td>"
					+ " <td>"+fm.getFmContact()+"</td>"
					+ "	<td><a href='FacilityManagerUpdate?fmId="+fm.getFmId()+"'>Edit</a></td>"
					+ "	<td><a href='FacilityManagerDelete?fmId="+fm.getFmId()+"'>Delete</a></td>"
					+ "</tr>");
			
			i++;
				
		}
		
		out.print("</table>"
				+ "</body>"
				+ "</html>");
		
	}

}
