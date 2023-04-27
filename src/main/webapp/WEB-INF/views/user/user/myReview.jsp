<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<h1>내가 쓴 리뷰 목록</h1>
<style>
.wrap{
	background-color: #fff;
	max-width: 1200px;
	border : 1px solid #f5f5f5;
	margin: 0 auto;
	border-radius:10px;
	padding : 15px 15px;
	margin-bottom : 20px;
}

/* 가게 이름 */
.name{
	margin-top : 4px;
	font-size: 18px;
	font-weight: bold;
}
/* 내용 부분 */
.content_wrap{
	display:flex;
	margin : 10px 10px;
}
.content{
	margin-top : 5px;
	margin-right : 15px;
}
.right{
	margin-left:auto;
}
.star{
	font-size:20px;
	margin-left:10px;
    color: transparent;
    text-shadow: 0 0 0 rgba(250, 208, 0, 0.99);
}
/* 버튼 */
.btn{
	display: inline-block;
	line-height:25px;
    height:27px;
    width: 80px;
    background-color: #FF7100;
    color: #fff;
    font-size: 14px;
    border:none;
    border-radius:5px;
    text-align:center;
    text-decoration: none;

}
</style>
<c:forEach var="rev" items="${list }">
<fmt:parseDate var="revTime" value="${rev.review_time }" pattern="yyyy-MM-dd HH:mm:ss"/>
<script>
	function reviewDel(){
		if(confirm("해당글을 삭제하시겠습니까?")){
			location.href = "reviewDel?no=${review.seq}";
		}
	}
</script>
<div class="wrap">
	<div class="content_wrap">
		<div class="name">${rev.store_name }</div>
		<div class="star">★</div>
		<div class="content">${rev.score }</div>
		<div class="content"><fmt:formatDate value="${revTime}" pattern="yyyy년 M월 d일" /></div>
		<div class="right">
			<a href="reviewEdit?no=${rev.seq }" class="btn">수정</a>
			<a href="javascript:reviewDel()" class="btn">삭제</a>
		</div>
	</div>
	<div class="content_wrap">
		<div class="content">${rev.review }</div>
		<img src="${rf.filename}" width="100px" height="100px" class="right">
	</div>
	
	</div>
	<!--  
	<li>사장님 댓글</li>
	<li>${rev.owner_comment }</li>
	<li>${rev.comment_time }</li>
	<li>${rev.seq }</li>
	-->
	
</c:forEach>


