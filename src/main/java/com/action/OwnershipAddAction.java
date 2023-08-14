package com.action;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bean.FlatBean;
import com.bean.OwnerDetailsBean;
import com.dao.OwnershipDAO;

public class OwnershipAddAction extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		
		int owner=Integer.parseInt(request.getParameter("owner"));
		int flat=Integer.parseInt(request.getParameter("flat"));

		boolean flag=false;
		
		OwnerDetailsBean ownerDetailsBean=new OwnerDetailsBean();
		FlatBean flatBean=new FlatBean();
		
		ownerDetailsBean.setOwId(owner);
		flatBean.setFlId(flat);
		
		
		try {
			flag = OwnershipDAO.insertOwnership(ownerDetailsBean, flatBean);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		if(flag) {
			response.sendRedirect("OwnershipView");
		}else {
			response.sendRedirect("error.html");
		}
		
		
		
	}

}
