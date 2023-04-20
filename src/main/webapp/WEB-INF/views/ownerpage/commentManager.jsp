<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.3/jquery.min.js"></script>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>댓글관리페이지</title>
</head>
<body>
	<c:forEach var="comment" items="${review }">
	<li>리뷰번호: ${comment.seq }</li>
	<li>예약자 아이디: ${comment.user_id }</li>
	<li>별점 : ${comment.score}</li>
	<li><a href="reviewContent?no=${comment.seq}">리뷰 내역: ${comment.review}</a></li>
	<li>작성 시간: ${comment.review_time}</li>
	<li>사장님 코멘트: ${comment.owner_comment}</li>
	<li>사장님 댓글 시간: ${comment.comment_time}</li>
	</c:forEach>
</body>
</html>