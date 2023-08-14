package com.delete;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bean.TenantDetailsBean;
import com.dao.TenantDetailsDAO;

public class TenantDetailsDeleteAction extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{
		
		int tId=Integer.parseInt(request.getParameter("tId"));
		
		boolean flag=false;
		
		TenantDetailsBean tenantDetailsBean=new TenantDetailsBean();
		tenantDetailsBean.setTId(tId);
		
		
		try {
			
			flag=TenantDetailsDAO.deleteTenant(tenantDetailsBean);
			
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
