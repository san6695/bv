<%@ page pageEncoding="utf-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>


<html lang="en">

<head>
    <base href="${pageContext.servletContext.contextPath}/">
  <meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
  <meta name="description" content="">
  <meta name="author" content="">
  <title>SB Admin - Start Bootstrap Template</title>
  <!-- Bootstrap core CSS-->
  <link href="vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">
  <!-- Custom fonts for this template-->
  <link href="vendor/font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css">
  <!-- Custom styles for this template-->
  <link href="css/sb-admin.css" rel="stylesheet">
</head>

<body class="bg-dark">
  <div class="container">
    <div class="card card-register mx-auto mt-5">
      <div class="card-header">Register an Account</div>
      <div class="card-body">
        ${message}
	<form:form action="user/register.htm" modelAttribute="user">
	<div class="form-group">
		<label>Username</label>
		<form:input path="id" cssClass="form-control"/>
	</div>
	
	<div class="form-group">
		<label>Password</label>
		<form:input type="password" path="password" cssClass="form-control"/>
	</div>
	
	<div class="form-group">
		<label>Fullname</label>
		<form:input path="fullname" cssClass="form-control"/>
	</div>
	
	<div class="form-group">
		<label>Email</label>
		<form:input path="email" cssClass="form-control"/>
	</div>
	
	
	
	<div class="form-group">
            <button class="btn btn-default">Đăng ký</button></hr>
            <button class="btn btn-default"><a href="user/login.htm">Quay lại login</a></button>
                 
	</div>
       
         

               
</form:form>

</div>
        <div class="text-center">
          <a class="d-block small mt-3" href="user/login.htm">Login Page</a>
          <a class="d-block small" href="user/change.htm">Change Password?</a>
        </div>
      </div>
    </div>
  </div>
  <!-- Bootstrap core JavaScript-->
  <script src="vendor/jquery/jquery.min.js"></script>
  <script src="vendor/bootstrap/js/bootstrap.bundle.min.js"></script>
  <!-- Core plugin JavaScript-->
  <script src="vendor/jquery-easing/jquery.easing.min.js"></script>
</body>

</html>