package com.user.servlet;

import java.io.IOException;

import com.dao.UserDao;
import com.db.DataBaseConnection;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/changePassword")
public class ChangePasword extends HttpServlet 
{

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException 
	{
		int userId = Integer.parseInt(req.getParameter("userId"));
		String oldPassword = req.getParameter("oldPassword");
		String newPassword = req.getParameter("newPassword");

		UserDao dao = new UserDao(DataBaseConnection.getConnection());
		HttpSession session = req.getSession();

		if(dao.checkOldPassword(userId, oldPassword))
		{
			if(dao.setNewPassword(userId, newPassword))
			{
				session.setAttribute("successMsg", "Password changed successfully !");
				resp.sendRedirect("change_password.jsp");
			}
			else
			{
				session.setAttribute("failuremsg", "Incorrect old password !");
				resp.sendRedirect("change_password.jsp");
			}
		}
		else
		{
			session.setAttribute("failuremsg", "Incorrect old password !");
			resp.sendRedirect("change_password.jsp");
		}


	}

}







