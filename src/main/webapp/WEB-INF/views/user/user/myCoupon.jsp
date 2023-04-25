<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<h1>쿠폰 내역</h1>
<c:forEach var="c" items="${list }">
<jsp:useBean id="now" class="java.util.Date"/>
<fmt:parseDate var="exTime" value="${c.expired_period }" pattern="yyyy-MM-dd"/>
<fmt:parseNumber value="${(exTime.time + (1000*60*60*24) - now.time) / (1000*60*60)}" var="def" scope="request"/>
<script>
	function couponDel(){
		location.href = "couponDel?no=${c.seq}";
	}
</script>
	<li>${c.store_name }</li>
	<li>${c.discount_rate }% 할인 쿠폰</li>
	<li>${c.expired_period}까지 사용가능</li>
	<c:if test="${def < 0 }">	<!--  -->
		<script> couponDel(); </script>
	</c:if>

</c:forEach>

