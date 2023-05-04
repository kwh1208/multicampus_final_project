<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ include file="/resources/header.jspf" %>
<style>
	.inquiry_all{
		width:1200px;
		display:flex;
		margin:0 auto;
		justify-content:center;
	}
	.inquiryList{
		overflow: auto;
    	text-align: center;
    	width: 70%;
    	margin: 0 auto;
    	padding-top: 30px;
	}
	.inquiryList li{
		float:left;
		width:33%;
		height:50px;
		line-height:50px;
		border-bottom:1px solid #ddd;
	}
	h1{
		color:#696969;
		text-align:center;
		margin-top:100px;
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
	<c:if test="${empty list}">
		<img src="/img/mark.png" style="width:100px; height:100px; display:block; margin:0 auto; padding-top:30px;">
		<h2 style="text-align:center; padding-top:15px;">등록된 문의가 없습니다.</h2>
	</c:if>
	
	<c:if test="${not empty list}">
		<div class="inquiry_all">
				<ul class="inquiryList">
					<li>제목</li>
					<li>등록일</li>
					<li>진행상태</li>
					
					
					<c:forEach var="inquiry" items="${list}">
						<li><a href="/board/inquiryView/${inquiry.seq }" id="title_a">${inquiry.question_title}</a></li>
						<li>${inquiry.question_time}</li>
						<c:if test="${empty inquiry.answer}">
							<li style="color:gray;">답변대기</li>
						</c:if>
						<c:if test="${not empty inquiry.answer}">
							<li style="color:red;">답변완료</li>
						</c:if>
					</c:forEach>
				</ul>
		</div>
	</c:if>

	<div id="footer" style="width:100%; height:250px; background:rgb(49,55,63); bottom:0; margin-top:150px; font-size:14px;">
		<div style="color:#fff;"><img src="/img/logo_r.png" style="width:90px; height:45px; margin-top:30px; margin-left:20px;"> | 개인정보처리방침 | 이용약관</div>
		<div style="color:#fff; margin-top:40px; margin-left:20px;">
			(주)먹스케쥴 [mukschedule@gmail.com]<br/>
			<p style="margin-bottom:5px;">Developers<br/></p>
			<a href="https://github.com/kwh1208">Kwon woohyun</a> | 
			<a href="https://github.com/yongtae">Kim yongtae</a> | 
			<a href="https://github.com/soomin">Cheon soomin</a> | 
			<a href="https://github.com/wldbs98">Hyun Jiyoon</a>
		</div>
	</div>
</body>
</html>