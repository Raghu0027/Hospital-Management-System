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
<title>Update Doctor Details</title>
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

	<div class="container-fluid p-3 offset-md-4">
		<div class="row">

			<div class="col-md-4">
				<div class="card paint-card">
					<div class="card-body">
						<p class="fs-3 text-center">Edit Doctor Details</p>
						<c:if test="${not empty failureMsg}">
							<p class="fs-3 text-center text-danger">${failureMsg}</p>
							<c:remove var="failureMsg" scope="session" />
						</c:if>
						<c:if test="${not empty successMsg}">
							<div class="fs-3 text-center text-success" role="alert">${successMsg}</div>
							<c:remove var="successMsg" scope="session" />
						</c:if>
						
						<%
							int id = Integer.parseInt(request.getParameter("id")); 
							DoctorDetailsDao dao2 = new DoctorDetailsDao(DataBaseConnection.getConnection());
							DoctorDetails doc = dao2.getDoctorDetailById(id);
						%>
						
						<form action="../updateDoctorDetails" method="post">
							<div class="mb-3">
								<label class="form-label">Full Name</label> <input type="text"
									required name="fullname" class="form-control" value="<%=doc.getFullName()%>">
							</div>

							<div class="mb-3">
								<label class="form-label">DOB</label> <input type="date"
									required name="dob" class="form-control" value="<%=doc.getDob()%>">
							</div>

							<div class="mb-3">
								<label class="form-label">Qualification</label> <input required
									name="qualification" type="text" class="form-control" value="<%=doc.getQualification()%>">
							</div>
							<div class="mb-3">
								<label class="form-label">Specialist</label> <select
									name="specialist" required class="form-control" >
									<option ><%=doc.getSpecialist()%></option>


									<%
									SpecialistDao dao = new SpecialistDao(DataBaseConnection.getConnection());
									List<Specialist> list = dao.getAllSpecialist();
									for (Specialist s : list) {
									%>
									<option><%=s.getSpecialistName()%></option>
									<%
									}
									%>

								</select>
							</div>

							<div class="mb-3">
								<label class="form-label">Email</label> <input type="text"
									required name="email" class="form-control" value="<%=doc.getEmail()%>">
							</div>

							<div class="mb-3">
								<label class="form-label">Mob No</label> <input type="text"
									required name="mobno" class="form-control" value="<%=doc.getMobNo()%>">
							</div>

							<div class="mb-3">
								<label class="form-label">Password</label> <input required
									name="password" type="text" class="form-control" value="<%=doc.getPassword()%>">
							</div>
								
							<input type="hidden" name="id" value="<%=doc.getId()%>">	
								
							<button type="submit" class="btn btn-primary col-md-12">Update</button>
						</form>
					</div>
				</div>
			</div>
		</div>
	</div>

	<%@include file="../component/footer.jsp"%>
	<%@include file="../component/allScripts.jsp"%>
</body>
</html>