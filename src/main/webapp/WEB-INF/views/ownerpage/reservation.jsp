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
	<h1>가게리뷰확인</h1>
	<div class="container">
	<c:forEach var="reservation" items="${reservation }">
	<div class="reservation-container">
	<div class="reservation_username">
	예약자 성함: ${reservation.user_name }
	</div>
	<div class="phone_numner">
	예약자 전화번호: ${reservation.phone_number }
	</div>
	<div class="email">
	예약자 이메일: ${reservation.email }
	</div>
	<div class="people_number">
	예약인원: ${reservation.number_of_people}명
	</div>
	<div class="coupon">
	쿠폰사용: ${reservation.coupon}
	</div>
	<div class="reservation_time">
	예약시간: ${reservation.reservation_time}
	</div>
	<div class="reservation_status">
	예약상태: ${reservation.reservation_status}
	</div>
	<div class="visit_status">
	방문확인: ${reservation.visit_status}
	</div>
	<div class="reservation_comment">
	예약코멘트: ${reservation.reservation_comment}
	</div>
	<div class="detail">
	<a href="reservationDetail?no=${reservation.seq}">예약상세정보보기</a>
	</div>
	</div>
	</c:forEach>
	</div>
</body>
</html>
