package com.doctor.servlet;

import java.io.IOException;

import com.dao.DoctorDetailsDao;
import com.db.DataBaseConnection;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/changeDoctorPassword")
public class ChangeDoctorPassword extends HttpServlet
{
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException 
	{
		int doctorId = Integer.parseInt(req.getParameter("doctorId"));
		String oldPassword = req.getParameter("oldPassword");
		String newPassword = req.getParameter("newPassword");

		DoctorDetailsDao dao = new DoctorDetailsDao(DataBaseConnection.getConnection());

		HttpSession session = req.getSession();

		if(dao.checkOldPassword(doctorId, oldPassword))
		{
			if(dao.setNewPassword(doctorId, newPassword))
			{
				session.setAttribute("successMsg", "Password changed successfully !");
				resp.sendRedirect("doctor/edit_profile.jsp");
			}
			else
			{
				session.setAttribute("failuremsg", "Incorrect old password !");
				resp.sendRedirect("doctor/edit_profile.jsp");
			}
		}
		else
		{
			session.setAttribute("failuremsg", "Incorrect old password !");
			resp.sendRedirect("edit_profile.jsp");
		}
	}

}








