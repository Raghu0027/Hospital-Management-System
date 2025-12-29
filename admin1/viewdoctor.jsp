<%@page import="java.sql.Connection"%>
<%@page import="com.db.DataBaseConnection"%>
<%@page import="com.dao.SpecialistDao"%>
<%@page import="com.dao.DoctorDetailsDao"%>
<%@page import="com.entity.Specialist"%>
<%@page import="com.entity.DoctorDetails"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page isELIgnored="false"%>


<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>View Doctor Details</title>
<%@include file="../component/allCss.jsp"%>

<style type="text/css">
.paint-card {
	box-shadow: 0 0 10px 0 rgba(0, 0, 0, 0.3);
}
</style>

</head>

<body>

	<% Connection connection = DataBaseConnection.getConnection();
		out.print(connection);
	%>
	<%@include file="navbar.jsp"%>

	<div class="container-fluid p-3">
		<div class="row">
			<div class="col-md-12">
				<div class="card paint-card">
					<div class="card-body">
						<p class="fs-3 text-center">Doctor Details</p>
						<c:if test="${not empty errorMsg}">
							<p class="fs-3 text-center text-danger">${errorMsg}</p>
							<c:remove var="errorMsg" scope="session" />
						</c:if>
						<c:if test="${not empty succMsg}">
							<div class="fs-3 text-center text-success" role="alert">${succMsg}</div>
							<c:remove var="succMsg" scope="session" />
						</c:if>
						<table class="table">
							<thead>
								<tr>
									<th scope="col">Full Name</th>
									<th scope="col">DOB</th>
									<th scope="col">Qualification</th>
									<th scope="col">Specialist</th>
									<th scope="col">Email</th>
									<th scope="col">Mob No</th>
									<th scope="col">Action</th>
								</tr>
							</thead>
							<tbody>


								<%
								DoctorDetailsDao docDao = new DoctorDetailsDao(DataBaseConnection.getConnection());
										List<DoctorDetails> docList = docDao.getAllDoctor();
										for (DoctorDetails doc : docList) {
								%>
										<tr>
											<td><%=doc.getFullName()%></td>
											<td><%=doc.getDob()%></td>
											<td><%=doc.getQualification()%></td>
											<td><%=doc.getSpecialist()%></td>
											<td><%=doc.getEmail()%></td>
											<td><%=doc.getMobNo()%></td>
											<td>
												<a href="edit_doctor.jsp?id=<%=doc.getId() %>" class="btn btn-sm btn-primary">Edit</a>
												<a href="../deleteDoctor?id=<%=doc.getId() %>" class="btn btn-sm btn-danger">Delete</a>
											</td>
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