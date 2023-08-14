package com.action;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bean.TenantDetailsBean;
import com.dao.TenantDetailsDAO;

public class TenantDetailsAddAction extends HttpServlet {

	private static final long serialVersionUID = 1L;
	
	RequestDispatcher dis=null;

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		
		String tName=request.getParameter("tName");
		String address=request.getParameter("address");
		long contact=Long.parseLong(request.getParameter("contact"));
		boolean flag=false;
		
		TenantDetailsBean tenantDetailsBean=new TenantDetailsBean();
		
		tenantDetailsBean.setTName(tName);
		tenantDetailsBean.setTAddress(address);
		tenantDetailsBean.setTContact(contact);
		
		
		try {
			flag = TenantDetailsDAO.insertTenant(tenantDetailsBean);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		if(flag) {
			dis=request.getRequestDispatcher("TenantDetailsView");
			dis.forward(request, response);
		}else {
			dis=request.getRequestDispatcher("error.html");
			dis.forward(request, response);
		}
		
		
		
	}

}
