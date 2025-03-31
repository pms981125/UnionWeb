<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.7.0/css/all.css" 
   integrity="sha384-lZN37f5QGtY3VHgisS14W3ExzMWZxybE1SJSEsQp9S+oqd12jhcu+A56Ebc1zFSJ"
   crossorigin="anonymous">  
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script> 
<link href="https://cdn.jsdelivr.net/npm/bootstrap@4.5.2/dist/css/bootstrap.min.css" rel="stylesheet">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.5.2/dist/js/bootstrap.bundle.min.js"></script>
<title>Log In</title>
<style>
.gradient-custom {
	background-color: #2266d5;
}

h1 {
	color: #3276e5;
	font-family: serif;
	font-weight: bold;
	font-style: italic;
}

.card {
	width: 400px;
	height: 550px;
	justify-content: center;
}

.card-body {
	flex: none;
}

.container-fluid {
	padding-right: 0px;
}
</style>
</head>
<body class="gradient-custom">
	<section class="d-flex vh-100">
    <div class="container-fluid row justify-content-center align-content-center">
    	<div class="card bg-dark" style="border-radius: 1rem;">
    		 <div class="card-body p-5 text-center">
    		 	<h1 class="mb-3">Pro.1</h1>
    		 	<p class="text-white-50 mt-2 mb-5">원활한 사용을 위해 로그인이 필요합니다.</p>
    		 	<div class="mb-2">
					<form action="logIn.do" method="post" name="logInForm" class="form-inline" >
						<div class="container-fluid row justify-content-center align-content-center text-center">
							<div class="container mb-2">
								<input type="text" name="id" placeholder="ID" required class="form-control" autofocus/>
							</div>
							<div class="container mb-2">
								<input type="password" name="password" placeholder="Password" required class="form-control mt-sm-2" />
							</div>
							<div class="container mt-sm-2">
								<input type="submit" value="로그인" class="btn btn-primary" />
								<button type="button" class="btn btn-success ml-sm-2" onclick="javascript:register()">회원 가입</button>
							</div>
						</div>
					</form>
    		 	</div>
			</div>
		</div>
    </div>
	</section>
	<script src="./js/logIn.js"></script>
	<script>
		function register() {
			let f = document.createElement('form');
					    
		    f.setAttribute('method', 'post');
		    f.setAttribute('action', 'register.jsp');
			document.body.appendChild(f);
			f.submit();
		}
	</script>
</body>
</html>