package com.action;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bean.UserDetailsBean;
import com.dao.UserDetailsDAO;

public class UserDetailsAddAction extends HttpServlet {

	private static final long serialVersionUID = 1L;
	
	RequestDispatcher dis=null;

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		
		String uName=request.getParameter("uName");
		String pass=request.getParameter("pass");
		String uType=request.getParameter("uType");
		boolean flag=false;
		
		UserDetailsBean userDetailsBean=new UserDetailsBean();
		
		userDetailsBean.setuName(uName);
		userDetailsBean.setPass(pass);
		userDetailsBean.setuType(uType);
		
		
		try {
			flag = UserDetailsDAO.insertUser(userDetailsBean);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		if(flag) {
			dis=request.getRequestDispatcher("UserDetailsView");
			dis.forward(request, response);
		}else {
			dis=request.getRequestDispatcher("error.html");
			dis.forward(request, response);
		}
		
		
		
	}

}
