package com.update;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.bean.FlatBean;
import com.bean.OwnerDetailsBean;
import com.bean.OwnershipBean;
import com.dao.OwnershipDAO;

public class OwnershipUpdate extends HttpServlet{
	
	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
		
		PrintWriter out=response.getWriter();
		
		HttpSession session=request.getSession(false);
		String uName=(String)session.getAttribute("uName");
		
		int oId=Integer.parseInt(request.getParameter("oId"));
		
		
		ArrayList<OwnershipBean> ownershipFetch=null;
		
		try {
			
			ownershipFetch = OwnershipDAO.fetchOwnershipById(oId);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
	
		out.print("<!DOCTYPE html>"
				+ "<html lang='en'>"
				+ "<head>"
				+ "    <meta charset='UTF-8'>"
				+ "    <meta http-equiv='X-UA-Compatible' content='IE=edge'>"
				+ "    <meta name='viewport' content='width=device-width, initial-scale=1.0'>"
				+ "    <title>Ownership Update</title>"
				+ "    <link rel='stylesheet' href='./css/ownershipstyle.css' type='text/css'>"
				+ "    <link rel='stylesheet' href='https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css'>"
				+ "	   <script src='./js/ownership.js' type='text/javascript'></script>"
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
				+ "        <h1>Ownership</h1>"
				+ "    </div>"
				+ "    <div class='ownership'>"
				+ "        <form id='oShip' name='oShip' method='post' action='OwnershipUpdateAction'>");
		
		for(OwnershipBean o: ownershipFetch) {
			
			
			out.print("            <input type='hidden' id='oId' name='oId' value='"+o.getFlOwId()+"'>"
					+ "			   <label>Owner</label>"
					+ "            <select id='owner' name='owner'>"
					+ "                <option value='select'>Select-One</option>");
		
		
		
			ArrayList<OwnerDetailsBean> ownerList =null;
				
			try {

				ownerList=OwnershipDAO.listOnlyOwner();
			
			}catch(SQLException e) {
				e.printStackTrace();
			}
						
			for(OwnerDetailsBean ow : ownerList) {
					
				out.print("           <option value='"+ow.getOwId()+"'>"+ow.getOwName()+"</option>");
			}
		
		
		
			out.print("            </select>"
					+ "           <br><br><br>"
					+ "           <label>Flat</label>"
					+ "           <select id='flat' name='flat'>"
					+ "               <option value='select'>Select-One</option>");
				
			ArrayList<FlatBean> flatList =null;
				
			try {

				flatList=OwnershipDAO.listOnlyFlat();
					
			}catch(SQLException e) {
				e.printStackTrace();
			}
								
			for(FlatBean f : flatList) {
							
				out.print("           <option value='"+f.getFlId()+"'>"+f.getFlNo()+" "+f.getFlTower()+"</option>");
			}
		

				
			out.print("           </select>"
					+ "          <br><br><br><br>"
					+ "            <input type='submit' name='button' id='button' value='Update' onclick='return valForm()'>");
		}
		
		out.print("        </form>"
				+ "    </div>"
				+ "	   <table>"
				+ "      <tr>"
				+ "        <th>Sl No</th>"
				+ "        <th>Owner</th>"
				+ "		   <th>Flat No</th>"
				+ "        <th>Edit</th>"
				+ "        <th>Delete</th>"
				+ "      </tr>");
		
		int i=1;
		
		ArrayList<OwnershipBean> ownership=null;
		
		try {

			ownership=OwnershipDAO.listOwnership();
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
		
		
		for(OwnershipBean o : ownership) {
			out.print("<tr>"
					+ "	<td>"+i+"</td>"
					+ " <td>"+o.getOwName()+"</td>"
					+ " <td>"+o.getFlatNo()+" "+o.getFlatTower()+"</td>"
					+ "	<td><a href='OwnershipUpdate?oId="+o.getFlOwId()+"'>Edit</a></td>"
					+ "	<td><a href='OwnershipDelete?oId="+o.getFlOwId()+"'>Delete</a></td>"
					+ "</tr>");
			
			i++;
				
		}
		
		out.print("</table>"
				+ "</body>"
				+ "</html>");
		
	}

}
