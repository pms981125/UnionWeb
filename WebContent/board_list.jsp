<%@page import="vo.PageInfo"%>
<%@page import="java.util.ArrayList"%>
<%@page import="vo.BoardVO"%>
<%@page import="java.util.List"%>
<%@page import="vo.MemberVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
	MemberVO user = (MemberVO) request.getAttribute("user");
	List<BoardVO> boardList = (ArrayList<BoardVO>) request.getAttribute("boardList");
	PageInfo pageInfo = (PageInfo) request.getAttribute("pageInfo");
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
<link rel="stylesheet" href="./css/board_list_table.css" />
<link rel="stylesheet" href="./css/table.css" />
<style>
table.board_list_table {
	margin-bottom: 0px;
}

.board_list_table thead th {
	font-weight:bolder;
	color:#4D5B67;
	background-color:#BDBEC0;
    text-align:center;
    vertical-align:top;
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
<title>View Board List</title>
</head>
<body>
	<nav class="navbar">
		<div class="nav_logo">
	         게시<span>판</span>
      	</div>
      	<ul class="nav_menu">
        	<li><a class="nav" href="javascript:logIn('${ user.getId() }','${ user.getPassword() }')">Home</a></li>
        	<li><a class="nav" href="javascript:getReservationList('${ user.getId() }')">Reservation</a></li>
        	<li class="selected"><a class="nav selected" href="javascript:getBoardList('${ user.getId() }')">Bulletin Board</a></li>
        	<li><a class="nav" href="javascript:getMemberList('${ user.getId() }')">Member</a></li>
        	<li><a class="nav" href="javascript:getChattingRoomList('${ user.getId() }')">Chat</a></li>
        	<li><a class="nav" href="javascript:goSettingPage('${ user.getId() }')">Setting</a></li>
        	<!-- <li><a class="nav" href="" id="logOut">Log out</a></li> -->
      	</ul>
    </nav>
	<%--
	${ user } <br />
	board <br />
	${ boardList } <br />
	<%= boardList %> <br />
	<%= pageInfo %> <br />
	${ pageInfo } <br />
	--%>
	<table border="0" cellpadding="0" cellspacing="0" class="table table-hover board_list_table">
		<thead>
			<tr>
				<th>제목</th>
				<th>작성자</th>
				<th>조회수</th>
				<th>좋아요</th>
				<th>작성일</th>
			</tr>
		</thead>
		<tbody>
		<% for(int i = 0; i < boardList.size(); i++) { %>
			<tr onclick="javascript:getBoardDetail('${ user.getId() }','<%= boardList.get(i).getNo() %>')">
				<td><%= boardList.get(i).getTitle() %></td>
				<td><%= boardList.get(i).getWriterName() %></td>
				<td><%= boardList.get(i).getReadCount() %></td>
				<td><%= boardList.get(i).getLikeCount() %></td>
				<td><%= boardList.get(i).getWriteDate() %></td>
			</tr>
		<% } %>
		</tbody>
		<!-- <tfoot></tfoot> -->
	</table>	
	<div class="container" align="center">
		<ul class="pagination">
			<c:if test="${ pageInfo.getStartPage() != 1 }">
				<li class="page-item page-link"><a href="javascript:getBoardList('${ user.getId() }','1')"><i class="fas fa-fast-backward"></i></a></li>
				<li class="page-item page-link"><a href="javascript:getBoardList('${ user.getId() }','${ pageInfo.getStartPage() - 10 }')"><i class="fas fa-backward"></i></a></li>
			</c:if>
			
			<c:forEach var="page_num" begin="${ pageInfo.getStartPage() }" end="${ pageInfo.getEndPage() }">
				<li class="page-item page-link ${ param.p == page_num ? 'bg-warning' : '' }"><a href="javascript:getBoardList('${ user.getId() }','${ page_num }')">${ page_num }</a></li>
			</c:forEach>
			
			<c:if test="${ pageInfo.getEndPage() < pageInfo.getTotalPage() }">
				<li class="page-item page-link"><a href="javascript:getBoardList('${ user.getId() }','${ pageInfo.getStartPage() + 10 }')"><i class="fas fa-forward"></i></a></li>
				<li class="page-item page-link"><a href="javascript:getBoardList('${ user.getId() }','${ pageInfo.getTotalPage() }')"><i class="fas fa-fast-forward"></i></a></li>
			</c:if>
		</ul>
	</div>
	<div aria-hidden="true" class="horizontal-line">
		<form action="getBoardList.do" method="post" class="form-inline mt-3" >
			<div class="container">
				<input type="hidden" value="${ user.getId() }" name="id" />
				<select name="find" id="find" class="form-control col-sm-2 me-1">
					<option ${ param.find == "title" ? "selected" : "" } value="title">제목</option>
					<option ${ param.find == "writerName" ? "selected" : "" } value="writerName">작성자</option>
				</select>
				<input type="text" name="query" class="form-control col-sm-8 me-2" value="${ param.query }" placeholder="검색어를 입력하세요" />
				<button type="submit" class="btn btn-primary col-sm-1 me-1" >검색</button>
				<button type="button" class="new btn btn-success col-sm-1" onclick="javascript:createBoardForm('${ user.getId() }')">게시물 작성</button>
			</div>
		</form>
	</div>
	<script src="./js/nav.js"></script>
	<script>
		function createBoardForm(id){
		    let f = document.createElement('form');
		    
		    let obj;
		    obj = document.createElement('input');
		    obj.setAttribute('type', 'hidden');
		    obj.setAttribute('name', 'id');
		    obj.setAttribute('value', id);
					    
		    f.appendChild(obj);
		    f.setAttribute('method', 'post');
		    f.setAttribute('action', 'createBoardForm.do');
		    document.body.appendChild(f);
		    f.submit();
		}
		
		function getBoardDetail(id, boardNo){
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
		    obj2.setAttribute('value', boardNo);
					    
		    f.appendChild(obj);
		    f.appendChild(obj2);
		    f.setAttribute('method', 'post');
		    f.setAttribute('action', 'getBoardDetail.do');
		    document.body.appendChild(f);
		    f.submit();
		}
		
		function getBoardList(id, page){
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
		    f.setAttribute('action', 'getBoardList.do');
		    document.body.appendChild(f);
		    f.submit();
		}
	</script>
</body>
</html>