<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<link rel="stylesheet" href="/table/inc/myPage.css" type="text/css"/>
<%@ include file="/resources/header.jspf" %>
<div class="wrap">
	<div class="orangeContainer">
		<div class="name">천수민</div>
	</div>
	<div class="summaryContainer">
		<div class="item">
			<div class="number">10</div>
			<div>총 먹풍</div>
		</div>
		<div class="item">
			<div class="number">2</div>
			<div>보유 쿠폰</div>
		</div>
	</div>

	<div class="listContainer">
		<a href="user/myReservation" class="item">
			<div class="text">예약 내역</div>
			<div class="right"> > </div>
		</a>
		<a href="user/myReview" class="item">
			<div class="text">내 리뷰 확인</div>
			<div class="right"> > </div>
		</a>
		<a href="user/mukpoong" class="item">
			<div class="text">먹풍 내역</div>
			<div class="right"> > </div>
		</a>
		<a href="user/myCoupon" class="item">
			<div class="text">쿠폰 내역</div>
			<div class="right"> > </div>
		</a>
		<a href="user/myQnA" class="item">
			<div class="text">문의 내역</div>
			<div class="right"> > </div>
		</a>
		<a href="user/wishList" class="item">
			<div class="text">찜목록</div>
			<div class="right"> > </div>
		</a>
	</div>
	<div class="listContainer">
		<a href="register/loginForm" class="item">
			<div class="text">로그인</div>
			<div class="right"> > </div>
		</a>
		<a href="user/joinEdit" class="item">
			<div class="text">회원정보 수정</div>
			<div class="right"> > </div>
		</a>	
	</div>
</div>