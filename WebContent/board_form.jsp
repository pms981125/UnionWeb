<%@page import="vo.MemberVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
	MemberVO user = (MemberVO) request.getAttribute("user");
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
<title>Create Board</title>
</head>
<body>
	<%--${ user } <br />
	<%= user %> <br /> --%>
	<div class="container mt-md-3" align="center">
		<form action="createBoard.do" method="post" name="boardForm" enctype="multipart/form-data">
			<input type="hidden" name="id" value="${ user.getId() }" />
			<div class="form-group input-group">
				<div class="input-group-prepend"><span class="input-group-text">제목</span></div>
				<input type="text" class="form-control" name="title" id="title" required placeholder="게시글 제목을 입력하세요." />
			</div>
			<div class="form-group input-group">
				<div class="input-group-prepend"><span class="input-group-text">내용</span></div>
				<textarea name="contents" id="contents" rows="15" cols="40" class="form-control" required placeholder="게시글 내용을 입력하세요."></textarea>
			</div>
			<div class="form-group input-group">
				<div class="form-group input-group imgDiv">
					<div class="addImage" id="image-show"></div>
					<div class="form-group input-group imgDiv">
						<input type="file" accept="image/*" onchange="loadFile(this)" name="img" alt="이미지 선택"/>
					</div>
				</div>
					
				<div class="form-group input-group mt-sm-1 justify-content-center">
		            <input type="submit" class="btn btn-primary float-right login-btn" value="게시글 등록"/>
					<button type="button" class="btn btn-success float-right login-btn ml-sm-2" onclick="javascript:getBoardList('${ user.getId() }')">이전</button>
				</div>
			</div>
		</form>
	</div>
	<script>
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
	</script>
</body>
</html>