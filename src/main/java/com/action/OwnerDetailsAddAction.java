package com.action;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bean.OwnerDetailsBean;
import com.dao.OwnerDetailsDAO;

public class OwnerDetailsAddAction extends HttpServlet {

	private static final long serialVersionUID = 1L;
	

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		
		String oName=request.getParameter("oName");
		String address=request.getParameter("address");
		long contact=Long.parseLong(request.getParameter("contact"));
		boolean flag=false;
		
		OwnerDetailsBean ownerDetailsBean=new OwnerDetailsBean();
		
		ownerDetailsBean.setOwName(oName);
		ownerDetailsBean.setOwAddress(address);
		ownerDetailsBean.setOwContact(contact);
		
		
		try {
			flag = OwnerDetailsDAO.insertOwner(ownerDetailsBean);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		if(flag) {
			response.sendRedirect("OwnerDetailsView");
		}else {
			response.sendRedirect("error.html");
		}
		
		
		
	}

}
