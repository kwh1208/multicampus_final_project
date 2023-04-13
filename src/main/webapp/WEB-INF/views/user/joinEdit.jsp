<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<style>
	*{
		padding:0;
		margin:0;
		}
	.all{
		font-size:15px;
		margin-top:100px;
	}
	h1{
		text-align:center;
		font-size:20px;
		color:#FF9139;
		margin-bottom:20px;
		margin-top:20px;
		}
	.joinEdit{
		width:400px;
		height:350px;
		padding:50px;
		box-sizing:border-box;
		margin:0 auto;
	}
	#joinEditForm ul{
		overflow:auto;	
	}
</style>
<script>
	$(function(){
		
		//유효성 검사
		$("#joinEditForm").submit(function(){
			
			//비밀번호
			if($("#user_password").val()==""){
				alert("비밀번호를 입력하여야 회원정보수정이 가능합니다.");
				return false;
			}
			
			//연락처
			
			//닉네임
			
			//이메일
			reg = /^\w{6,15}@[a-zA-Z]{2,8}.[a-z]{2,5}(.[a-z]{2,5})?$/
			if(!reg.test($("#email").val())){
				alert("이메일을 잘못입력하였습니다.");
				return false;
			}
			
			$("#joinEditForm").attr("action", "joinEditOk");
			
		});
		
	});
	
</script>

<h1>회원정보수정폼</h1>
<div class="joinEdit">
	<form method="post" id="joinEditForm">
		<ul>
			<li>아이디</li>
			<li>
				<input type="text" name="user_id" id="user_id" minlength="8" maxlength="15" value="${dto.user_id }" readonly/>
			</li>
			<li>비밀번호</li>
			<li><input type="password" name="user_password" id="user_passwaord" minlength="8" maxlength="15"/></li>
			<li>이름</li>
			<li><input type="text" name="user_name" id="user_name" minlength="2" maxlength="10" value="${dto.user_name }" readonly/></li>
			<li>닉네임</li>
			<li><input type="text" name="nickname" id="nickname" minlength="3" maxlength="15" value="${dto.nickname }"/></li>
			<li>연락처</li>
			<li><input type="text" name="phone_number" id="phone_number" minlength="10" maxlength="11"></li>
			<li>이메일</li>
			<li><input type="text" name="email" id="email" value="${dto.email }"/></li>
			<li>주소</li>
			<li><input type="text" name="address" id="address" value="${dto.address}"/></li>
			<li>
			<input type="submit" value="회원정보수정하기"/>
			</li>
		</ul>
	</form>
</div>
