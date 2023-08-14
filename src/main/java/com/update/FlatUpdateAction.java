package com.update;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bean.FlatBean;
import com.dao.FlatDAO;

public class FlatUpdateAction extends HttpServlet {

	private static final long serialVersionUID = 1L;
	
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{
		
		int flId=Integer.parseInt(request.getParameter("flId"));
		String flatNo=request.getParameter("flatNo");
		String flatType=request.getParameter("flatType");
		String flatTower=request.getParameter("flatTower");
		
		boolean flag=false;
		
		FlatBean flatBean=new FlatBean();
		flatBean.setFlId(flId);
		flatBean.setFlNo(flatNo);
		flatBean.setFlType(flatType);
		flatBean.setFlTower(flatTower);
		
		try {
			
			flag=FlatDAO.updateFlat(flatBean);
			
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
