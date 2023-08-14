package com.delete;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.bean.FlatBean;
import com.bean.RentalBean;
import com.bean.TenantDetailsBean;
import com.dao.RentalDAO;

public class RentalDelete extends HttpServlet {
	
	
	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
		
		PrintWriter out=response.getWriter();
		
		HttpSession session=request.getSession(false);
		String uName=(String)session.getAttribute("uName");
		
		int rId=Integer.parseInt(request.getParameter("rId"));
		
		
		ArrayList<RentalBean> rentalFetch=null;
		
		try {
			
			rentalFetch = RentalDAO.fetchRentalById(rId);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
	
		out.print("<!DOCTYPE html>"
				+ "<html lang='en'>"
				+ "<head>"
				+ "    <meta charset='UTF-8'>"
				+ "    <meta http-equiv='X-UA-Compatible' content='IE=edge'>"
				+ "    <meta name='viewport' content='width=device-width, initial-scale=1.0'>"
				+ "    <title>Rental Delete</title>"
				+ "    <link rel='stylesheet' href='./css/rentals.css' type='text/css'>"
				+ "    <link rel='stylesheet' href='https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css'>"
				+ "	   <script src='./js/rental.js' type='text/javascript'></script>"
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
				+ "        <h1>Rental</h1>"
				+ "    </div>"
				+ "    <div class='rental'>"
				+ "        <form id='rent' name='rent' method='post' action='RentalDeleteAction'>");
		
		for(RentalBean r: rentalFetch) {
			
			
			out.print("            <input type='hidden' id='rId' name='rId' value='"+r.getrId()+"'>"
					+ "            <label>Tenant</label>"
					+ "            <select id='tenant' name='tenant'>"
					+ "                <option value='select'>Select-One</option>");
		
		
		
			ArrayList<TenantDetailsBean> tenantList =null;
				
			try {

				tenantList=RentalDAO.listOnlyTenant();
			
			}catch(SQLException e) {
				e.printStackTrace();
			}
						
			for(TenantDetailsBean t : tenantList) {
					
				out.print("           <option value='"+t.getTId()+"'>"+t.getTName()+"</option>");
			}
		
		
		
			out.print("            </select>"
					+ "           <br><br><br>"
					+ "           <label>Flat</label>"
					+ "           <select id='flat' name='flat'>"
					+ "               <option value='select'>Select-One</option>");
				
			ArrayList<FlatBean> flatList =null;
				
			try {

				flatList=RentalDAO.listOnlyFlat();
					
			}catch(SQLException e) {
				e.printStackTrace();
			}
								
			for(FlatBean f : flatList) {
							
				out.print("           <option value='"+f.getFlId()+"'>"+f.getFlNo()+" "+f.getFlTower()+"</option>");
			}
		

				
			out.print("           </select>"
					+ "          <br><br><br><br>"
					+ "            <input type='submit' name='button' id='button' value='Delete'>");
		}
		
		out.print("        </form>"
				+ "    </div>"
				+ "	   <table>"
				+ "      <tr>"
				+ "        <th>Sl No</th>"
				+ "        <th>Tenant</th>"
				+ "		   <th>Flat No</th>"
				+ "        <th>Edit</th>"
				+ "        <th>Delete</th>"
				+ "      </tr>");
		
		int i=1;
		
		ArrayList<RentalBean> rental=null;
		
		try {

			rental=RentalDAO.listRental();
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
		
		
		for(RentalBean r : rental) {
			out.print("<tr>"
					+ "	<td>"+i+"</td>"
					+ " <td>"+r.getTName()+"</td>"
					+ " <td>"+r.getFlatNo()+" "+r.getFlatTower()+"</td>"
					+ "	<td><a href='RentalUpdate?rId="+r.getrId()+"'>Edit</a></td>"
					+ "	<td><a href='RentalDelete?rId="+r.getrId()+"'>Delete</a></td>"
					+ "</tr>");
			
			i++;
				
		}
		
		out.print("</table>"
				+ "</body>"
				+ "</html>");
		
	}

}
