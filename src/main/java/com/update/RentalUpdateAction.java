package com.update;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bean.FlatBean;
import com.bean.RentalBean;
import com.bean.TenantDetailsBean;
import com.dao.RentalDAO;

public class RentalUpdateAction extends HttpServlet{
	
	private static final long serialVersionUID = 1L;
	
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{
		
		int rId=Integer.parseInt(request.getParameter("rId"));
		int tenant=Integer.parseInt(request.getParameter("tenant"));
		int flat=Integer.parseInt(request.getParameter("flat"));
		
		boolean flag=false;
		
		RentalBean rentalBean=new RentalBean();
		TenantDetailsBean tenantDetailsBean=new TenantDetailsBean();
		FlatBean flatBean=new FlatBean();
		
		rentalBean.setrId(rId);
		tenantDetailsBean.setTId(tenant);
		flatBean.setFlId(flat);
		
		
		try {
			flag = RentalDAO.updateRental(rentalBean,tenantDetailsBean,flatBean);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		if(flag) {
			response.sendRedirect("RentalView");
		}else {
			response.sendRedirect("error.html");
		}
		
	}

}
