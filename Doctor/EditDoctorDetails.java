package com.doctor.servlet;

import java.io.IOException;

import com.dao.DoctorDetailsDao;
import com.db.DataBaseConnection;
import com.entity.DoctorDetails;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/editDoctor")
public class EditDoctorDetails extends HttpServlet 
{

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException 
	{

		String fullName = req.getParameter("fullname");
		String dob = req.getParameter("dob");
		String qualification = req.getParameter("qualification");
		String specialist = req.getParameter("specialist");
		String email = req.getParameter("email");
		String mobno = req.getParameter("mobno");

		int id = Integer.parseInt(req.getParameter("id"));

		DoctorDetails doctor = new DoctorDetails(id,fullName, dob, qualification, specialist, email, mobno, "");

		DoctorDetailsDao dao = new DoctorDetailsDao(DataBaseConnection.getConnection());

		HttpSession session = req.getSession();

		if(dao.editDoctorDetails(doctor))
		{
			DoctorDetails updatedDoc = dao.getDoctorDetailById(id);
			session.setAttribute("successMsgD", "Doctor details updated successfully !");
			session.setAttribute("docObj", updatedDoc);
			resp.sendRedirect("doctor/edit_profile.jsp");
		}
		else
		{
			session.setAttribute("failureMsgD", "Something went wrong !");
			resp.sendRedirect("doctor/edit_profile.jsp");
		}
	}

}










