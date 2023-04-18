<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ include file="/resources/header.jspf" %>
<link type="text/css" rel="stylesheet" href="/style/style.css"/>

<style>
	.container{
		width:1200px;
		margin:0 auto;
	}
	#img_box1{
		margin-top:250px;
	}
	#search{
		width:100px;
		height:50px;
		line-height:50px;
		background-color:rgb(255, 150, 91);
		border:none;
		border-radius:30px;
		color:white;
		font-size:1.2em;
	}
	#select_box{
		margin-top:20px;
	}
	select{
		appearance: none;
        -webkit-appearance: none;
        -moz-appearance: none;
        width:300px;
        height:50px;
        line-height:50px;
        border: 2px solid rgb(255, 150, 91);
        border-radius:20px;
        padding:0 10px;
	}
	select::-ms-expand{ 
		display: none;
    } 
    select:focus{
		outline:none;
	}
</style>
<div class="container">
	<div id="img_box1">
		<img src="/img/logo1.png" style="width:385px; height:139px;">
	</div>
	<div id="select_box">
		<select>
			<option>지역 선택</option>
			<option>마포</option>
			<option>강남</option>
		</select>
		<input id="search" type="submit" value="검색하기" onclick="location.href='district'"/>
	</div>
	<div id="img_box2">
	
	</div>
</div>
</body>
</html>
