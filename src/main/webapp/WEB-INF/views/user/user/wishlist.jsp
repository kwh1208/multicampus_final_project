<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ include file="/resources/header.jspf" %>

<style>
h1{
	font-size:30px;
	color:#FF7100;
	margin-bottom:50px;
	text-align:center;
}
.wrap{
	background-color: #fff;
	max-width: 700px;
	border : 1px solid #f5f5f5;
	margin: 0 auto;
	border-radius:10px;
	padding : 20px 20px;
	margin-bottom : 20px;
}
.name{
	margin-top:3px;
	font-size: 19px;
	font-weight: bold;
	text-decoration: none; 
	color:#000;
}
.content{
	margin-top:4px;
}
.content_wrap{
	margin : 10px 20px;
	display:flex;
}

.star{
	font-size:20px;
    color: transparent;
    text-shadow: 0 0 0 rgba(250, 208, 0, 0.99);
}
.image{
	margin-right:10px;
}
.score{
	margin:5px 5px;
	font-weight:bold;
}
.count{
	margin-top:6px;
	color:#828282;
	font-size:14px;
}
.comment{

}
.category{
	margin-top:-10px;
	color:#828282;
	font-size:13px;
}
</style>
<h1>찜 목록</h1>
<c:forEach var="w" items="${list }">
<div class="wrap">
	<div class="content_wrap">
		<div class="item">
			<img src="" width="200px" height="200px" class="image">
		</div>
		<div class="item">
			<div class="content_wrap">
				<a href="/restaurant/${w.store_seq}" class="name">${w.store_name } ></a>
			</div>
			<div class="content_wrap">
				<div class="comment">${w.owner_comment }</div>
			</div>
			<div class="content_wrap">
				<div class="star">★</div>
				<div class="score">${w.avg_score }</div>
				<div class="count">(35)</div>
			</div>
			<div class="content_wrap">
				<div class="category">${w.district } · ${w.category }</div>
			</div>
		</div>	
	</div>
</div>

</c:forEach>
