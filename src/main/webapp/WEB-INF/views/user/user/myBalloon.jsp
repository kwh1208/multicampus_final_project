<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<h1>먹풍 내역</h1>
<c:forEach var="bal" items="${list }">
	<li>${bal.use_get_time }</li>
	<li>${bal.content }</li>
	<li>${bal.balloon}개</li>
	<li>${bal.total_balloon}개</li>
	
</c:forEach>