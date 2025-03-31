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
<title>Register</title>
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

input[type=radio] {
	color: white;
}
</style>
</head>
<body class="gradient-custom">
	<section class="d-flex vh-100">
    <div class="container-fluid row justify-content-center align-content-center">
    	<div class="card bg-dark" style="border-radius: 1rem;">
    		<div class="card-body p-5 text-center">
    			<h1 class="mb-3">Register</h1>
		    	<div class = "mb-2">
					<form action="register.do" method="post">
						<div class="container mb-2">
							<label class="form-label text-white">이름</label>
							<input type="text" name="name" placeholder="이름" required class="form-control"/>
						</div>
						<div class="container mb-2">
							<label class="form-label text-white">ID</label>
							<input type="text" name="id" placeholder="ID" required class="form-control"/>
						</div>
						<div class="container mb-2">
							<label class="form-label text-white">비밀번호</label>
							<input type="text" name="password" placeholder="비밀번호" required class="form-control"/>
						</div>
						<div class="container mb-2">
							<label class="form-label text-white">생년월일</label>
							<input type="date" name="birth" placeholder="나이" class="form-control"/>
						</div>
						<div class="container mb-2">
							<label class="form-label text-white mr-2">성별</label>
							<input type="radio" name="gender" value="Male"/> <span class="text-white">남성</span> 
							<input type="radio" name="gender" value="Female"/> <span class="text-white">여성</span> 
							<input type="radio" name="gender" value="null"/> <span class="text-white">비공개</span>
						</div>
						<div class="container mb-2" align="center">
							<input type="submit" value="회원가입" class="btn btn-primary"/>
							<button type="button" class="btn btn-success ml-sm-2" onclick="javascript:goLogIn()">이전</button>
						</div>	
					</form>
				</div>
			</div>
		</div>
    </div>
	</section>
	<script>
		function goLogIn(){
			let f = document.createElement('form');
					    
		    f.setAttribute('method', 'post');
		    f.setAttribute('action', 'logIn.jsp');
			document.body.appendChild(f);
			f.submit();
		}
	</script>
</body>
</html>