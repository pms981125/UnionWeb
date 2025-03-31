<%@page import="java.time.format.DateTimeFormatter"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="vo.ReservationVO"%>
<%@page import="vo.MemberVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
	MemberVO user = (MemberVO) request.getAttribute("user");
	List<ReservationVO> reservationList = (ArrayList) request.getAttribute("reservationList");
	DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM월 dd일 HH시 mm분");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.7.0/css/all.css" 
   integrity="sha384-lZN37f5QGtY3VHgisS14W3ExzMWZxybE1SJSEsQp9S+oqd12jhcu+A56Ebc1zFSJ"
   crossorigin="anonymous"> 
<link rel="stylesheet" href="./css/nav.css" />
<link rel="stylesheet" href="./css/container&grid.css" />
<link rel="stylesheet" href="./css/margin.css" />
<link rel="stylesheet" href="./css/button.css" />
<link rel="stylesheet" href="./css/table.css" />
<link rel="stylesheet" href="./css/reservation_list_table.css" />
<title>View Reservation List</title>
<style>
/* body {
	background-image: url("./image/pocket_watch.jpg");
	background-repeat: no-repeat;
	background-size: cover;
} */

.table {
	background-color: white;
}

.horizontal-line {
    border-top: 1px solid rgba(0, 0, 0, 0.3);
    margin-top: 20px; /* 필요에 따라 여백 설정 */
    margin-bottom: 20px; /* 필요에 따라 여백 설정 */
	position: absolute;
    width: 99%;
    top: 75%;
}
</style>
</head>
<body>
	<nav class="navbar">
		<div class="nav_logo">
	        <span>${ user.getName() }</span>님의 예약된 일정 <!-- hr높이? -->
      	</div>
      	<ul class="nav_menu">
        	<li><a class="nav" href="javascript:logIn('${ user.getId() }','${ user.getPassword() }')">Home</a></li>
        	<li class="selected"><a class="nav selected" href="javascript:getReservationList('${ user.getId() }')">Reservation</a></li>
        	<li><a class="nav" href="javascript:getBoardList('${ user.getId() }')">Bulletin Board</a></li>
        	<li><a class="nav" href="javascript:getMemberList('${ user.getId() }')">Member</a></li>
        	<li><a class="nav" href="javascript:getChattingRoomList('${ user.getId() }')">Chat</a></li>
        	<li><a class="nav" href="javascript:goSettingPage('${ user.getId() }')">Setting</a></li>
        	<!-- <li><a class="nav" href="" id="logOut">Log out</a></li> -->
      	</ul>
    </nav>
	<%-- 테스트용
	${ user } <br />
    <%= user %> <br />
	${ reservationList } <br />
    <%= reservationList %> <br />
    --%>
    <table border="0" cellpading="0" cellspacing="0" class="table table-hover reservation_list_table">
    	<thead>
    		<tr>
    			<th>번호</th>
    			<th>이름</th>
    			<th>일시</th>
    			<th>조회</th>
    			<th>삭제</th>
    		</tr>
    	</thead>
    	<tbody>
    	<% for(int i = 0; i < reservationList.size(); i++) { %>
    		<tr>
    			<td><%= i + 1 %></td>
    			<td><a href="javascript:getReservationDetail('${ user.getId() }','<%= reservationList.get(i).getNo() %>')"><%= reservationList.get(i).getName() %></a></td>
    			<td><%= reservationList.get(i).getReservationDate().format(formatter) %></td>
    			<td><a href="javascript:getReservationDetail('${ user.getId() }','<%= reservationList.get(i).getNo() %>')"><i class="fas fa-search"></i></a></td>
    			<td><a href="javascript:deleteReservation('${ user.getId() }','<%= reservationList.get(i).getNo() %>')"><i class="fas fa-trash-alt"></i></a></td>
    		</tr>
    	<% } %>
    	</tbody>
    </table>
    <div aria-hidden="true" class="horizontal-line">
	    <div class="container" align="right">
	    	<button type="button" class="btn btn-primary mt-3 ms-1" onclick="javascript:goReservationForm('${ user.getId() }')">새 예약 생성</button>
	    </div>
    </div>
    
    <script src="./js/nav.js"></script>
    <script>
		function goReservationForm(id){
			let f = document.createElement('form');
			    
			let obj;
			obj = document.createElement('input');
			obj.setAttribute('type', 'hidden');
			obj.setAttribute('name', 'id');
			obj.setAttribute('value', id);
			    
			f.appendChild(obj);
			f.setAttribute('method', 'post');
			f.setAttribute('action', 'goReservationForm.do');
			document.body.appendChild(f);
			f.submit();
		}
		
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