<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.3/jquery.min.js"></script>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>예약확인페이지</title>
</head>
<body>
	<c:forEach var="reservation" items="${reservation }">
	<li>예약번호: ${reservation.seq }</li>
	<li><a href="reservationDetail?no=${reservation.seq}">예약자 아이디: ${reservation.user_id }</a></li>
	<li>예약자 성함: ${reservation.user_name }</li>
	<li>예약자 전화번호: ${reservation.phone_number }</li>
	<li>예약자 이메일: ${reservation.email }</li>
	<li>예약인원: ${reservation.number_of_people}명</li>
	<li>쿠폰사용여부: ${reservation.coupon}</li>
	<li>예약시간: ${reservation.reservation_time}</li>
	<li>예약상태: ${reservation.reservation_status}</li>
	<li>예약코멘트: ${reservation.reservation_comment}</li>
	</c:forEach>
</body>
</html>