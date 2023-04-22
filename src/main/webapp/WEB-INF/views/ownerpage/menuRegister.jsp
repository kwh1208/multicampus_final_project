<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.3/jquery.min.js"></script>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>메뉴예약 페이지</title>
<script>
	$(document).on('click', '#menuForm input[value=" + "]', function(){
		$(this).parent().parent().append('<div><input type="file" id="filename" name="filename"><input type="button" value=" + "></div>');
		$(this).val(" - ");
	});
	
	$(document).on('click', '#menuForm input[value=" - "]',function(){
		$(this).parent().remove();
	});
</script>
</head>
<body>
	<form action="menuRegisterOk" ModelAttribute="MenuDTO" id="menuForm" method="post" enctype="multipart/form-data">
	<ul>
		<li>메뉴 이름:</li>
		<li><input type="text" name="menu_name" id="menu_name"></li>
		<li><label for="filename">가게 사진:</label></li>
		<li>
		<div>
			<input type="file" id="filename" name="filename"><input type="button" value=" + ">
		</div>
		</li>
	 	<li><input type="submit" value="가게 등록"></li>
	 </ul>
	 </form>
</body>
</html>