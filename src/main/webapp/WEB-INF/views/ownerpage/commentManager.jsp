<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.3/jquery.min.js"></script>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>댓글관리페이지</title>
    <style>
    h1 {
   		text-align: center;
  		margin-top: 100px;
   		margin-bottom: 20px;
 		}
    .container {
            max-width: 960px;
            margin: 0 auto;
        }
        .review-container {
            border: 1px solid black;
            padding: 10px;
            margin-bottom: 10px;
            border-radius: 10px;
        }
        .review-author {
            font-weight: bold;
        }
        .review-time {
            color: gray;
            font-size: 12px;
        }
        .review-rating {
            margin-top: 10px;
            font-size: 24px;
            color: orange;
        }
        .review-content {
            margin-top: 10px;
            white-space: pre-line;
        }
        .space-work{
        	margin: 10px;
        }
        .review-owner {
            margin-top: 10px;
            margin-bottom: 10px;
            font-weight: bold;
        }
        .review-owner-comment {
            margin-top: 10px;
            white-space: pre-line;
        }
        .review-boss-comment {
            margin-top: 10px;
            color: gray;
            font-size: 12px;
            white-space: pre-line;
        }
    </style>
</head>
<body>
	<h1>가게 리뷰 확인</h1>
<div class="container">
    <c:forEach var="comment" items="${review}">
        <div class="review-container">
            <div class="review-author">
                작성자 ID: ${comment.user_id}
            </div>
            <div class="review-time">
                Comment time: ${comment.review_time}
            </div>
            <div class="review-rating">
                별점: ${comment.score}
            </div>
            <div class="review-content">
                내용: ${comment.review}
                
            </div>
            <div class="space-work"></div>
            <div class="review-owner">
                사장님 코멘트:
            </div>
            <div class="review-owner-comment">
                ${comment.owner_comment}
            </div>
            <div class="review-boss-comment">
               사장님 comment time: ${comment.comment_time}
            </div>
            <div><a href="reviewContent?no=${comment.seq}">상세보기</a></div>
        </div>
    </c:forEach>
</div>
</body>
</html>
<!-- <li><a href="reviewContent?no=${comment.seq}">상세보기</a></li> -->