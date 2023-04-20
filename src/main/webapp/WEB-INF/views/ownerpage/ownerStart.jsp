<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.3/jquery.min.js"></script>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>가게선택페이지</title>
</head>
<body>
	<h1>가게선택</h1>
	<c:if test="${store!=null }">
	<c:forEach var="store" items="${store }">
	<li>가게번호: ${store.seq }</li>
	<li>사장님 아이디: ${store.owner_id }</li>
	<li><a href="ownerMyPage?no=${store.seq}">가게이름: ${store.store_name}</a></li>
	</c:forEach>
	</c:if>
	
	<a href="/ownerpage/storeRegister">가게등록 바로가기</a>
</body>
</html>