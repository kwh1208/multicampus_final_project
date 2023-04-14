<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<h1>내가 쓴 리뷰 목록</h1>
<li>가게 이름</li>
<li>${store_name }스토어네임..</li>
<li>쓴 날짜</li>
<li>${dto.review_time }</li>
<li>평점</li>
<li>${dto.score }</li>
<li>리뷰내용</li>
<li>${dto.comment_time }</li>
<input type="button" value="수정">
<input type="button" value="삭제">