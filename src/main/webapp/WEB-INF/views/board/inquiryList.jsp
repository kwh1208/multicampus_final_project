<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ include file="/resources/header.jspf" %>
<style>
	.c{
		width:1200px;
		margin:0 auto;
	}
	.inquiryList{
		overflow:auto;
	}
	.inquiryList li{
		float:left;
		width:30%;
		height:50px;
		line-height:50px;
		border-bottom:1px solid #ddd;
	}
	.inquiryList li:nth-child(3n+2){
		width:20%;
		white-space:nowrap;
		overflow:hidden;
		text-overflow:ellipsis;
	}
	h1{
		color:#696969;
		text-align:center;
		margin-top:200px;
	}
	#title_a{
		color:black;
	}
	#title_a:hover{
		color:#FF7100;
		cursor:pointer;
	}
</style>
<h1>나의 1:1문의목록</h1>
<div class="c" style="margin:50px 10% 0 10%;">
	<ul class="inquiryList">
		<li>제목</li>
		<li>등록일</li>
		<li style="padding-left:100px;">진행상태</li>
		
		
		<c:forEach var="inquiry" items="${list}">
			<li><a href="/board/inquiryView/${inquiry.seq }" id="title_a">${inquiry.question_title}</a></li>
			<li>${inquiry.question_time}</li>
			<c:if test="${empty inquiry.answer}">
				<li style="padding-left:100px; color:gray;">답변대기</li>
			</c:if>
			<c:if test="${not empty inquiry.answer}">
				<li style="padding-left:100px;">답변완료</li>
			</c:if>
		</c:forEach>
	</ul>
</div>

</body>
</html>