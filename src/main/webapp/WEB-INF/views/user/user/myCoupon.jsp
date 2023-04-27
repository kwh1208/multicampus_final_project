<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<h1>쿠폰 내역</h1>
<style>
/*body, ul, li{
	padding:0;
	margin:0;
	list-style-type:none;
}*/

.wrap{
	float:left;
	max-width: 1200px;
	width:400px;
	height:150px;
	border : 1px solid #c0c0c0;
	border-radius:5px;
	padding : 20px 20px;
	box-shadow:0 4px 6px 0 rgba(0,0,0,0.2);
	position:relative;
	background:linear-gradient(90deg, #ffffff 65%, #FF7100 35%);
	margin:50px 50px 100px 50px;
}
.wrap::after {
  position: absolute;
  content: "";
  height: 30px;
  right: -1px;
  z-index: 1;
  top: 80px;
  background-color: #fff;
  width: 20px;
  border-color:transparent;
 /* border : 1px solid #c0c0c0;*/
  border-radius:40px 0px 0px 40px;
}
.coupon_wrap{
	display:flex;
	margin : 10px 10px;
}

.left{
	margin:10px 10px;
	text-align:center;
}
.name{
	letter-spacing:1px;
	font-weight: bold;
	font-size:20px;
}
.text{
	margin-top:10px;
	color:#a9a9a9;
}
.price{
	margin:30px 10px;
	font-size:50px;
	color: #fff;
	margin-left:auto;
}
</style>


<c:forEach var="c" items="${list }">
<jsp:useBean id="now" class="java.util.Date"/>
<fmt:parseDate var="exTime" value="${c.expired_period }" pattern="yyyy-MM-dd"/>
<fmt:parseNumber value="${(exTime.time + (1000*60*60*24) - now.time) / (1000*60*60)}" var="def" scope="request"/>
<script>
	function couponDel(){
		location.href = "couponDel?no=${c.seq}";
	}
</script>

<div class="wrap">
	<div class="coupon_wrap">
		<div class="left">
			<div class="name">${c.store_name }</div>
			<div class="text">전 메뉴 할인 쿠폰</div>
			<div class="text">${c.expired_period}까지 사용가능</div>
		</div>	
		<div class="price">${c.discount_rate }%</div>

		<c:if test="${def < 0 }">
			<script> couponDel(); </script>
		</c:if>
	</div>
</div>
</c:forEach>

