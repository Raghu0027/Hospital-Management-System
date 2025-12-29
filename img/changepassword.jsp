<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page isELIgnored="false"%>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Change Password</title>

<%@include file="component/allCss.jsp"%>

<style type="text/css">
.paint-card {
	box-shadow: 0 0 10px 0 rgba(0, 0, 0, 0.3);
}
</style>

</head>
<body>

	<%@include file="component/navbar.jsp"%>
	
	<c:if test="${ empty userObj }">
		<c:redirect url="user_login.jsp"></c:redirect>
	</c:if>

	<div class="container p-4">
		<div class="row">
			<div class="col-md-4 offset-md-4">
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
						<form action="changePassword" method="post">
							<div class="mb-3">
								<label>Enter New Password</label> <input type="text"
									name="newPassword" class="form-control" required>
							</div>

							<div class="mb-3">
								<label>Enter Old Password</label> <input type="text"
									name="oldPassword" class="form-control" required>
							</div>
							<input type="hidden" value="${userObj.id }" name="userId">
							<button class="btn btn-success col-md-12" >Change
								Password</button>
						</form>
					</div>
				</div>
			</div>
		</div>
	</div>
	
	<%@include file="component/footer.jsp"%>
	<%@include file="component/allScripts.jsp"%>
</body>
</html>












