<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ include file="/resources/header.jspf" %>
<style>
	*{
		padding:0;
		margin:0;
		border:none;
	}
	.all{
		font-size:15px;
		margin-top:180px;
	}
	.login{
		width:400px;
		height:350px;
		padding:50px;
		box-sizing:border-box;
		margin:0 auto;
	}
	h1{
		text-align:center;
		font-size:20px;
		color:#FF9139;
		margin-bottom:20px;
	}
	#logFrm>input{
		width:100%;
		height:50px;
		padding: 0 10px;
		box-sizing:border-box;
		margin-bottom:15px;
		border-bottom:#FF7100 solid 2px;
	}
	#logFrm>input::placeholder{
		color:#FFA964;
	}
	#logFrm>input[type="submit"]{
		color:#fff;
		background-color:#FF7100;
		font-size:15px;
		margin-top:20px;
	}
	#find{
		text-align:center;
	}
	#find a, span{
		color:#FFA964;
		text-decoration:none;
	}
	input:focus{
		outline:none;
	}
</style>

<div class="all">
<div class="login">
	<h1>로그인</h1>
	<form method="post" action="loginOk" id="logFrm">
			<input type="text" name="user_id" id="user_id" placeholder="아이디"/>
			<input type="password" name="user_password" id="user_password" placeholder="비밀번호"/>
			<input type="submit" value="로그인"/>
	</form>
	<div style="margin:0 auto; padding:0;" id="find">
		<a href="/register/idSearchForm">아이디찾기</a>
		<span>/</span>
		<a href="/register/passwordSearchForm">비밀번호찾기</a>
		<span>/</span>
		<a href="/register/joinForm">회원가입</a>
	</div>
</div>
</div>

</body>
</html>