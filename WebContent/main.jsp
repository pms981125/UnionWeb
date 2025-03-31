<%@page import="vo.MemberVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	MemberVO user = (MemberVO) request.getAttribute("user");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="./css/nav.css" />
<link rel="stylesheet" href="./css/main.css" />
<title>Home</title>
</head>
<body>
	<%-- <%= user %> <br />
	${ user } <br /> --%>
	<nav class="navbar">
		<div class="nav_logo">
	         <span>${ user.getName() }</span>님 환영합니다.
      	</div>
      	<ul class="nav_menu">
        	<%-- <li><a class="nav" href="javascript:logIn('${ user.getId() }','${ user.getPassword() }')">Home</a></li> --%>
        	<li class="selected"><a class="nav selected" href="javascript:logOut()" >Log out</a></li>
        	<li><a class="nav" href="javascript:getReservationList('${ user.getId() }')">Reservation</a></li>
        	<li><a class="nav" href="javascript:getBoardList('${ user.getId() }')">Bulletin Board</a></li>
        	<li><a class="nav" href="javascript:getMemberList('${ user.getId() }')">Member</a></li>
        	<li><a class="nav" href="javascript:getChattingRoomList('${ user.getId() }')">Chat</a></li>
        	<li><a class="nav" href="javascript:goSettingPage('${ user.getId() }')">Setting</a></li>
      	</ul>
    </nav>
    <!-- <div class="f-container">
		<div class="flex-container">
	        <div class="flex-item">
	        	<img src="./image/pocket_watch.jpg" alt="" class="addImage" id ="image" width="100%"/>
	        	<label class="">예약</label>
	        </div>
	        <div class="flex-item">
	        	<img src="./image/pocket_watch.jpg" alt="" class="addImage" id ="image" width="100%"/>
	        </div>
	    </div>
		<div class="flex-container">
	        <div class="flex-item">
	        	<img src="./image/pocket_watch.jpg" alt="" class="addImage" id ="image" width="100%"/>
	        </div>
	        <div class="flex-item">
	        	<img src="./image/pocket_watch.jpg" alt="" class="addImage" id ="image" width="100%"/>
	        </div>
	    </div>
    </div> -->
	<script src="./js/nav.js"></script>
</body>
</html> 