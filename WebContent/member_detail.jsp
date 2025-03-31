<%@page import="vo.MemberVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
	MemberVO user = (MemberVO) request.getAttribute("user");
	MemberVO targetUser = (MemberVO) request.getAttribute("targetUser");
%>
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
<title>View Member Detail</title>
<style>
input.form-control {
	background-color: white !important;
	/* border: 0px; */
}
span {
	background-color: white !important;
}
</style>
</head>
<body>
	<%-- <%= user %> <br />
	<%= targetUser %> <br />
	${ user } <br />
	${ targetUser } <br /> --%>
	<div class="container mt-md-3" align="center">
		<div class="jumbotron">
			<h3>회원 상세 조회</h3>
		</div>
		<div class="form-group input-group">
			<div class="input-group-prepend"><span class="input-group-text">이름</span></div>
			<input type="text" class="form-control" value="${ targetUser.getName() }" readonly/>
		</div>
		<div class="form-group input-group">
			<div class="input-group-prepend"><span class="input-group-text">ID</span></div>
			<input type="text" class="form-control" value="${ targetUser.getId() }" readonly/>
		</div>
		<div class="form-group input-group">
			<div class="input-group-prepend"><span class="input-group-text">가입일</span></div>
			<input type="text" class="form-control" value="${ targetUser.getRegisterDate() }" readonly/>
		</div>
		<% if(targetUser.getBirth() == null) { %>
		<div class="form-group input-group">
			<div class="input-group-prepend"><span class="input-group-text">생년월일</span></div>
			<input type="text" class="form-control" value="비공개" readonly/>
		</div>
		<% } else { %>
		<div class="form-group input-group">
			<div class="input-group-prepend"><span class="input-group-text">생년월일</span></div>
			<input type="text" class="form-control" value="<%= targetUser.getBirth() %>" readonly/>
		</div>
		<% } %>
		<% if(targetUser.getAge() == 0) { %>
		<div class="form-group input-group">
			<div class="input-group-prepend"><span class="input-group-text">나이</span></div>
			<input type="text" class="form-control" value="비공개" readonly/>
		</div>
		<% } else { %>
		<div class="form-group input-group">
			<div class="input-group-prepend"><span class="input-group-text">나이</span></div>
			<input type="text" class="form-control" value="<%= targetUser.getAge() %>" readonly/>
		</div>
		<% } %>
		<div class="form-group input-group">
			<div class="input-group-prepend"><span class="input-group-text">성별</span></div>
			<input type="text" class="form-control" value="<%= targetUser.getGender() %>" readonly/>
		</div>
		<div class="form-group input-group">
			<div class="form-group input-group mt-sm-1 justify-content-center">
				<button type="button" class="btn btn-primary float-right login-btn ml-sm-2" onclick="javascript:createChattingRoom('${ user.getId() }','<%= targetUser.getId() %>','${ user.getName() }','<%= targetUser.getName() %>')">DM</button>
				<button type="button" class="btn btn-success float-right login-btn ml-sm-2" onclick="javascript:getMemberList('${ user.getId() }')">이전</button>
			</div>
		</div>
	</div>
	<script>
		function getMemberList(id){
			let f = document.createElement('form');
					    
			let obj;
		    obj = document.createElement('input');
		    obj.setAttribute('type', 'hidden');
		    obj.setAttribute('name', 'id');
		    obj.setAttribute('value', id);
					    
		    f.appendChild(obj);
		    f.setAttribute('method', 'post');
		    f.setAttribute('action', 'getMemberList.do');
			document.body.appendChild(f);
			f.submit();
		}
		
		function createChattingRoom(id, targetId, name, targetName) {
			let f = document.createElement('form');
			
			let obj;
			obj = document.createElement('input');
			obj.setAttribute('type', 'hidden');
			obj.setAttribute('name', 'managerId');
			obj.setAttribute('value', id);
			
			let obj2;
			obj2 = document.createElement('input');
			obj2.setAttribute('type', 'hidden');
			obj2.setAttribute('name', 'invitePeopleId');
			obj2.setAttribute('value', targetId);
			
			let obj3;
			obj3 = document.createElement('input');
			obj3.setAttribute('type', 'hidden');
			obj3.setAttribute('name', 'name');
			obj3.setAttribute('value', name + ", " + targetName + "의 채팅방");
						    
			f.appendChild(obj);
			f.appendChild(obj2);
			f.appendChild(obj3);
			f.setAttribute('method', 'post');
			f.setAttribute('action', 'createChattingRoom.do');
			document.body.appendChild(f);
			f.submit();
		}
	</script>
</body>
</html>