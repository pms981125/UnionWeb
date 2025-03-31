<%@page import="vo.MemberVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
	MemberVO user = (MemberVO) request.getAttribute("user");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Setting</title>
<!-- <link rel="stylesheet" href="./css/nav.css" /> -->
<link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.7.0/css/all.css" 
   integrity="sha384-lZN37f5QGtY3VHgisS14W3ExzMWZxybE1SJSEsQp9S+oqd12jhcu+A56Ebc1zFSJ"
   crossorigin="anonymous">  
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script> 
<link href="https://cdn.jsdelivr.net/npm/bootstrap@4.5.2/dist/css/bootstrap.min.css" rel="stylesheet">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.5.2/dist/js/bootstrap.bundle.min.js"></script>
<style>
.form-control {
	color: black;
}

.gradient-custom {
	background-color: #2266d5;
}

h1 {
	color: #3276e5;
}

.card {
	width: 500px;
	height: 700px;
	justify-content: center;
}

.card-body {
	flex: none;
}
</style>
</head>
<body class="gradient-custom">
	<section class="d-flex vh-100">
    <div class="container-fluid row justify-content-center align-content-center">
    	<div class="card bg-dark" style="border-radius: 1rem;">
    		<div class="card-body p-5 text-center">
    			<h1 class="mb-3">회원 정보 수정</h1>
		    	<div class = "mb-2">
					<form action="updateUser.do" method="post">
						<div class="container mb-2" >
							<input type="hidden" name="originalId" value="${ user.getId() }" />
							<label class="form-label text-white">이름</label>
							<input type="text" name="name" value="${ user.getName() }" class="form-control"/>
						</div>
						<div class="container mb-2">
							<label class="form-label text-white">ID</label>
							<input type="text" name="id" value="${ user.getId() }" class="form-control"/>
						</div>
						<div class="container mb-2">
							<label class="form-label text-white">기존 비밀번호 확인</label>
							<input type="password" name="originalPassword" placeholder="기존 비밀번호 확인" class="form-control" id="originalPassword"/>
						</div>
						<div class="container mb-2">
							<label class="form-label text-white">새 비밀번호</label>
							<input type="text" name="password" placeholder="새 비밀번호" required class="form-control"/>
						</div>
						<div class="container mb-2">
							<label class="form-label text-white">생년월일</label>
							<input type="date" name="birth" value="${ user.getBirth() }" class="form-control"/>
						</div>
						<div class="container mb-2">
							<label class="form-label mr-2 text-white">성별</label>
							<% if(user.getGender().equals("Male")) { %>
								<input type="radio" name="gender" value="Male" checked /><span class="text-white"> 남성 </span> 
								<input type="radio" name="gender" value="Female"/><span class="text-white"> 여성 </span>
								<input type="radio" name="gender" value="null"/><span class="text-white"> 비공개 </span>
							<% } else if(user.getGender().equals("Female")) { %>
								<input type="radio" name="gender" value="Male" /><span class="text-white"> 남성 </span>
								<input type="radio" name="gender" value="Female" checked/><span class="text-white"> 여성 </span> 
								<input type="radio" name="gender" value="null"/><span class="text-white"> 비공개 </span>
							<% } else { %>
								<input type="radio" name="gender" value="Male" /><span class="text-white"> 남성 </span>
								<input type="radio" name="gender" value="Female"/><span class="text-white"> 여성 </span>
								<input type="radio" name="gender" value="null" checked/><span class="text-white"> 비공개 </span>
							<% } %>
						</div>
						<div class="container mb-2" align="center">
							<input type="submit" value="회원정보 수정" class="btn btn-primary"/>
							<button type="button" class="btn btn-success ml-sm-2" onclick="javascript:goHome('${ user.getId() }','${ user.getPassword() }')">홈으로</button>
						</div>	
						<div class="container" align="center">
							<button type="button" class="btn btn-danger" onclick="javascript:withdraw('${ user.getId() }')">계정 탈퇴</button>
						</div>	
					</form>
				</div>
			</div>
		</div>
    </div>
	</section>
	<!-- <script src="./js/nav.js"></script> -->
	<script>
		function goHome(id, password){
			let f = document.createElement('form');
			
			let obj;
			obj = document.createElement('input');
			obj.setAttribute('type', 'hidden');
			obj.setAttribute('name', 'id');
			obj.setAttribute('value', id);
			
			let obj2;
			obj2 = document.createElement('input');
			obj2.setAttribute('type', 'hidden');
			obj2.setAttribute('name', 'password');
			obj2.setAttribute('value', password);
						    
			f.appendChild(obj);
			f.appendChild(obj2);
		    f.setAttribute('method', 'post');
		    f.setAttribute('action', 'logIn.do');
			document.body.appendChild(f);
			f.submit();
		}
		
		function withdraw(id){
			let f = document.createElement('form');
			
			let obj;
			obj = document.createElement('input');
			obj.setAttribute('type', 'hidden');
			obj.setAttribute('name', 'id');
			obj.setAttribute('value', id);
			
			let obj2;
			let val = document.getElementById('originalPassword').value;
			obj2 = document.createElement('input');
			obj2.setAttribute('type', 'hidden');
			obj2.setAttribute('name', 'password');
			obj2.setAttribute('value', val);
						    
			f.appendChild(obj);
			f.appendChild(obj2);
		    f.setAttribute('method', 'post');
		    f.setAttribute('action', 'withdraw.do');
			document.body.appendChild(f);
			f.submit();
		}
	</script>
</body>
</html>