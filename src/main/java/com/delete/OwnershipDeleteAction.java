package com.delete;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bean.OwnershipBean;
import com.dao.OwnershipDAO;

public class OwnershipDeleteAction extends HttpServlet {
	
	private static final long serialVersionUID = 1L;

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{
		
		String id=request.getParameter("oId");
		int oId=Integer.parseInt(id);
		
		
		boolean flag=false;
		
		OwnershipBean ownershipBean=new OwnershipBean();
		
		ownershipBean.setFlOwId(oId);
		
		
		try {
			flag = OwnershipDAO.deleteOwnership(ownershipBean);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		if(flag) {
			response.sendRedirect("OwnershipView");
		}else {
			response.sendRedirect("error.html");
		}
		
	}


}
