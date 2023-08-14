package com.viewlist;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.bean.OwnerDetailsBean;
import com.dao.OwnerDetailsDAO;

public class OwnerDetailsViewAction extends HttpServlet {
	

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
				+ "    <title>Owner Details</title>"
				+ "    <link rel='stylesheet' href='./css/owners.css' type='text/css'>"
				+ "    <link rel='stylesheet' href='https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css'>"
				/*
				 * +
				 * "    <script src='https://ajax.googleapis.com/ajax/libs/jquery/3.6.4/jquery.min.js'></script>"
				 * +
				 * "    <link rel='stylesheet' href='https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css'>"
				 * +
				 * "    <script src='https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js'></script>"
				 */
				+ "	   <script src='./js/owner.js' type='text/javascript'></script>"
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
				+ "        <h1>Owner Details</h1>"
				+ "    </div>"
				+ "    <div class='owner-details'>"
				+ "        <form id='owner' name='owner' method='post' action='OwnerDetails'>"
				+ "            <label>Owner Name</label>"
				+ "            <input type='text' placeholder='Enter the name' id='oName' name='oName'>"
				+ "            <br><br><br>"
				+ "            <div class='textarea'>"
				+ "              <label>Address</label>"
				+ "              <textarea placeholder='Enter the address'  id='address' name='address' rows='5' cols='60'></textarea>"
				+ "            </div>  "
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
				+ "        <th>Owner Name</th>"
				+ "		   <th>Address</th>"
				+ "		   <th>Contact No</th>"
				+ "        <th>Edit</th>"
				+ "        <th>Delete</th>"
				+ "      </tr>");
		
		int i=1;
		
		ArrayList<OwnerDetailsBean> ownerDetails=null;
		
		try {

			ownerDetails=OwnerDetailsDAO.listOwner();
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
		
		
		for(OwnerDetailsBean ow : ownerDetails) {
			out.print("<tr>"
					+ "	<td>"+i+"</td>"
					+ " <td>"+ow.getOwName()+"</td>"
					+ " <td>"+ow.getOwAddress()+"</td>"
					+ " <td>"+ow.getOwContact()+"</td>"
					+ "	<td><a href='OwnerDetailsUpdate?owId="+ow.getOwId()+"'>Edit</a></td>"
					+ "	<td><a href='OwnerDetailsDelete?owId="+ow.getOwId()+"'>Delete</a></td>"
					+ "</tr>");
			
			i++;
				
		}
		
		out.print("</table>"
				+ "</body>"
				+ "</html>");
		
	}
	
	
	
}


	
	


