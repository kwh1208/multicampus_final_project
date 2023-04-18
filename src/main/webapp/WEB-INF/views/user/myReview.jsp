<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<h1>내가 쓴 리뷰 목록</h1>

<c:forEach var="review" items="${list }">
<li>가게 이름</li>
<li>${review.store_name }</li>
<li>${review.review_time }</li>
<li>평점</li>
<li>${review.score }</li>
<li>리뷰내용</li>
<li>${review.comment_time }</li>
</c:forEach>

<input type="button" value="수정">
<input type="button" value="삭제">