<%@page import="eat_schedule.dto.UserDTO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<link rel="stylesheet" href="/inc/myPage.css" type="text/css"/>
<%@ include file="/resources/header.jspf" %>

<div class="wrap">
	<div class="topContainer">
		<div class="name">${dto.user_name}</div>
		<div class="text"> 님, 안녕하세요!</div>
		<a href="user/joinEdit" class="delete">
			<div class="modify">정보 수정 > </div>
		</a>
	</div>
	<div class="summaryContainer">
		<div class="item">
			<div>총 먹풍 > </div>
			<a href="user/myBalloon" class="delete">
				<div class="number">${dto.balloon } 개</div>	
			</a>
		</div>
		<div class="v-line"></div>
		<div class="item">
			<div>보유 쿠폰 > </div>
			<a href="user/myCoupon" class="delete">
				<div class="number">${cnt} 개</div>
			</a>
		</div>
	</div>
	
	<div class="listContainer">
		<a href="http://localhost:9090/table" class="item">
			<div class="text">홈</div>
		</a>
		<a href="user/myReservation" class="item">
			<div class="text">예약 내역</div>
			<div class="right"> > </div>
		</a>
		<a href="user/myReview" class="item">
			<div class="text">내 리뷰 확인</div>
			<div class="right"> > </div>
		</a>
		<a href="user/myBalloon" class="item">
			<div class="text">먹풍 내역</div>
			<div class="right"> > </div>
		</a>
		<a href="user/myCoupon" class="item">
			<div class="text">쿠폰 내역</div>
			<div class="right"> > </div>
		</a>
		<a href="/board/inquiryList" class="item">
			<div class="text">문의 내역</div>
			<div class="right"> > </div>
		</a>
		<a href="user/wishlist" class="item">
			<div class="text">찜목록</div>
			<div class="right"> > </div>
		</a>
	</div>

</div>