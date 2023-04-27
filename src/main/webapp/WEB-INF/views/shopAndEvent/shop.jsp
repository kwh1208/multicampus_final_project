<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ include file="/resources/header.jspf" %>

<style>
	body, ul, li{
		padding:0;
		margin:0;
		list-style-type:none;
	}
	.coupon{
		float:left;
		margin:50px 100px 100px 100px;
	}
	.shop_all{
		width:1200px;
		display:flex;
		margin:0 auto;
	}
	#buyCoupon{
		margin:0 auto;
	}
	#useBalloon{
		width:200px;
		height:40px;
		line-height:40px;
		background-color:#FF7100;
		border:none;
		border-radius:30px;
		color:white;
		font-size:1.1em;
	}
	ul{
		line-height:3;
		text-align:center;
	}
	ol>li{
		margin-bottom:9px;
		font-size:0.9em;
	}
	h1{
		margin-top:150px;
		text-align:center;
		color:#696969;
	}
	#useBalloon:hover{
		background-color:#FFA964;
		cursor:pointer;
	}
</style>
<script>

	var balloon = "${balloon}";
		
	function shopping(num, dc, seq){
		if(balloon<(num+5)){
			alert("다음 예약을 이용하시기 위해 보유 먹풍선이" + (num+5) + "개 이상인 경우만 구매 가능합니다.\n문의사항은 1:1문의를 이용해주세요.");
		}else if(balloon>=(num+5) && confirm("먹풍선 " + num + "개를 사용하여 쿠폰을 구매하시겠습니까?")){
			$("#discountRate").attr("value", dc);
			$("#seq").attr("value", seq);
			$("#buyCoupon").attr("action", "buyCouponOk");
			$("#buyCoupon").submit();
			alert("구매 완료하였습니다.\n구매하신 쿠폰은 마이페이지에서 확인 가능합니다.");
		}else{
			alert("구매를 취소하였습니다.");
		}
	}
			
</script>
<h1>쿠폰상점</h1>
<div class="shop_all">
	<form method="post" id="buyCoupon">
		<c:forEach var="sDTO" items="${list}">
			<div class="coupon">
				<ul>
					<li><img src="/img/coupon01.jpeg" style="width:200px; height:230px; border-radius:10%;"></li>
					<li style="color:#696969; font-size:1.1em;">${sDTO.discount_rate}% 할인</li>
					<li><input type="button" id="useBalloon" value="구매하기 먹풍선 - ${sDTO.balloon_number}개" onclick="shopping(${sDTO.balloon_number}, ${sDTO.discount_rate}, ${sDTO.seq})"/></li>
					
					<li><input type="hidden" name="discount_rate" id="discountRate" value=""/></li>
					<li><input type="hidden" name="seq" id="seq" value=""/></li>
					<li><input type="hidden" id="shopBalloon" value="<c:out value="${sDTO.balloon_number}"/>"/></li>
				</ul>
			</div>	
		</c:forEach>
	</form>
</div>
	<div style="width:1080px; margin:0 auto 50px auto; padding:10px; height:170px; background-color:#f8f9fa; color:#696969;">
		[상점 이용시 주의사항]<br/>
		<ol>
			<li>1. 보유한 먹풍선 갯수 확인은 로그인 후 메뉴탭 상단 오른쪽 혹은 마이페이지에서 확인가능합니다.</li>
			<li>2. 쿠폰 구매 시 다음 예약에 사용할 먹풍선을 위해 쿠폰가격+5개의 먹풍선을 보유하고 있어야 합니다.</li>
			<li>3. 상점쿠폰은 '먹스케줄' 사이트에서 예약 시 모든 가게에서 사용가능합니다. (예약 시 쿠폰사용여부 선택)</li>
			<li>4. 구매하신 쿠폰은 마이페이지에서 확인가능합니다.</li>
			<li>5. 상점에서 구매하신 쿠폰의 유효기간은 구매하신 날로부터 1달입니다.</li>
		</ol>
	</div>
</body>
</html>