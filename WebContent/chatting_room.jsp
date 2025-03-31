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
	
	int limit = 10;
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title></title>
<link rel="stylesheet" href="./css/table.css" />
<link rel="stylesheet" href="./css/form_inline.css" />
<link rel="stylesheet" href="./css/container&grid.css" />
<link rel="stylesheet" href="./css/margin.css" />
<link rel="stylesheet" href="./css/button.css" />
<link rel="stylesheet" href="./css/input_group.css" />
<link rel="stylesheet" href="./css/jumbotron.css" />
<style>
.jumbotron h1 {
  line-height: 0.4;
}
.table {
  width: 80%;
}
.table td {
  padding: 0.75rem;
  vertical-align: top;
  border-top: 0px;
}
</style>
</head>
<body>
	<div class="container" align="center">
		<div class="jumbotron">
			<h1>${ chattingRoom.getName() }</h1>
		</div>
		<div class="container" align="right">
			<button type="button" class="btn btn-success me-1" onclick="javascript:getChattingRoomList('${ user.getId() }')">이전</button>
			<% if(chattingRoom.getManager().equals(user.getId())) { %>
				<button type="button" class="btn btn-danger me-3" onclick="javascript:deleteChattingRoom('${ user.getId() }', '${ chattingRoom.getNo() }')">채팅방 삭제</button>
			<% } else { %>
				<button type="button" class="btn btn-danger me-3" onclick="javascript:exitChattingRoom('${ user.getId() }', '${ chattingRoom.getNo() }')">채팅방 나가기</button>
			<% } %>
		</div>
		<hr />
		<table class="table">
		<% if(messageNum > limit) { %> 
			<% for(int i = messageNum - limit; i < messageNum; i++) { %>
				<% if(contentChattingRoom.getWriterId().get(i).equals(id)) { %>
					<tr>
						<td align="right"><%= contentChattingRoom.getMessage().get(i) %></td> 
					</tr>
				<% } else { %>
					<tr>
						<td align="left">
							<%= LogInService.getInstance().getUserById(contentChattingRoom.getWriterId().get(i)).getName() %> : <%= contentChattingRoom.getMessage().get(i) %> <!-- 스크롤? -->
						</td>
					</tr>
				<% } %>
			<% } %>
		<% } else { %>
			<% for(int i = 0; i < contentChattingRoom.getNo().size(); i++) { %>
				<% if(contentChattingRoom.getWriterId().get(i).equals(id)) { %>
					<tr>
						<td align="right"><%= contentChattingRoom.getMessage().get(i) %></td>
					</tr>
				<% } else { %>
					<tr>
						<td align="left">
							<!-- 자신의 보낸 메시지가 아닌 메시지는 보낸 사람의 이름과 함께 표시 -->
							<%= LogInService.getInstance().getUserById(contentChattingRoom.getWriterId().get(i)).getName() %> : <%= contentChattingRoom.getMessage().get(i) %>
						</td>
					</tr>
				<% } %>
			<% } %>
		<% } %>
		</table>
		<hr />
	</div>
	<form action="sendMessage.do" method="post" class="form-inline">
		<div class="container" align="center">
			<input type="hidden" name="chattingRoomNo" value="${ chattingRoom.getNo() }" />
			<input type="hidden" name="userId" value="${ id }" />
			<input type="text" class="form-control" name="message" placeholder="메시지 입력" autofocus="autofocus" size="60"/>
			<input type="submit" class="btn btn-primary" value="전송" />
		</div>
	</form>
	<script>
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
	</script>
</body>
</html>