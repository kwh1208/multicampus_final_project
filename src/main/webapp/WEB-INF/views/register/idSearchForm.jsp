<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ include file="/resources/header.jspf" %>
<style>
	.container{
		margin-top:200px;
	}
	.container>div{
		width:50%;
		margin:100px auto;
	}
	.container li{
		padding:10px;
		margin-left:20%;
	}
	.container input{
		width:70%;
		height:30px;
		line-height:30px;
	}
	h1{
		text-align:center;
		color:#696969;
	}
	input:focus{
		outline:none;
	}
	input[type="submit"]{
		width:100px;
		height:40px;
		line-height:40px;
		background-color:#FF7100;
		border:none;
		border-radius:30px;
		color:white;
		float:right;
		margin-right:28%;
		margin-top:10px;
	}
</style>
<script>
	$(function(){
		$("#idSearch").submit(function(){
			event.preventDefault();
			
			if($("#user_name").val()==""){
				alert('이름을 입력하세요.');
				return false;
			}
			if($("#email").val()==""){
				alert('이메일을 입력하세요.');
				return false;
			}
			
			var url = "idSearchEmailSend";
			var params = $("#idSearch").serialize();
			
			$.ajax({
				url : url,
				data : params,
				type : 'POST',
				success : function(result){
					if(result=='Y'){
						alert("이메일로 아이디를 전송하였습니다.");
						location.href="/register/loginForm";
					}else{
						alert("존재하지않는 정보입니다.");
					}
				},error : function(){
					console.log(e.responseText);
				}
			});
		});
	});
</script>
<div class="container">
	<div>
		<h1>아이디 찾기</h1>
		<form method="post" id="idSearch">
			<ul>
				<li>이름</li>
				<li><input type="text" name="user_name" id="user_name"/></li>
				<li>이메일</li>
				<li><input type="text" name="email" id="email"/></li>
				<li><input type="submit" value="아이디찾기"/>
			</ul>
		</form>
	</div>	
</div>
</body>
</html>