package com.update;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bean.FlatBean;
import com.bean.OwnerDetailsBean;
import com.bean.OwnershipBean;
import com.dao.OwnershipDAO;

public class OwnershipUpdateAction extends HttpServlet{
	
private static final long serialVersionUID = 1L;
	
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{
		
		int oId=Integer.parseInt(request.getParameter("oId"));
		int owner=Integer.parseInt(request.getParameter("owner"));
		int flat=Integer.parseInt(request.getParameter("flat"));
		
		boolean flag=false;
		
		OwnershipBean ownershipBean=new OwnershipBean();
		OwnerDetailsBean ownerDetailsBean=new OwnerDetailsBean();
		FlatBean flatBean=new FlatBean();
		
		ownershipBean.setFlOwId(oId);
		ownerDetailsBean.setOwId(owner);
		flatBean.setFlId(flat);
		
		
		try {
			flag = OwnershipDAO.updateOwnership(ownershipBean,ownerDetailsBean,flatBean);
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
