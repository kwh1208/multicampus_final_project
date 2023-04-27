<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<style>
li{
	list-style-type:none;
}
.history table{
	width: 1200px;
	margin: 0 auto;
	border-collapse: collapse;
	border:1px solid #d7d5d5; 
	color:#353535; 
	line-height:1.5;
}
.history th{
	padding:11px 0 9px; 
	border-left:1px solid #e9e9e9; 
	vertical-align:middle; 
	background:#fbfafa;
}
.history td{
	/*font-family: 나눔고딕,NanumGothic,돋움,Dotum;*/
	font-size : 15px;
	padding:10px 10px; 
	border-top:1px solid #e9e9e9; 
	text-align:center;  
	vertical-align:middle; 
}

.plus{
	color:#FF7100;
}
.num{
	font-weight:bold;
}
.text{
	font-weight: bold;
	margin : 20px 5px 5px auto;
}


</style>
<h1>먹풍 이용 내역</h1>



<div class="history">
<table border="1" summary="">
	<colgroup>
		<col style="width:10%" />
		<col style="width:20%" />
		<col style="width:60%" />
		<col style="width:auto" />
	</colgroup>
	<thead>
	<tr>
		<th scope="col">적립 날짜</th>
		<th scope="col">식당 이름</th>
		<th scope="col">적립 내용</th>
		<th scope="col">먹풍선</th>
	</tr>
	</thead>
	<c:forEach var="b" items="${list }">
	<fmt:parseDate var="useTime" value="${b.use_get_time }" pattern="yyyy-MM-dd HH:mm:ss"/>
	<tbody>
		<tr>
			<td><fmt:formatDate value="${useTime }" pattern="yyyy.MM.dd"/></td>
			<td>${b.store_name }</td>
			<td>${b.content }</td>
			<td class="num">
				<c:if test="${b.balloon > 0 }">
					<div class="plus">+${b.balloon } 개</div>
				</c:if>
				<c:if test="${b.balloon < 0 }">
					<div class="minus">${b.balloon } 개</div>
				</c:if>
			</td>
		</tr>
	</tbody>
	</c:forEach>
</table>
</div>
	<div style="width:1200px; margin:50px auto 50px auto; padding:10px; height:400px; background-color:#f8f9fa; color:#696969;">
		<div class="text">'먹풍선'이란?</div>
			<div>  '먹풍선'은 매장 예약, 쿠폰 결제 시 필요한 수단입니다. </div>
		<div class="text">'먹풍선'을 어떻게 얻나요?</div>
			<div>  - 신규 회원 가입 시, 본인 인증한 이메일 당 최초 1회에 한 해 먹풍선 10개가 지급됩니다.</div>
			<div>  - 포토 리뷰를 작성하면 먹풍선 2개, 글 리뷰를 작성하면 먹풍선 1개가 지급됩니다.</div>
			<div>  - 먹스케줄로 예약 후 방문 확인이 완료되면 먹풍선 8개가 지급됩니다.</div>
		<div class="text">'먹풍선'은 어떻게 사용하나요?</div>
			<div>  - 먹스케줄의 모든 매장에서 예약 1회 당 먹풍선 5개가 필요합니다.</div>
			<div>  - 홈 - 상단메뉴 - 상점 탭에서 쿠폰을 구매할 수 있습니다.</div>
		<div class="text">'먹풍선'은 다시 되돌려 주나요?</div>
			<div>  - 매장이 예약을 취소하면 먹풍선 5개를 돌려드리지만, 고객의 노쇼처리로 확인 되었을 경우에는 먹풍선을 되돌려 드리지 않습니다.</div>
			<div>  - 잦은 노쇼로 인해 보유 먹풍선이 5개 이하가 되면 매장 예약이 금지되고 휴면계정으로 전환됩니다.</div>

	</div>