package com.doctor.servlet;

import java.io.IOException;

import com.dao.AppointmentDao;
import com.db.DataBaseConnection;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/updateCommentStatus")
public class UpdateCommentStatus extends HttpServlet
{

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException 
	{
		String comment = req.getParameter("comment");
		int id = Integer.parseInt(req.getParameter("id"));
		int doctorId = Integer.parseInt(req.getParameter("docId"));

		AppointmentDao dao = new AppointmentDao(DataBaseConnection.getConnection());

		HttpSession session = req.getSession();

		if(dao.updateCommentStatus(id, doctorId, comment))
		{
			session.setAttribute("successMsg", "Comment updated successfully !");
			resp.sendRedirect("doctor/patient.jsp");
		}
		else
		{
			session.setAttribute("failureMsg", "Something went wromg");
			resp.sendRedirect("doctor/patient.jsp");
		}
	}

}