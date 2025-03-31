<%@page import="vo.BoardVO"%>
<%@page import="vo.MemberVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	MemberVO user = (MemberVO) request.getAttribute("user");
	BoardVO board = (BoardVO) request.getAttribute("board");
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
<style>
.addImage {
	width: 450px;
    height: 400px;
    background-color: lightgray;
    border-radius: 20px 20px / 20px 20px;
    overflow: hidden;
    margin: 0px 10px 10px 0px;
}

.imgDiv {
	display: flex;
	justify-content: center;
}
.fa-heart {
	color: red;
}
input.form-control {
	background-color: white !important;
	/* border: 0px; */
}
span {
	background-color: white !important;
}
textarea {
	background-color: white !important;
}
.container{
	width: 70%;
}
</style>
<title>View Detail Board</title>
</head>
<body>
	<%-- ${ user } <br />
	${ board } <br />
	<%= user %> <br />
	<%= board %> <br /> --%>
	<div class="container mt-md-3" align="center">
		<div class="jumbotron">
			<h3>게시물 상세 조회</h3>
		</div>
		<form action="goEditBoardForm.do" method="post" name="editboard">
			<input type="hidden" name="id" value="${ user.getId() }" />
			<input type="hidden" name="no" value="${ board.getNo() }" />
			<div class="form-group input-group">
				<div class="input-group-prepend"><span class="input-group-text">작성일</span></div>
				<input type="date" class="form-control" value="${ board.getWriteDate() }" readonly/>
				<div class="input-group-prepend"><span class="input-group-text">조회수</span></div>
				<input type="text" class="form-control" value="${ board.getReadCount() }" readonly/>
			</div>
			<div class="form-group input-group">
				<div class="input-group-prepend"><span class="input-group-text">제목</span></div>
				<input type="text" class="form-control" value="${ board.getTitle() }" readonly/>
				<div class="input-group-prepend"><span class="input-group-text">작성자</span></div>
				<input type="text" class="form-control" value="${ board.getWriterName() }" readonly/>
			</div>
			<div class="form-group input-group">
				<textarea rows="15" cols="40" class="form-control" readonly>${ board.getContents() }</textarea>
			</div>
			<div class="form-group input-group">
			<c:if test="${ board.getImageSrc() != null }">
				<div class="form-group input-group imgDiv">
					<img src="./upload/${ board.getImageSrc() }" alt="" class="addImage"/>
				</div>
			</c:if>
			<div class="form-group input-group">
				<% if(board.getLikeID() != null && board.getLikeID().contains(user.getId())) {%>
				<div class="input-group-prepend"><span class="input-group-text"><a href="javascript:like('${ user.getId() }','${ board.getNo() }')"><i class="fas fa-heart"></i></a></span></div> <!-- 속 찬 하트 -->
				<% } else { %>
				<div class="input-group-prepend"><span class="input-group-text"><a href="javascript:like('${ user.getId() }','${ board.getNo() }')"><i class="far fa-heart"></i></a></span></div> <!-- 속 빈 하트 -->
				<% } %>
				<input type="text" class="form-control col-1" value="${ board.getLikeCount() }" readonly/>
			</div>
				<div class="form-group input-group mt-sm-1 justify-content-center">
		            <input type="submit" class="btn btn-primary float-right login-btn" value="게시글 수정"/>
		</form>
		<form action="deleteBoard.do" method="post">
		<input type="hidden" name="id" value="${ user.getId() }" />
		<input type="hidden" name="no" value="${ board.getNo() }" />
		            <input type="submit" class="btn btn-danger float-right login-btn ml-sm-2" value="삭제"/>
					<button type="button" class="btn btn-success float-right login-btn ml-sm-2" onclick="javascript:getBoardList('${ user.getId() }')">이전</button>
				</div>
			</div>
		</form>
	</div>
	<script>
	    function getBoardList(id){
	    	let f = document.createElement('form');
	    			    
	    	let obj;
	        obj = document.createElement('input');
	        obj.setAttribute('type', 'hidden');
	        obj.setAttribute('name', 'id');
	        obj.setAttribute('value', id);
	    			    
	        f.appendChild(obj);
	        f.setAttribute('method', 'post');
	        f.setAttribute('action', 'getBoardList.do');
	    	document.body.appendChild(f);
	    	f.submit();
	    }
	    
	    function like(id, no) {
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
		    f.setAttribute('action', 'like.do');
			document.body.appendChild(f);
			f.submit();
	    }
	</script>
</body>
</html>