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
	width: 350px;
    height: 300px;
    background-color: lightgray;
    border-radius: 20px 20px / 20px 20px;
    overflow: hidden;
    margin: 0px 10px 10px 0px;
}

.imgDiv {
	display: flex;
	justify-content: center;
}
</style>
<title>Edit Board</title>
</head>
<body>
	<div class="container mt-md-3" align="center">
		<form action="editBoard.do" method="post" name="editBoardForm" enctype="multipart/form-data"> <!-- req.getParameter() 주의 -->
			<input type="hidden" name="id" value="${ user.getId() }" />
			<input type="hidden" name="no" value="${ board.getNo() }" />
			<div class="form-group input-group">
				<div class="input-group-prepend"><span class="input-group-text">제목</span></div>
				<input type="text" class="form-control" name="title" id="title" value="${ board.getTitle() }"/>
			</div>
			<div class="form-group input-group">
				<div class="input-group-prepend"><span class="input-group-text">내용</span></div>
				<textarea name="contents" id="contents" rows="15" cols="40" class="form-control">${ board.getContents() }</textarea>
			</div>
			<c:if test="${ board.getImageSrc() != null }">
				<div class="form-group input-group imgDiv">
					<img src="./upload/${ board.getImageSrc() }" alt="" class="addImage" id ="image" width="100%"/>
				</div>
				<div class="form-group input-group imgDiv">
					<input type="file" accept="image/*" onchange="changeImage(this)" name="img" alt="이미지 선택" /> <!-- 이미지 선택 취소 구현? -->
				</div>
			</c:if>
			<div class="form-group input-group">
			<c:if test="${ board.getImageSrc() == null }">
				<div class="form-group input-group imgDiv">
					<div class="addImage" id="image-show"></div>
					<div class="form-group input-group imgDiv">
						<input type="file" accept="image/*" onchange="loadFile(this)" name="img" alt="이미지 선택"/>
					</div>
				</div>
			</c:if>
					
				<div class="form-group input-group mt-sm-1 justify-content-center">
		            <input type="submit" class="btn btn-primary float-right login-btn" value="게시글 수정"/>
					<button type="button" class="btn btn-success float-right login-btn ml-sm-2" onclick="javascript:getBoardDetail('${ user.getId() }','${ board.getNo() }')">이전</button>
				</div>
			</div>
		</form>
	</div>
	<script>
		function changeImage(input) {
			let file = input.files[0];
			let img = document.getElementById('image');
			img.src = URL.createObjectURL(file);
        }
		
		function loadFile(input) {
            let file = input.files[0]; // 선택파일 가져오기

            let newImage = document.createElement("img"); //새 이미지 태그 생성

            //이미지 source 가져오기
            newImage.src = URL.createObjectURL(file);
            newImage.style.width = "100%"; //div에 꽉차게 넣으려고
            newImage.style.height = "100%";
            newImage.style.objectFit = "cover"; // div에 넘치지 않고 들어가게

            //이미지를 image-show div에 추가
            let container = document.getElementById('image-show');
            container.appendChild(newImage);
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
	</script>
</body>
</html>