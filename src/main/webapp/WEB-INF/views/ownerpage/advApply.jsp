<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.3/jquery.min.js"></script>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <!-- iamport.payment.js -->
	<script type="text/javascript" src="https://cdn.iamport.kr/js/iamport.payment-1.1.8.js"></script>
	<script>
	// 주문번호 만들기
	function createOrderNum(){
		const date = new Date();
		const year = date.getFullYear();
		const month = String(date.getMonth() + 1).padStart(2, "0");
		const day = String(date.getDate()).padStart(2, "0");
		
		let orderNum = year + month + day;
		for(let i=0;i<10;i++) {
			orderNum += Math.floor(Math.random() * 8);	
		}
		return orderNum;
	}


	// 카드 결제
	function paymentCard(data) {
		// 모바일로 결제시 이동페이지
		const pathName = location.pathname;
		const href = location.href;
		const m_redirect = href.replaceAll(pathName, "");
			
		IMP.init("가맹점 식별코드"); 
			
		IMP.request_pay({ // param
			pg: "html5_inicis",
		  	pay_method: data.payMethod,
		  	merchant_uid: data.orderNum,
		  	name: data.name,
		  	amount: data.amount,
		   	buyer_email: "",
		   	buyer_name: "",
		  	buyer_tel: data.phone,
		  	buyer_addr: data.deleveryAddress2 + " " + data.deleveryAddress3,
		  	buyer_postcode: data.deleveryAddress1,
		  	m_redirect_url: m_redirect, 
	  	}, 
		function (rsp) { // callback
			if (rsp.success) {
	         // 결제 성공 시 로직,
		        data.impUid = rsp.imp_uid;
		        data.merchant_uid = rsp.merchant_uid;
		        paymentComplete(data);  
				
			} else {
	          // 결제 실패 시 로직,
			}
		});
	}

	// 계산 완료
	function paymentComplete(data) {
		
		 $.ajax({
			url: "/api/order/payment/complete",
	        method: "POST",
	        data: data,
		})
		.done(function(result) {
			messageSend();
	        swal({
				text: result,
				closeOnClickOutside : false
			})
			.then(function(){
				location.replace("/orderList");
			})
		}) // done 
	    .fail(function() {
			alert("에러");
			location.replace("/");
		}) 
	}  
	</script>
    <title>광고리스트신청페이지</title>
    
</head>
<body>
	관고리스트신청
</body>
</html>