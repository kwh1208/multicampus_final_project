<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<h1>쿠폰 내역</h1>
<c:forEach var="coupon" items="${list }">
	<li>${coupon.store_name }</li>
	<li>${coupon.discount_rate }% 할인 쿠폰</li>
	<li>${coupon.expired_period}까지 사용가능</li>
</c:forEach>

