<%@page import="vo.ReservationVO"%>
<%@page import="vo.MemberVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
	MemberVO user = (MemberVO) request.getAttribute("user");
	ReservationVO reservation = (ReservationVO) request.getAttribute("reservation");
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
<title>Modify Reservation</title>
</head>
<body>
	<div class="container mt-md-5" align="center">
		<div class="jumbotron">
			<h1>예약 일정 수정</h1>
		</div>
		<form action="modifyReservation.do" method="post">
			<input type="hidden" name="id" value="${ user.getId() }" />
			<input type="hidden" name="no" value="${ reservation.getNo() }" />
			<div class="form-group input-group">
				<div class="input-group-prepend"><span class="input-group-text">이름</span></div>
				<input type="text" class="form-control" name="name" value="${ reservation.getName() }" required/>
			</div>
			<div class="form-group input-group">
				<div class="input-group-prepend"><span class="input-group-text">예약자</span></div>
				<input type="text" class="form-control" readonly value="${ reservation.getReservationPersonName() }" required/>
			</div>
			<div class="form-group input-group">
				<div class="input-group-prepend"><span class="input-group-text">예약일</span></div>
				<input type="datetime-local" class="form-control" name="reservationDate" value="<%= reservation.getReservationDate() %>" required/>
			</div>
			<div class="form-group input-group">
				<div class="input-group-prepend"><span class="input-group-text">인원</span></div>
				<input type="text" class="form-control" name="reservationPeopleNum" value="${ reservation.getReservationPeopleNum() }" required/>
			</div>
			<div class="form-group input-group">
				<div class="input-group-prepend"><span class="input-group-text">장소</span></div>
				<input type="text" class="form-control" name="reservationLocationName" value="${ reservation.getReservationLocationName() }" required/>
			</div>
			<div class="form-group input-group">
				<div class="form-group input-group mt-sm-1 justify-content-center">
					<input type="submit"  class="btn btn-primary float-right login-btn" value="수정"/>
					<button type="button" class="btn btn-success float-right login-btn ml-sm-2" onclick="javascript:getReservationDetail('${ user.getId() }','${ reservation.getNo() }')">이전</button>
				</div>
			</div>
		</form>
	</div>
	<script>
		function getReservationDetail(id, no) {
			let f = document.createElement('form');
			    
			let obj;
			obj = document.createElement('input');
			obj.setAttribute('type', 'hidden');
			obj.setAttribute('name', 'id');
			obj.setAttribute('value', id);
			    
			let obj2;
			obj2 = document.createElement('input');
			obj2.setAttribute('type', 'hidden');
			obj2.setAttribute('name', 'no');
			obj2.setAttribute('value', no);
			    
			f.appendChild(obj);
			f.appendChild(obj2);
			f.setAttribute('method', 'post');
			f.setAttribute('action', 'getReservationDetail.do');
			document.body.appendChild(f);
			f.submit();
		}
	</script>
</body>
</html>