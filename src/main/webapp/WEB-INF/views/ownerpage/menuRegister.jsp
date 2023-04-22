<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.3/jquery.min.js"></script>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>메뉴예약 페이지</title>
<style>
ul{
   list-style:none;
   padding-left:0px;
   }
</style>
<script>
		$(document).on('click', '#menuForm input[value=" + "]', function(){
			$(this).parent().parent().append('<div><ul><li>메뉴 이름:</li><li><input type="text" name="menu_name" id="menu_name"></li><li>가격</li><li><input type="number" name="price" id="price" step="100"></li><li>카테고리</li><li><input type="text" name="category" id="category"></li><li>음식 정보</li><li><input type="text" name="information" id="information"></li><li><label for="filename">메뉴 사진:</label></li><li><input type="file" id="filename" name="filename"></li><li><input type="button" value=" + "></li></ul></div>');
			$(this).val(" - ");
		});
		
		$(document).on('click', '#menuForm input[value=" - "]',function(){
			$(this).parent().remove();
		});
</script>
</head>
<body>
	<form action="menuRegisterOk" ModelAttribute="MenuDTO" id="menuForm" method="post" enctype="multipart/form-data">
	<div>
	<ul>
		<li>메뉴 이름:</li>
		<li><input type="text" name="menu_name" id="menu_name"></li>
		<li>가격</li>
		<li><input type="number" name="price" id="price" step="100"></li>
		<li>카테고리</li>
		<li><input type="text" name="category" id="category"></li>
		<li>음식 정보</li>
		<li><input type="text" name="information" id="information"></li>
		<li><label for="filename">메뉴 사진:</label></li>
		<li>
			<input type="file" id="filename" name="filename">
		</li>
		<li><input type="button" value=" + "></li>
		</ul>
		</div>
	 <input type="submit" value="메뉴 등록">
	 </form>
</body>
</html>