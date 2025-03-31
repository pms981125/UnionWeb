<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="vo.MemberVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
	MemberVO user = (MemberVO) request.getAttribute("user");
	List<MemberVO> userList = (ArrayList<MemberVO>) request.getAttribute("userList");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.7.0/css/all.css" 
   integrity="sha384-lZN37f5QGtY3VHgisS14W3ExzMWZxybE1SJSEsQp9S+oqd12jhcu+A56Ebc1zFSJ"
   crossorigin="anonymous"> 
<link rel="stylesheet" href="./css/nav.css" />
<link rel="stylesheet" href="./css/button.css" />
<link rel="stylesheet" href="./css/pagination.css" />
<link rel="stylesheet" href="./css/form_inline.css" />
<link rel="stylesheet" href="./css/input_group.css" />
<link rel="stylesheet" href="./css/margin.css" />
<link rel="stylesheet" href="./css/container&grid.css" />
<link rel="stylesheet" href="./css/table.css" />
<link rel="stylesheet" href="./css/user_list_table2.css" />
<title>View Member List</title>
<style>
/* .user_list_table thead th {
	font-weight:bolder;
	color:#4D5B67;
	background-color:#BDBEC0;
    text-align:center;
    vertical-align:top;
} */
.table {
  	border-collapse: separate;
  	border-spacing: 0;
  	margin: 30px auto;
  	width:80%;
  	text-align: center;
}

.table thead th {
  	background: #42446e;
  	color: #fff;
  	text-align: center;
}

a {
	color:#4D5B67;
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
	<%-- <%= user %> <br />
	<%= userList %> <br />
	${ user } <br />
	${ userList } <br />
	memberList --%>
	<nav class="navbar">
		<div class="nav_logo">
	         <span>회원</span> 조회
      	</div>
      	<ul class="nav_menu">
        	<li><a class="nav" href="javascript:logIn('${ user.getId() }','${ user.getPassword() }')">Home</a></li>
        	<li><a class="nav" href="javascript:getReservationList('${ user.getId() }')">Reservation</a></li>
        	<li><a class="nav" href="javascript:getBoardList('${ user.getId() }')">Bulletin Board</a></li>
        	<li class="selected"><a class="nav selected" href="javascript:getMemberList('${ user.getId() }')">Member</a></li>
        	<li><a class="nav" href="javascript:getChattingRoomList('${ user.getId() }')">Chat</a></li>
        	<li><a class="nav" href="javascript:goSettingPage('${ user.getId() }')">Setting</a></li>
        	<!-- <li><a class="nav" href="" id="logOut">Log out</a></li> -->
      	</ul>
    </nav>
    
    <table border="0" cellpadding="0" cellspacing="0" class="table table-hover user_list_table">
    	<thead>
    		<tr>
    			<th>번호</th> <!-- 유저번호 X -->
    			<th>이름</th>
    			<th>조회</th>
    			<th>DM</th>
    		</tr>
    	</thead>
    	<tbody>
    	<% for(int i = 0; i < userList.size(); i++) { %>
    		<tr>
    			<td><%= i + 1 %></td> <!-- 유저번호 X -->
    			<td><%= userList.get(i).getName() %></td>
    			<td><a href="javascript:getUserDetail('${ user.getId() }','<%= userList.get(i).getId() %>')"><i class="fas fa-search"></i></a></td>
    			<td><a href="javascript:createChattingRoom('${ user.getId() }','<%= userList.get(i).getId() %>','${ user.getName() }','<%= userList.get(i).getName() %>')"><i class="fas fa-comments"></i></a></td>
    		</tr>
    	<% } %>
    	</tbody>
    	<!-- <tfoot>페이징 처리 구역?</tfoot> -->
    </table>
    <div class="container" align="center">
		<ul class="pagination">
			<c:if test="${ pageInfo.getStartPage() != 1 }">
				<li class="page-item page-link"><a href="javascript:getMemberList('${ user.getId() }','1')"><i class="fas fa-fast-backward"></i></a></li>
				<li class="page-item page-link"><a href="javascript:getMemberList('${ user.getId() }','${ pageInfo.getStartPage() - 10 }')"><i class="fas fa-backward"></i></a></li>
			</c:if>
			
			<c:forEach var="page_num" begin="${ pageInfo.getStartPage() }" end="${ pageInfo.getEndPage() }">
				<li class="page-item page-link ${ param.p == page_num ? 'bg-warning' : '' }"><a href="javascript:getMemberList('${ user.getId() }','${ page_num }')">${ page_num }</a></li>
			</c:forEach>
			
			<c:if test="${ pageInfo.getEndPage() < pageInfo.getTotalPage() }">
				<li class="page-item page-link"><a href="javascript:getMemberList('${ user.getId() }','${ pageInfo.getStartPage() + 10 }')"><i class="fas fa-forward"></i></a></li>
				<li class="page-item page-link"><a href="javascript:getMemberList('${ user.getId() }','${ pageInfo.getTotalPage() }')"><i class="fas fa-fast-forward"></i></a></li>
			</c:if>
		</ul>
	</div>
	<div aria-hidden="true" class="horizontal-line">
		<form action="getMemberList.do" method="post" class="form-inline mt-3" >
			<div class="container">
				<input type="hidden" value="${ user.getId() }" name="id" />
				<input type="text" name="query" size="30" class="form-control col-sm-3 me-2" value="${ param.query }" placeholder="이름으로 검색" />
				<button type="submit" class="btn btn-primary col-sm-1" >검색</button>
			</div>
		</form>
	</div>
    <script src="./js/nav.js"></script>
    <script>
    	function getUserDetail(id, targetId) {
    		let f = document.createElement('form');
    		
    		let obj;
    		obj = document.createElement('input');
    		obj.setAttribute('type', 'hidden');
    		obj.setAttribute('name', 'id');
    		obj.setAttribute('value', id);
    		
    		let obj2;
    		obj2 = document.createElement('input');
    		obj2.setAttribute('type', 'hidden');
    		obj2.setAttribute('name', 'targetId');
    		obj2.setAttribute('value', targetId);
    					    
    		f.appendChild(obj);
    		f.appendChild(obj2);
    		f.setAttribute('method', 'post');
    		f.setAttribute('action', 'getUserDetail.do');
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
    	
		function getMemberList(id, page){
			    let f = document.createElement('form');
			    
			    let obj;
			    obj = document.createElement('input');
			    obj.setAttribute('type', 'hidden');
			    obj.setAttribute('name', 'id');
			    obj.setAttribute('value', id);
			
			    let obj2;
			    obj2 = document.createElement('input');
			    obj2.setAttribute('type', 'hidden');
			    obj2.setAttribute('name', 'page');
			    obj2.setAttribute('value', page);
						    
			    f.appendChild(obj);
			    f.appendChild(obj2);
			    f.setAttribute('method', 'post');
			    f.setAttribute('action', 'getMemberList.do');
			    document.body.appendChild(f);
			    f.submit();
			}
    </script>
</body>
</html>