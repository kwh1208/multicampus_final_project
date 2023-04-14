<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<h1>예약내역 목록</h1>
<li>가게이름</li>
<li>${store_name }가게이름어케들고오지</li>
<li>예약일시</li>
<li>${dto.reservation_time }시</li>
<li>인원</li>
<li>${dto.number_of_people }명</li>
<li>예약자</li>
<li>${user_name}(${phone_number })유저네임 폰넘버 어케들고오지</li>
<li>요청사항</li>
<li>${dto.reservation_comment } </li>

<input type="button" class="cancle" value="예약취소">
<input type="button" class="review" value="리뷰쓰기">(예약시간지나면활성화)