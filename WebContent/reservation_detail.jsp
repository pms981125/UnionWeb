<%@page import="vo.ReservationVO"%>
<%@page import="vo.MemberVO"%>
<%@page import="java.time.format.DateTimeFormatter"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
	MemberVO user = (MemberVO) request.getAttribute("user");
	ReservationVO reservation = (ReservationVO) request.getAttribute("reservation");
	DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy년 MM월 dd일 HH시 mm분");
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
<title>Detail Reservation</title>
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
	<%-- 테스트용
	${ user } <br />
	${ reservation } <br />
	--%>
	 <div class="container mt-md-5" align="center">
		<div class="jumbotron">
			<h1>예약 일정 조회</h1>
		</div>
		<div class="form-group input-group">
			<div class="input-group-prepend"><span class="input-group-text">이름</span></div>
			<input type="text" class="form-control" readonly value="${ reservation.getName() }"/>
		</div>
		<div class="form-group input-group">
			<div class="input-group-prepend"><span class="input-group-text">예약자</span></div>
			<input type="text" class="form-control" readonly value="${ reservation.getReservationPersonName() }"/>
		</div>
		<div class="form-group input-group">
			<div class="input-group-prepend"><span class="input-group-text">예약일</span></div>
			<input type="text" class="form-control" readonly value="<%= reservation.getReservationDate().format(formatter) %>"/>
		</div>
		<div class="form-group input-group">
			<div class="input-group-prepend"><span class="input-group-text">인원</span></div>
			<input type="text" class="form-control" readonly value="${ reservation.getReservationPeopleNum() }"/>
		</div>
		<div class="form-group input-group">
			<div class="input-group-prepend"><span class="input-group-text">장소</span></div>
			<input type="text" class="form-control" readonly value="${ reservation.getReservationLocationName() }"/>
		</div>
		<div class="form-group input-group">
			<div class="form-group input-group mt-sm-1 justify-content-center">
				<button type="button" class="btn btn-primary float-right login-btn" onclick="javascript:modifyReservationForm('${ user.getId() }','${ reservation.getNo() }')">수정</button>
				<button type="button" class="btn btn-success float-right login-btn ml-sm-2" onclick="javascript:getReservationList('${ user.getId() }')">이전</button>
				<button type="button" class="btn btn-danger float-right login-btn ml-sm-2" onclick="javascript:deleteReservation('${ user.getId() }','${ reservation.getNo() }')">삭제</button>
			</div>
		</div>
	</div>
	<script>
		function modifyReservationForm(id, no){
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
		    f.setAttribute('action', 'modifyReservationForm.do');
			document.body.appendChild(f);
			f.submit();
		}
	
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
		
		function deleteReservation(id, no){
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
		    f.setAttribute('action', 'deleteReservation.do');
			document.body.appendChild(f);
			f.submit();
		}
	</script>
</body>
</html>