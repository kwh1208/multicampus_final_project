<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ include file="/resources/header.jspf" %> 
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">

<style>
	*{
		text-size:1.2em;
	}
	.c{
		display:flex;
		justify-content:center;
		margin-top:130px;
		margin-bottom:30px;
	}
	#inquiryForm li{
		padding:5px 0px;
		margin-bottom:5px;
	}
	h1{
		text-align:center;
		margin-bottom:30px;
		color:#696969;
	}
	#question_title{
		width:500px;
		height:30px;
	}
	#question{
		width:500px;
		height:250px;
	}
	ul{
		list-style-type:none;
	}
	input, textarea{
		border:1.5px solid #ddd;
		border-radius:5px;
	}
	input[type="submit"]{
		float:right;
		border:none;
		background-color:#FF7100;
		color:white; 
		width:100px;
		height:40px;
		border-radius:30px;
	}
	input:focus, textarea:focus{
		outline:none;
	}
</style>
</head>
<script>
	// 폼의 유효성검사
	$("#inquiryForm").submit(function(){
		if($("#question_title").val()==""){
			alert("제목을 입력하세요.");
			return false;
		}else{
			alert("문의가 등록되었습니다.\n문의내역은 마이페이지에서 확인가능합니다.");
		}
	});

</script>
<body>
<div class="c">
<div class="all">
	<h1>1:1 문의</h1>
	<div style="border-top:1px solid #696969; margin-bottom:20px;"></div>
	<form method="post" action="inquiryWriteOk" id="inquiryForm" enctype="multipart/form-data">
		<ul>
			<li>제목</li>
				<li style="margin-bottom:20px;"><input type="text" name="question_title" id="question_title"/></li>
			<li>상담내용</li>
				<li style="margin-bottom:20px;"><textarea name="question" id="question"></textarea></li>
			<li>첨부파일</li>
				<li>
					<div><input type="file" name="filename"/></div>
				</li>
			<li>
				<input type="submit" value="등록하기" id="submit"/>
			</li>
		</ul>
	</form>
</div>
</div>

</body>
</html>