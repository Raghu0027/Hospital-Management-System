package com.admin.servlet;

import java.io.IOException;

import com.dao.SpecialistDao;
import com.db.DataBaseConnection;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/addSpecialist")
public class AddSpecialist extends HttpServlet 
{
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException 
	{
		String specialistName = req.getParameter("specialistName");

		SpecialistDao dao = new SpecialistDao(DataBaseConnection.getConnection());

		boolean addSpecialist = dao.addSpecialist(specialistName);

		HttpSession session = req.getSession();

		if(addSpecialist)
		{

			session.setAttribute("successMsg", "Specialist added successfuly!");
			resp.sendRedirect("admin/index.jsp");
		}
		else
		{
			session.setAttribute("failureMsg", "Something went wrong!");
			resp.sendRedirect("admin/index.jsp");
		}




	}

}