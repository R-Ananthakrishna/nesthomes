package com.update;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bean.TenantDetailsBean;
import com.dao.TenantDetailsDAO;

public class TenantDetailsUpdateAction extends HttpServlet {

	private static final long serialVersionUID = 1L;
	
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{
		
		int tId=Integer.parseInt(request.getParameter("tId"));
		String tName=request.getParameter("tName");
		String address=request.getParameter("address");
		long contact=Long.parseLong(request.getParameter("contact"));
		
		boolean flag=false;
		
		TenantDetailsBean tenantDetailsBean=new TenantDetailsBean();
		tenantDetailsBean.setTId(tId);
		tenantDetailsBean.setTName(tName);
		tenantDetailsBean.setTAddress(address);
		tenantDetailsBean.setTContact(contact);
		
		try {
			
			flag=TenantDetailsDAO.updateTenant(tenantDetailsBean);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		if (flag){
			response.sendRedirect("TenantDetailsView");
		}else {
			response.sendRedirect("error.html");
		}
	
	}

}
