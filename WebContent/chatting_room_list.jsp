<%@page import="service.LogInService"%>
<%@page import="vo.ContentChattingRoomVO"%>
<%@page import="service.GetChattingRoomService"%>
<%@page import="vo.ChattingRoomVO"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="vo.MemberVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
	/* MemberVO user = LogInService.getInstance().getUserById(request.getParameter("id")); */
	MemberVO user = (MemberVO) request.getAttribute("user");
	List<String> pcrl = (ArrayList<String>) request.getAttribute("pcrl");
	List<ChattingRoomVO> chattingRoomList = (ArrayList<ChattingRoomVO>) request.getAttribute("chattingRoomList");
	List<ContentChattingRoomVO> contentChattingRoomList = (ArrayList<ContentChattingRoomVO>) request.getAttribute("contentChattingRoomList");
	int roomNum = (int) request.getAttribute("roomNum");
	
	String content;
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
<title>View Chatting Room List</title>
<link rel="stylesheet" href="./css/nav.css" />
<link rel="stylesheet" href="./css/table.css" />
<link rel="stylesheet" href="./css/form_inline.css" />
<link rel="stylesheet" href="./css/container&grid.css" />
<link rel="stylesheet" href="./css/margin.css" />
<link rel="stylesheet" href="./css/button.css" />
<link rel="stylesheet" href="./css/input_group.css" />
<link rel="stylesheet" href="./css/modal.css" />
<link rel="stylesheet" href="./css/chatting_room.css" />
<style>
body {
	margin: 8px;
	font-family: sans-serif;
}

.table tr th {
/* 	border: 1px solid black;
	border-top: 1px solid black; */
	text-align: center;
}

.table tr td {
	border-left:  1px solid #dee2e6;
	border-right: 1px solid #dee2e6;
	border-bottom: 1px solid #dee2e6;
}

a {
	text-decoration: none;
	color: #212529;
}

a:hover {
	text-decoration: none;
	color: #212529;
}

.action_menu {
	display: block;
}

.navbar {
	display: flex;
  	justify-content: space-between;
  	align-items: center;
  	background-color: var(--background-color);
  	padding: 8px 12px;
  	height: 88px;
}

nav ul {
	margin-top: 16px;
}

.horizontal-line {
    border-top: 1px solid rgba(0, 0, 0, 0.3);
    margin-top: 20px; /* 필요에 따라 여백 설정 */
    margin-bottom: 20px; /* 필요에 따라 여백 설정 */
	position: absolute;
    width: 99%;
    top: 75%;
}

.horDiv {
	position: relative;
}

form.hidden {
	display: none;
}

div.topContainer {
	margin-top: 30px;
}

table.table thead th {
	background: #7F7FD5;
	background: -webkit-linear-gradient(to right, #91EAE4, #86A8E7, #7F7FD5);
    background: linear-gradient(to right, #91EAE4, #86A8E7, #7F7FD5);
}
</style>
</head>
<body>
	<nav class="navbar">
		<div class="nav_logo">
	        <span>${ user.getName() }</span>님의 채팅방
      	</div>
      	<ul class="nav_menu">
        	<li><a class="nav" href="javascript:logIn('${ user.getId() }','${ user.getPassword() }')">Home</a></li>
        	<li><a class="nav" href="javascript:getReservationList('${ user.getId() }')">Reservation</a></li>
        	<li><a class="nav" href="javascript:getBoardList('${ user.getId() }')">Bulletin Board</a></li>
        	<li><a class="nav" href="javascript:getMemberList('${ user.getId() }')">Member</a></li>
        	<li class="selected"><a class="nav selected" href="javascript:getChattingRoomList('${ user.getId() }')">Chat</a></li>
        	<li><a class="nav" href="javascript:goSettingPage('${ user.getId() }')">Setting</a></li>
      	</ul>
    </nav>
	<%
		if(roomNum != 0) {
	%>
		<div class="container col-9 topContainer">
			<table class="table table-hover">
				<thead>
					<tr>
						<th>채팅방 이름</th>
						<th>최근 메시지</th>
					</tr>
				</thead>
				<tbody>
				<% for(int i = 0; i < roomNum; i++) { %>
					<tr onclick="javascript:getChattingRoom(<%= pcrl.get(i) %>,'${ user.getId() }',<%= i %>)">
						<td width="100px" align="center"><a href="javascript:getChattingRoom(<%= pcrl.get(i) %>,'${ user.getId() }',<%= i %>)"><%= chattingRoomList.get(i).getName() %></a></td>
						<% try {
							content = contentChattingRoomList.get(i).getMessage().getLast();
						} catch(Exception e) {
							content = "채팅방 메시지가 없습니다.";
							e.printStackTrace();
						} %>
						<td width="200px" align="center"><%= content %></td>
					</tr>
					<div id="modalContainer" class="hidden no<%= i %>">
						<div class="container" align="center">
							<div class="container-fluid h-100 mt-4">
								<div class="justify-content-center h-100">
									<div class="col-md-8 col-xl-6 chat">
										<div class="card">
											<div class="card-header msg_head">
												<div class="d-flex bd-highlight" >
													<div class="user_info">
														<span><%= chattingRoomList.get(i).getName() %></span>
													</div>
												</div>
												<span id="action_menu_btn"><a href="javascript:menu(<%= i %>)"><i class="fas fa-ellipsis-v"></i></a></span>
												<div class="action_menu hidden" id="action_menu<%= i %>">
													<ul>
														<li><a href="javascript:close(<%= i %>)">닫기</a></li>
														<% if(chattingRoomList.get(i).getManager().equals(user.getId())) { %>
															<li><a href="javascript:deleteChattingRoom('${ user.getId() }', <%= chattingRoomList.get(i).getNo() %>)">채팅방 삭제</a></li>
														<% } else { %>
															<li><a href="javascript:exitChattingRoom('${ user.getId() }', '<%= chattingRoomList.get(i).getNo() %>)">채팅방 나가기</a></li>
														<% } %>
													</ul>
												</div>
											</div>
											<div class="card-body msg_card_body" id="contents<%= i %>">
											<% for(int j = 0; j < contentChattingRoomList.get(i).getNo().size(); j++) { %>
												<% if(contentChattingRoomList.get(i).getWriterId().get(j).equals(user.getId())) { %>
												<div class="d-flex justify-content-end mb-4">
													<div class="msg_cotainer_send">
														<%= contentChattingRoomList.get(i).getMessage().get(j) %>
														<!-- <span class="msg_time_send">8:55 AM, Today</span> -->
													</div>
												</div>
												<% } else { %>
												<div class="d-flex justify-content-start mb-4">
													<div class="msg_cotainer">
														<%= contentChattingRoomList.get(i).getMessage().get(j) %>
														<span class="msg_time"><%= LogInService.getInstance().getUserById(contentChattingRoomList.get(i).getWriterId().get(j)).getName() %></span>
													</div>
												</div>
												<% } %>
											<% } %>
											</div>
											<div class="card-footer">
												<div class="input-group">
													<textarea name="text" class="form-control type_msg" placeholder="Type your message..." autofocus id='textarea<%= i %>' onkeydown="isEnter(<%= chattingRoomList.get(i).getNo() %>, <%= i %>)"></textarea>
													<%-- <div class="input-group-append">
														<span class="input-group-text send_btn"><a href="javascript:sendMessage('${ user.getId() }',<%= chattingRoomList.get(i).getNo() %>,<%= i %>)"><i class="fas fa-location-arrow"></i></a></span>
													</div> --%> <!-- 전송 버튼. UI 안맞아서 삭제 -->
												</div>
											</div>
										</div>
									</div>
								</div>
							</div>
						</div>
					</div>
				<% } %>
				</tbody>
			</table>
		</div>
	<%			
		}
	%>
	<div aria-hidden="true" class="horizontal-line">
		<form action="createChattingRoom.do" class="form-inline mt-2 horDiv" method="post">
			<div class="container" align="center">
				<h3>새로운 채팅방 생성</h3>
				<input type="text" class="form-control" name="name" placeholder="채팅방 이름"/>
			</div>
			<div class="container mt-2" align="center">
				<input type="hidden" name="managerId" value="${ user.getId() }" />
				<input type="text" class="form-control" name="invitePeopleId" placeholder="초대할 사람의 Id/여러 명일 시 ,로 공백없이 입력" size="45"/>
			</div>
			<div class="container mt-2" align="center">
				<input type="submit" class="btn btn-primary" value="생성" />
			</div>
		</form>
	</div>
	
	<script src="./js/nav.js"></script>
	<script>
		function getChattingRoom(roomNo, id, i) {
			//const modal = document.getElementById('modalContainer');
			const modal = document.querySelector('.no' + i);
			modal.classList.remove('hidden');
			
			const div = document.querySelector('.horDiv');
			div.classList.add('hidden');
			
			$("#contents" + i).scrollTop($("#contents" + i)[0].scrollHeight); // 스크롤 아래로
			/* 
			let f = document.createElement('form');
			    
			let obj;
			obj = document.createElement('input');
			obj.setAttribute('type', 'hidden');
			obj.setAttribute('name', 'roomNo');
			obj.setAttribute('value', roomNo);
			    
			let obj2;
			obj2 = document.createElement('input');
			obj2.setAttribute('type', 'hidden');
			obj2.setAttribute('name', 'userId');
			obj2.setAttribute('value', id);
			    
			f.appendChild(obj);
			f.appendChild(obj2);
			f.setAttribute('method', 'post');
			f.setAttribute('action', 'getChattingRoom.do');
			document.body.appendChild(f);
			f.submit(); */
		}
		
		<% // sendMessage 후
			if(request.getAttribute("iValue") != null) {
				String iValue = (String) request.getAttribute("iValue");
				int roomNo = Integer.parseInt((String) request.getAttribute("roomNo"));
		%>
			$(window).on('load', function() { 
				getChattingRoom(<%= roomNo %>, '<%= user.getId() %>', '<%= iValue %>');
			});
		<% } %>
		
		
		function menu(i) {
			const menu = document.querySelector('#action_menu' + i);
			
			if(menu.classList.contains('hidden')) {
				menu.classList.remove('hidden');
			} else {
				menu.classList.add('hidden');
			}
		}
		
		function close(i) {
			const menu = document.querySelector('#action_menu' + i);
			const modal = document.querySelector('.no' + i);
			const div = document.querySelector('.horDiv');
			
			menu.classList.add('hidden');
			modal.classList.add('hidden');
			div.classList.remove('hidden');
		}
		
		function isEnter(no, i) {
			if(window.event.keyCode == 13) {
				this.sendMessage('<%= user.getId() %>', no, i);
			} 
		}
	
		function getChattingRoomList(id){
			let f = document.createElement('form');
			    
			let obj;
			obj = document.createElement('input');
			obj.setAttribute('type', 'hidden');
			obj.setAttribute('name', 'id');
			obj.setAttribute('value', id);
			    
			f.appendChild(obj);
			f.setAttribute('method', 'post');
			f.setAttribute('action', 'getChattingRoomList.do');
			document.body.appendChild(f);
			f.submit();
		}
		
		function deleteChattingRoom(id, roomNo){
			const div = document.querySelector('.horDiv');
			div.classList.remove('hidden');
			
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
			obj2.setAttribute('value', roomNo);
			    
			f.appendChild(obj);
			f.appendChild(obj2);
			f.setAttribute('method', 'post');
			f.setAttribute('action', 'deleteChattingRoom.do');
			document.body.appendChild(f);
			f.submit();
		}
		
		function exitChattingRoom(id, roomNo){
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
			obj2.setAttribute('value', roomNo);
			    
			f.appendChild(obj);
			f.appendChild(obj2);
			f.setAttribute('method', 'post');
			f.setAttribute('action', 'exitChattingRoom.do');
			document.body.appendChild(f);
			f.submit();
		}
		
		function sendMessage(id, roomNo, i){
			let f = document.createElement('form');
			    
			let obj;
			obj = document.createElement('input');
			obj.setAttribute('type', 'hidden');
			obj.setAttribute('name', 'userId');
			obj.setAttribute('value', id);
			    
			let obj2;
			obj2 = document.createElement('input');
			obj2.setAttribute('type', 'hidden');
			obj2.setAttribute('name', 'chattingRoomNo');
			obj2.setAttribute('value', roomNo);
			
			let obj3;
			var textareaContent = document.getElementById("textarea" + i).value;
			obj3 = document.createElement('input');
			obj3.setAttribute('type', 'hidden');
			obj3.setAttribute('name', 'message');
			obj3.setAttribute('value', textareaContent);
			
			let obj4;
			obj4 = document.createElement('input');
			obj4.setAttribute('type', 'hidden');
			obj4.setAttribute('name', 'i');
			obj4.setAttribute('value', i);
			    
			f.appendChild(obj);
			f.appendChild(obj2);
			f.appendChild(obj3);
			f.appendChild(obj4);
			f.setAttribute('method', 'post');
			f.setAttribute('action', 'sendMessage.do');
			document.body.appendChild(f);
			f.submit(); 
		}
	</script>
</body>
</html>