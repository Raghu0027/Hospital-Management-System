package com.admin.servlet;

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

@WebServlet("/addDoctor")
public class AddDoctor extends HttpServlet
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
		String password = req.getParameter("password");

		DoctorDetails doctor = new DoctorDetails(fullName, dob, qualification, specialist, email, mobno, password);

		DoctorDetailsDao dao = new DoctorDetailsDao(DataBaseConnection.getConnection());

		HttpSession session = req.getSession();

		if(dao.registerDoctor(doctor))
		{
			session.setAttribute("successMsg", "Doctor added successfully !");
			resp.sendRedirect("admin/doctor.jsp");
		}
		else
		{
			session.setAttribute("failureMsg", "Something went wrong !");
			resp.sendRedirect("admin/doctor.jsp");
		}
	}
}


