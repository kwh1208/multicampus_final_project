<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.3/jquery.min.js"></script>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>예약상세보기 페이지</title>
</head>
<body>
	<h1>예약내용보기</h1>
	<ul>
		<li>번호</li>
		<li>${reservationDetail.seq }</li>
		<li>예약자 아이디</li>
		<li>${reservationDetail.user_id }</li>
		<li>예약자 이름</li>
		<li>${reservationDetail.user_name }</li>
		<li>예약자 번호</li>
		<li>${reservationDetail.phone_number }</li>
		<li>예약자 이메일</li>
		<li>${reservationDetail.email }</li>
		<li>예약인원</li>
		<li>${reservationDetail.number_of_people }</li>
		<li>예약시간</li>
		<li>${reservationDetail.reservation_time }</li>
		<li>예약자 코멘트</li>
		<li>${reservationDetail.reservation_comment }</li>
		<li>쿠폰 사용 여부</li>
		<li>${reservationDetail.coupon }</li>
	</ul>
	<c:if test="${reservationDetail.reservation_status eq '예약 대기' }">
	<li>예약 상태</li>
	<li>${reservationDetail.reservation_status }</li>
	<form id="reservationCheck-form" ModelAttribute="ReservationDTO" action="reservationCheckOk" method="post">
		<input type="hidden" name="seq" value="${reservationDetail.seq }">
		<input type="hidden" name="reservation_status" value="예약 완료">
		<button type="submit">예약 확정</button>
	</form>
	<form id="reservationCheck-form" ModelAttribute="ReservationDTO" action="reservationFailOk" method="post">
		<input type="hidden" name="seq" value="${reservationDetail.seq }">
		<input type="hidden" name="reservation_status" value="예약 취소">
		<button type="submit">예약 취소</button>
	</form>
	</c:if>
	<ul>
		<c:if test="${reservationDetail.reservation_status eq '예약 완료' && reservationDetail.visit_status eq '방문 미확인'}">
		<li>예약 상태</li>
		<li>${reservationDetail.visit_status }</li>
		<form id="reservationCheck-form" ModelAttribute="ReservationDTO" action="showCheckOk" method="post">
			<input type="hidden" name="seq" value="${reservationDetail.seq }">
			<input type="hidden" name="user_id" value="${reservationDetail.user_id }">
			<input type="hidden" name="visit_status" value="방문 확인">
			<button type="submit">방문 확인</button>
		</form>
		<form id="reservationCheck-form" ModelAttribute="ReservationDTO" action="noShowCheckOk" method="post">
			<input type="hidden" name="seq" value="${reservationDetail.seq }">
			<input type="hidden" name="user_id" value="${reservationDetail.user_id }">
			<input type="hidden" name="visit_status" value="노쇼">
			<button type="submit">노쇼</button>
		</form>
		</c:if>
	</ul>
</body>
</html>