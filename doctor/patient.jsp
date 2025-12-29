<%@page import="com.db.DataBaseConnection"%>
<%@page import="com.dao.AppointmentDao"%>
<%@page import="com.entity.DoctorDetails"%>
<%@page import="com.entity.UserAppointment"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page isELIgnored="false"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Patient</title>
<%@include file="../component/allCss.jsp"%>
<style type="text/css">
.paint-card {
	box-shadow: 0 0 10px 0 rgba(0, 0, 0, 0.3);
}
</style>
</head>
<body>
	<%@include file="navbar.jsp"%>
	<c:if test="${empty docObj }">
		<c:redirect url="../doctor_login.jsp"></c:redirect>
	</c:if>

	<div class="container p-3">
		<div class="row">

			<div class="col-md-12">
				<div class="card paint-card">
					<div class="card-body">
						<p class="fs-3 text-center">Patient Details</p>
						<c:if test="${not empty failureMsg}">
							<p class="fs-4 text-center text-danger">${failureMsg}</p>
							<c:remove var="failureMsg" scope="session" />
						</c:if>
						<c:if test="${not empty successMsg}">
							<p class=" fs-4 text-center text-success">${successMsg}</p>
							<c:remove var="successMsg" scope="session" />
						</c:if>

						<table class="table">
							<thead>
								<tr>
									<th scope="col">Full Name</th>
									<th scope="col">Gender</th>
									<th scope="col">Age</th>
									<th scope="col">Appointment Date</th>
									<th scope="col">Email</th>
									<th scope="col">Mob No</th>
									<th scope="col">Diseases</th>
									<th scope="col">Status</th>
									<th scope="col">Action</th>
								</tr>
							</thead>
							<tbody>

								<%
									DoctorDetails doc = (DoctorDetails)session.getAttribute("docObj");
									int doctorId = doc.getId();
									
									AppointmentDao dao = new AppointmentDao(DataBaseConnection.getConnection());
									List<UserAppointment> list = dao.getAllAppointmentByDoctorLogin(doctorId);
									
									for(UserAppointment obj : list)
									{
								%>

								<tr>
									<td><%=obj.getFullName() %></td>
									<td><%=obj.getGender() %></td>
									<td><%=obj.getAge() %></td>
									<td><%=obj.getAppointmentDate()%></td>
									<td><%=obj.getEmail()%></td>
									<td><%=obj.getPhNo()%></td>
									<td><%=obj.getDiseases()%></td>

									<td>
										<%
													if(obj.getStatus().equalsIgnoreCase("Pending"))
													{
												%> <a class="btn btn-sm btn-warning">Pending</a> <% 		
													}else {
												%> <%=obj.getStatus() %> <%
													}	
												%>
									</td>
									<!-- <td><%=obj.getStatus()%></td> -->
									<td><a href="comment.jsp?id=<%=obj.getId() %>"
										class="btn btn-sm btn-success">Comment</a></td>


								</tr>


								<%		
									}
								%>

							</tbody>
						</table>

					</div>
				</div>
			</div>

		</div>
	</div>




	<%@include file="../component/footer.jsp"%>
	<%@include file="../component/allScripts.jsp"%>
</body>
</html>