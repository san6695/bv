
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>


<!DOCTYPE html>
<html lang="en">

<head>
  <meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
  <meta name="description" content="">
  <meta name="author" content="">
  <title>SB Admin - Start Bootstrap Template</title>
   <base href="${pageContext.servletContext.contextPath}/">
  <!-- Bootstrap core CSS-->
  <link href="vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">
  <!-- Custom fonts for this template-->
  <link href="vendor/font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css">
  <!-- Custom styles for this template-->
  <link href="css/sb-admin.css" rel="stylesheet">
</head>

<body class="bg-dark">
  <div class="container">
    <div class="card card-login mx-auto mt-5">
      <div class="card-header">Change Password</div>
      <div class="card-body">
          ${message}
       <form action="user/change.htm" method="post">
		<div class="form-group">
			<label>Username</label>
			<input name="id" class="form-control" />
		</div>
		
		<div class="form-group">
			<label>Mật khẩu cũ</label>
			<input name="password" class="form-control" type="password"/>
		</div>
				
		<div class="form-group">
			<label>Mật khẩu mới</label>
			<input name="newpass1" class="form-control" type="password"/>
		</div>
		
		<div class="form-group">
			<label>Nhập lại mật khẩu mới</label>
			<input name="newpass2" class="form-control" type="password"/>
		</div>
		
		<div class="form-group">
			<button class="btn btn-default">Thay đổi</button>
		</div>
	</form>

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



