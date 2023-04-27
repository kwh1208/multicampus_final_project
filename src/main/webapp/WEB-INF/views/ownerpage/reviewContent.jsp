<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.3/jquery.min.js"></script>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>댓글상세보기 페이지</title>
</head>
<body>
	<h1>리뷰내용보기</h1>
	<ul>
		<li>번호</li>
		<li>${reviewDetail.seq }</li>
		<li>등록자 아이디</li>
		<li>${reviewDetail.user_id }</li>
		<li>별점</li>
		<li>${reviewDetail.score }</li>
		<li>내용</li>
		<li>${reviewDetail.review }</li>
		<li>리뷰작성시간</li>
		<li>${reviewDetail.review_time }</li>
	</ul>
	<ul>
		<c:if test="${reviewDetail.owner_comment!=null }"> 
		<form id="comment-form" ModelAttribute="ReviewDTO" action="ownerCommentAdd" method="post">
		<input type="hidden" name="seq" value="${reviewDetail.seq }">
		<label for="owner_comment">사장님 코멘트:</label>
      	<textarea id="owner_comment" name="owner_comment">${reviewDetail.owner_comment }</textarea>
      	<button type="submit">댓글수정</button>
	</form>
		<li>사장님 코멘트 시간</li>
		<li>${reviewDetail.comment_time }</li>
		</c:if>
	</ul>
	<c:if test="${reviewDetail.owner_comment==null }">
	<form id="comment-form" ModelAttribute="ReviewDTO" action="ownerCommentAdd" method="post">
		<input type="hidden" name="seq" value="${reviewDetail.seq }">
		<label for="owner_comment">사장님 코멘트:</label>
      	<textarea id="owner_comment" name="owner_comment"></textarea>
      	<button type="submit">댓글작성</button>
	</form>
	</c:if>
	<c:if test="${reviewDetail.coupon_status==0 }">
	<form id="coupon-form" ModelAttribute="CouponDTO" action="couponGift" method="post">
		<input type="hidden" name="user_id" value="${reviewDetail.user_id }">
		<input type="hidden" name="store_seq" value="${reviewDetail.store_seq }">
		<input type="hidden" name="review_seq" value="${reviewDetail.seq}">
		<button type="submit">쿠폰 선물하기</button>
	</form>
	</c:if>
</body>
</html>