<%@page import="vo.MemberVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
	MemberVO user = (MemberVO) request.getAttribute("user");
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
<title>Add Reservation</title>
</head>
<body>
	<%-- 테스트용
	${ user } <br />
    <%= user %> <br />
    --%>
    <div class="container mt-md-5" align="center">
		<div class="jumbotron">
			<h1>예약 일정 생성</h1>
		</div>
		<form action="makeReservation.do" method="post">
			<input type="hidden" value="${ user.getId() }" name="id"/>
			<div class="form-group input-group">
				<div class="input-group-prepend"><span class="input-group-text">이름</span></div>
				<input type="text" class="form-control" required name="name"/>
			</div>
			<div class="form-group input-group">
				<div class="input-group-prepend"><span class="input-group-text">예약일</span></div>
				<input type="datetime-local" class="form-control" required name="reservationDate"/>
			</div>
			<div class="form-group input-group">
				<div class="input-group-prepend"><span class="input-group-text">예약자</span></div>
				<input type="text" class="form-control" readonly value="${ user.getName() }" name="reservationPersonName"/>
			</div>
			<div class="form-group input-group">
				<div class="input-group-prepend"><span class="input-group-text">인원</span></div>
				<input type="text" class="form-control" required name="reservationPeopleNum"/>
			</div>
			<div class="form-group input-group">
				<div class="input-group-prepend"><span class="input-group-text">장소</span></div> <!-- 실제론 좌표 값도 -->
				<input type="text" class="form-control" required name="reservationLocationName"/>
			</div>
			<div class="form-group input-group">
				<div class="form-group input-group mt-sm-1 justify-content-center">
					<input type="submit" value="생성" class="btn btn-primary"/>
					<button type="button" class="btn btn-success float-right login-btn ml-sm-2" onclick="javascript:getReservationList('${ user.getId() }')">이전</button>
				</div>
			</div>
		</form>
	</div>
	<script>
		function getReservationList(id){
			let f = document.createElement('form');
					    
			let obj;
		    obj = document.createElement('input');
		    obj.setAttribute('type', 'hidden');
		    obj.setAttribute('name', 'id');
		    obj.setAttribute('value', id);
					    
		    f.appendChild(obj);
		    f.setAttribute('method', 'post');
		    f.setAttribute('action', 'getReservationList.do');
			document.body.appendChild(f);
			f.submit();
		}
	</script>
</body>
</html>