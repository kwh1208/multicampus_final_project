<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<h1>예약내역 목록</h1>
<c:forEach var="res" items="${list }">
	<li>가게이름</li>
	<li>${res.store_name }</li>
	<li>예약일시</li>
	<li>${res.reservation_time }시</li>
	<li>인원</li>
	<li>${res.number_of_people }명</li>
	<li>예약자</li>
	<li>${res.user_name}(${res.phone_number })</li>
	<li>요청사항</li>
	<li>${res.reservation_comment } </li>
</c:forEach>

<a href="reviewCancle">예약취소</a>(예약시간지나면비활성화)
<a href="reviewWrite">리뷰쓰기</a>(예약시간지나면활성화)