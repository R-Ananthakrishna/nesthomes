package com.delete;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bean.RentalBean;
import com.dao.RentalDAO;

public class RentalDeleteAction extends HttpServlet {
	
	
	private static final long serialVersionUID = 1L;
	
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{
		
		int rId=Integer.parseInt(request.getParameter("rId"));
		
		boolean flag=false;
		
		RentalBean rentalBean=new RentalBean();
		
		rentalBean.setrId(rId);
		
		
		try {
			flag = RentalDAO.deleteRental(rentalBean);
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
