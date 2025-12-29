<%@page import="com.entity.Specialist"%>
<%@page import="java.util.List"%>
<%@page import="com.entity.DoctorDetails"%>
<%@page import="com.db.DataBaseConnection"%>
<%@page import="com.dao.CountDao"%>
<%@page import="com.dao.SpecialistDao"%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page isELIgnored="false"%>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Edit Doctor</title>
<%@include file="../component/allCss.jsp" %>
<style type="text/css">
.paint-card {
	box-shadow: 0 0 10px 0 rgba(0, 0, 0, 0.3);
}
</style>
</head>
<body>

	<c:if test="${empty docObj }">
		<c:redirect url="../doctor_login.jsp"></c:redirect>
	</c:if>

	<%@include file="navbar.jsp" %>
<div class="container p-4">
		<div class="row">
			<div class="col-md-4">
				<div class="card paint-card">
					<p class="text-center fs-3">Change Password</p>
					<c:if test="${not empty successMsg }">
						<p class="text-center text-success fs-3">${successMsg}</p>
						<c:remove var="successMsg" scope="session" />
					</c:if>

					<c:if test="${not empty failuremsg }">
						<p class="text-center text-danger fs-5">${failuremsg}</p>
						<c:remove var="failuremsg" scope="session" />
					</c:if>
					<div class="card-body">
						<form action="../changeDoctorPassword" method="post">
							<div class="mb-3">
								<label>Enter New Password</label> <input type="text"
									name="newPassword" class="form-control" required>
							</div>

							<div class="mb-3">
								<label>Enter Old Password</label> <input type="text"
									name="oldPassword" class="form-control" required>
							</div>
							<input type="hidden" value="${docObj.id }" name="doctorId">
							<button class="btn btn-success col-md-12">Change
								Password</button>
						</form>
					</div>
				</div>
			</div>

			<div class="col-md-5 offset-md-2">
				<div class="card paint-card">
					<p class="text-center fs-3">Edit Profile</p>
					<c:if test="${not empty successMsgD }">
						<p class="text-center text-success fs-3">${successMsgD}</p>
						<c:remove var="successMsgD" scope="session" />
					</c:if>

					<c:if test="${not empty failureMsgD }">
						<p class="text-center text-danger fs-5">${failureMsgD}</p>
						<c:remove var="failureMsgD" scope="session" />
					</c:if>
					<div class="card-body">
						<form action="../editDoctor" method="post">
							<div class="mb-3">
								<label class="form-label">Full Name</label> <input type="text"
									required name="fullname" class="form-control"
									value="${docObj.fullName }">
							</div>

							<div class="mb-3">
								<label class="form-label">DOB</label> <input type="date"
									required name="dob" class="form-control"
									value="${docObj.dob }">
							</div>

							<div class="mb-3">
								<label class="form-label">Qualification</label> <input required
									name="qualification" type="text" class="form-control"
									value="${docObj.qualification }">
							</div>
							<div class="mb-3">
								<label class="form-label">Specialist</label> <select name="specialist"
									required class="form-control">
									<option>${docObj.specialist }</option>

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
									readonly required name="email" class="form-control"
									value="${docObj.email }">
							</div>

							<div class="mb-3">
								<label class="form-label">Mob No</label> <input type="text"
									required name="mobno" class="form-control"
									value="${docObj.mobNo }">
							</div>

							<input type="hidden" name="id" value="${docObj.id }">

							<button type="submit" class="btn btn-primary">Update</button>
						</form>

					</div>
				</div>
			</div>


		</div>
	</div>
	
	<%@include file="../component/footer.jsp" %>
	<%@include file="../component/allScripts.jsp" %>
</body>
</html>