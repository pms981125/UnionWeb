<%@page import="service.GetChattingRoomService"%>
<%@page import="service.LogInService"%>
<%@page import="vo.MemberVO"%>
<%@page import="vo.ContentChattingRoomVO"%>
<%@page import="vo.ChattingRoomVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
	ChattingRoomVO chattingRoom = (ChattingRoomVO) request.getAttribute("chattingRoom");
	ContentChattingRoomVO contentChattingRoom = (ContentChattingRoomVO) request.getAttribute("contentChattingRoom");
	MemberVO user = (MemberVO) request.getAttribute("user");
	String id = user.getId();
	request.setAttribute("id", id);
	int messageNum = 0;
	
	try {
		GetChattingRoomService getChattingRoomService = GetChattingRoomService.getInstance();
		/* messageNum = contentChattingRoom.getNo().getLast(); */
		messageNum = getChattingRoomService.getContentCount(chattingRoom.getNo());
 	} catch(Exception e) {
 		e.printStackTrace();
 	}
	
	int limit = 10; // 표시할 메시지 갯수 제한시 사용
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
<title></title>
<link rel="stylesheet" href="./css/form_inline.css" />
<link rel="stylesheet" href="./css/container&grid.css" />
<link rel="stylesheet" href="./css/margin.css" />
<link rel="stylesheet" href="./css/button.css" />
<link rel="stylesheet" href="./css/jumbotron.css" />
<link rel="stylesheet" href="./css/chatting_room.css" />
</head>
<body>
	<div class="container" align="center">
		<%-- <div class="jumbotron">
			<h1>${ chattingRoom.getName() }</h1>
		</div> --%>
		<%-- <div class="container" align="right">
			<button type="button" class="btn btn-success me-1" onclick="javascript:getChattingRoomList('${ user.getId() }')">이전</button>
			<% if(chattingRoom.getManager().equals(user.getId())) { %>
				<button type="button" class="btn btn-danger me-3" onclick="javascript:deleteChattingRoom('${ user.getId() }', '${ chattingRoom.getNo() }')">채팅방 삭제</button>
			<% } else { %>
				<button type="button" class="btn btn-danger me-3" onclick="javascript:exitChattingRoom('${ user.getId() }', '${ chattingRoom.getNo() }')">채팅방 나가기</button>
			<% } %>
		</div> --%>
		<div class="container-fluid h-100 mt-4">
			<div class="justify-content-center h-100">
				<div class="col-md-8 col-xl-6 chat">
					<div class="card">
						<div class="card-header msg_head">
							<div class="d-flex bd-highlight" >
								<div class="user_info">
									<span>${ chattingRoom.getName() }</span>
								</div>
							</div>
							<span id="action_menu_btn"><i class="fas fa-ellipsis-v"></i></span>
							<div class="action_menu">
								<ul>
									<li><a href="javascript:getChattingRoomList('${ user.getId() }')">이전</a></li>
									<% if(chattingRoom.getManager().equals(user.getId())) { %>
										<li><a href="javascript:deleteChattingRoom('${ user.getId() }', '${ chattingRoom.getNo() }')">채팅방 삭제</a></li>
									<% } else { %>
										<li><a href="javascript:exitChattingRoom('${ user.getId() }', '${ chattingRoom.getNo() }')">채팅방 나가기</a></li>
									<% } %>
								</ul>
							</div>
						</div>
						<div class="card-body msg_card_body" id="contents">
						<% for(int i = 0; i < contentChattingRoom.getNo().size(); i++) { %>
							<% if(contentChattingRoom.getWriterId().get(i).equals(id)) { %>
							<div class="d-flex justify-content-end mb-4">
								<div class="msg_cotainer_send">
									<%= contentChattingRoom.getMessage().get(i) %>
									<!-- <span class="msg_time_send">8:55 AM, Today</span> -->
								</div>
							</div>
							<% } else { %>
							<div class="d-flex justify-content-start mb-4">
								<div class="msg_cotainer">
									<%= contentChattingRoom.getMessage().get(i) %>
									<span class="msg_time"><%= LogInService.getInstance().getUserById(contentChattingRoom.getWriterId().get(i)).getName() %></span>
								</div>
							</div>
							<% } %>
						<% } %>
						</div>
						<div class="card-footer">
							<div class="input-group">
								<textarea name="text" class="form-control type_msg" placeholder="Type your message..." autofocus id='textarea' onkeydown="isEnter()"></textarea>
								<div class="input-group-append">
									<span class="input-group-text send_btn"><a href="javascript:sendMessage('${ id }','${ chattingRoom.getNo() }')"><i class="fas fa-location-arrow"></i></a></span>
								</div>
							</div>
						</div>						
					</div>
				</div>
			</div>
		</div>
	</div>
	<%-- <form action="sendMessage.do" method="post" class="form-inline mt-4">
		<div class="container" align="center">
			<input type="hidden" name="chattingRoomNo" value="${ chattingRoom.getNo() }" />
			<input type="hidden" name="userId" value="${ id }" />
			<input type="text" class="form-control" name="message" placeholder="메시지 입력" autofocus="autofocus" size="60"/>
			<input type="submit" class="btn btn-primary ml-2" value="전송" />
		</div>
	</form> --%>
	<script>
		$(function() {
			$('#contents').scrollTop($('#contents')[0].scrollHeight) /* jsp 로드 시 스크롤 최하단으로 */
		});
		
		$(document).ready(function(){
			$('#action_menu_btn').click(function(){
				$('.action_menu').toggle();
			});
		});
		
		function isEnter() {
			if(window.event.keyCode == 13) sendMessage('${ id }','${ chattingRoom.getNo() }');
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
		
		function sendMessage(id, roomNo){
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
			var textareaContent = document.getElementById("textarea").value;
			obj3 = document.createElement('input');
			obj3.setAttribute('type', 'hidden');
			obj3.setAttribute('name', 'message');
			obj3.setAttribute('value', textareaContent);
			    
			f.appendChild(obj);
			f.appendChild(obj2);
			f.appendChild(obj3);
			f.setAttribute('method', 'post');
			f.setAttribute('action', 'sendMessage.do');
			document.body.appendChild(f);
			f.submit();
		}
	</script>
</body>
</html>