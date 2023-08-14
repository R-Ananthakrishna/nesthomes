package com.update;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bean.OwnerDetailsBean;
import com.dao.OwnerDetailsDAO;

public class OwnerDetailsUpdateAction extends HttpServlet {

	private static final long serialVersionUID = 1L;
	
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{
		
		int owId=Integer.parseInt(request.getParameter("owId"));
		String oName=request.getParameter("oName");
		String address=request.getParameter("address");
		long contact=Long.parseLong(request.getParameter("contact"));
		
		boolean flag=false;
		
		OwnerDetailsBean ownerDetailsBean=new OwnerDetailsBean();
		ownerDetailsBean.setOwId(owId);
		ownerDetailsBean.setOwName(oName);
		ownerDetailsBean.setOwAddress(address);
		ownerDetailsBean.setOwContact(contact);
		
		try {
			
			flag=OwnerDetailsDAO.updateOwner(ownerDetailsBean);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		if (flag){
			response.sendRedirect("OwnerDetailsView");
		}else {
			response.sendRedirect("error.html");
		}
	
	}

}
