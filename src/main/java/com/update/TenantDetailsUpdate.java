package com.update;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.bean.TenantDetailsBean;
import com.dao.TenantDetailsDAO;

public class TenantDetailsUpdate extends HttpServlet {

	private static final long serialVersionUID = 1L;
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
		
		PrintWriter out=response.getWriter();
		
		HttpSession session=request.getSession(false);
		String uName=(String)session.getAttribute("uName");
		
		int tId=Integer.parseInt(request.getParameter("tId"));
		
		ArrayList<TenantDetailsBean> tenantDetailsFetch=null;
		
		try {
			
			tenantDetailsFetch = TenantDetailsDAO.fetchTenantById(tId);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
	
		out.print("<!DOCTYPE html>"
				+ "<html lang='en'>"
				+ "<head>"
				+ "    <meta charset='UTF-8'>"
				+ "    <meta http-equiv='X-UA-Compatible' content='IE=edge'>"
				+ "    <meta name='viewport' content='width=device-width, initial-scale=1.0'>"
				+ "    <title>Tenant Details Update</title>"
				+ "    <link rel='stylesheet' href='./css/tenants.css' type='text/css'>"
				+ "    <link rel='stylesheet' href='https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css'>"
				/*
				 * +
				 * "    <script src='https://ajax.googleapis.com/ajax/libs/jquery/3.6.4/jquery.min.js'></script>"
				 * +
				 * "    <link rel='stylesheet' href='https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css'>"
				 * +
				 * "    <script src='https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js'></script>"
				 */
				+ "	   <script src='./js/tenant.js' type='text/javascript'></script>"
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
				/*
				 * + "	   <div class='alert alert-success alert-dismissible' id='alert'>" +
				 * "        <div id='top'>" +
				 * "            <a href='#' class='dismiss' data-dismiss='alert' aria-label='close'>&times;</a>"
				 * + "            <i class='fa fa-check-circle fa-5x'></i>" +
				 * "            <h1>Successful</h1>" + "        </div>" +
				 * "        <div id='bottom'>" +
				 * "            <h3>Owner details have been saved successfully</h3><br>" +
				 * "            <a href='admin.html'><input type='button' id='goHome' name='goHome' value='Go Back Home'></a>"
				 * + "        </div>" + "    </div>"
				 */
				+ "    <div id='form-header'>"
				+ "        <h1>Tenant Details</h1>"
				+ "    </div>"
				+ "    <div class='tenant-details'>"
				+ "        <form id='tenant' name='tenant' method='post' action='TenantDetailsUpdateAction'>");
		
		for(TenantDetailsBean t: tenantDetailsFetch) {
			
			out.print("			   <input type='hidden' id='tId' name='tId' value='"+t.getTId()+"'>"
					+ "            <label>Owner Name</label>"
					+ "            <input type='text' id='tName' name='tName' value='"+t.getTName()+"'>"
					+ "            <br><br><br>"
					+ "            <div class='textarea'>"
					+ "              <label>Address</label>"
					+ "              <textarea id='address' name='address' rows='5' cols='60'>"+t.getTAddress()+"</textarea>"
					+ "            </div>"
					+ "            <br><br><br>"
					+ "            <label>Contact No.</label>"
					+ "            <input type='tel' id='contact' name='contact' value='"+t.getTContact()+"' pattern='[6-9]{1}[0-9]{9}'>"
					+ "            <br><br><br><br>"
					+ "            <input type='submit' name='button' id='button' value='Update' onclick='return valForm()'>");
		}
		
		
		out.print("        </form>"
				+ "    </div>"
				+ "	   <table>"
				+ "      <tr>"
				+ "        <th>Sl No</th>"
				+ "        <th>Tenant Name</th>"
				+ "		   <th>Address</th>"
				+ "		   <th>Contact No</th>"
				+ "        <th>Edit</th>"
				+ "        <th>Delete</th>"
				+ "      </tr>");

		
		int i=1;
		
		ArrayList<TenantDetailsBean> tenantDetails=null;
		
		try {

			tenantDetails=TenantDetailsDAO.listTenant();
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
		
		
		for(TenantDetailsBean t : tenantDetails) {
			out.print("<tr>"
					+ "	<td>"+i+"</td>"
					+ " <td>"+t.getTName()+"</td>"
					+ " <td>"+t.getTAddress()+"</td>"
					+ " <td>"+t.getTContact()+"</td>"
					+ "	<td><a href='TenantDetailsUpdate?tId="+t.getTId()+"'>Edit</a></td>"
					+ "	<td><a href='TenantDetailsDelete?tId="+t.getTId()+"'>Delete</a></td>"
					+ "</tr>");
			
			i++;
				
		}
		
		out.print("</table>"
				+ "</body>"
				+ "</html>");
		
	}
	

}
