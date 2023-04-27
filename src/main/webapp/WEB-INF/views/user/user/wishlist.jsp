<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<h1>찜 목록</h1>
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
	font-size: 18px;
	font-weight: bold;
}

/* 내용 부분 */
.content_wrap{
	display:flex;
}
.content{
	
}
.star{
	font-size:20px;
	margin-left:10px;
    color: transparent;
    text-shadow: 0 0 0 rgba(250, 208, 0, 0.99);
}
.heart{
	margin-left:auto;
}

</style>
<c:forEach var="w" items="${list }">
<div class="wrap">
	<div class="content_wrap">
		1<div class="item">사 진</div>
		2<div class="item">
			<div class="content_wrap">
				<div class="name">${w.store_name } ></div>
				<div class="star">★</div>
				<div class="content">(가게평점)</div>
			</div>
			<div class="content_wrap">
				<div class="content">${w.district }·</div>
				<div class="content">${w.category }</div>
			</div>
		</div>
		3<div class="heart">♥</div>		
	</div>
</div>

</c:forEach>
