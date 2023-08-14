package com.action;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bean.FlatBean;
import com.dao.FlatDAO;

public class FlatAddAction extends HttpServlet {

	private static final long serialVersionUID = 1L;
	
	
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String flatNo=request.getParameter("flatNo");
		String flatType=request.getParameter("flatType");
		String flatTower=request.getParameter("flatTower");
		
		boolean flag=false;
		
		FlatBean flatBean=new FlatBean();
		
		flatBean.setFlNo(flatNo);
		flatBean.setFlType(flatType);
		flatBean.setFlTower(flatTower);
		
		try {
			
			flag=FlatDAO.insertFlat(flatBean);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		if(flag) {
			response.sendRedirect("FlatView");
		}else {
			response.sendRedirect("error.html");
		}
		
	}
	

}
