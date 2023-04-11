<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ include file="/resources/header.jspf" %>
<style>
	body{
		margin:0;
		padding-top:70px;
	}
	a{
		text-decoration:none;
		color:#FF7100;
	}
	select{
		margin-top:200px;
		margin-left:150px;
		width:300px;
		height:50px;
		padding-left:5px;
		border: 2px solid #FF7100;
		border-radius:20px;
		color:#9B4500;
	}
	img{
		display:flex;
		width:350px;
		height:350px;
		margin-right:0;
		margin-top:150px;
	}
	#select_box #img{
		float:left;
		width:33%;
	}
</style>

	<div id="select_box">
		<select>
			<option>지역 선택</option>
			<option>마포</option>
			<option>강남</option>
		</select>
		<input type="submit" value="검색하기" onclick="location.href='district'"/>
	</div>
	<div id="img">
		
	</div>
</body>
</html>