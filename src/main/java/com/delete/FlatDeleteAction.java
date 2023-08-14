package com.delete;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bean.FlatBean;
import com.dao.FlatDAO;

public class FlatDeleteAction extends HttpServlet {

	private static final long serialVersionUID = 1L;
	
	
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{
		
		int flId=Integer.parseInt(request.getParameter("flId"));
		
		
		boolean flag=false;
		
		FlatBean flatBean=new FlatBean();
		flatBean.setFlId(flId);
		
		
		
		try {
			
			flag=FlatDAO.deleteFlat(flatBean);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		if (flag){
			response.sendRedirect("FlatView");
		}else {
			response.sendRedirect("error.html");
		}
		
		
	}

}
