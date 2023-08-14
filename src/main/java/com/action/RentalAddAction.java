package com.action;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bean.FlatBean;
import com.bean.TenantDetailsBean;
import com.dao.RentalDAO;

public class RentalAddAction extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		
		int tenant=Integer.parseInt(request.getParameter("tenant"));
		int flat=Integer.parseInt(request.getParameter("flat"));

		boolean flag=false;
		
		TenantDetailsBean tenantDetailsBean=new TenantDetailsBean();
		FlatBean flatBean=new FlatBean();
		
		tenantDetailsBean.setTId(tenant);
		flatBean.setFlId(flat);
		
		
		try {
			flag = RentalDAO.insertRental(tenantDetailsBean, flatBean);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		if(flag) {
			response.sendRedirect("RentalView");
		}else {
			response.sendRedirect("error.html");
		}
		
		
		
	}

}
