<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
	<c:set var="store_seq" value="${store.seq }" />
	<c:set var="buyer_name" value="${user.user_name }" />
	<c:set var="buyer_email" value="${user.email }" />
	<c:set var="buyer_tel" value="${user.phone_number }" />
	<c:set var="buyer_addr" value="${user.address }" />
	<c:set var="district" value="${store.district }" />
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.3/jquery.min.js"></script>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
   	<style>
   	body {
	  font-family: Arial, sans-serif;
	  background-color: #f9f9f9;
	}
	
	div {
	  margin: 20px auto;
	  padding: 20px;
	  background-color: #fff;
	  border: 1px solid #ccc;
	  border-radius: 10px;
	  box-shadow: 2px 2px 10px rgba(0, 0, 0, 0.1);
	  max-width: 1200px;
	}
	
	div:first-child {
	  text-align: center;
	  font-size: 24px;
	  font-weight: bold;
	  margin-bottom: 20px;
	}
	
	div > div:last-child {
	  display: flex;
	  justify-content: center;
	  align-items: center;
	  margin-top: 20px;
	}
	
	div > div:last-child input[type="date"] {
	  padding: 8px;
	  font-size: 16px;
	  border: none;
	  border-radius: 4px;
	  margin-right: 10px;
	  box-shadow: inset 0 0 5px rgba(0, 0, 0, 0.1);
	}
	
	div > div:last-child button {
	  padding: 8px 16px;
	  font-size: 16px;
	  border: none;
	  border-radius: 4px;
	  color: #fff;
	  cursor: pointer;
	}
	
	div > div:last-child button:first-child {
	  background-color: #0070c0;
	}
	
	div > div:last-child button:last-child {
	  background-color: #dc3545;
	}
	
	div > div:last-child button:hover {
	  opacity: 0.8;
	}
	input[type="date"] {
			padding: 8px;
			border-radius: 4px;
			border: 1px solid #ccc;
			font-size: 16px;
		}
	button {
	 	background-color: #FF9139;
		color: #fff;
    	border: none;
		border-radius: 5px;
		cursor: pointer;
		font-size: 18px;
	}
	button:hover {
  		background-color: #C65800;
	}
   	</style>
    <!-- step1 -->
    <!-- jQuery -->
    <script type="text/javascript" src="https://code.jquery.com/jquery-1.12.4.min.js" ></script>
    <!-- iamport.payment.js -->
	<script type="text/javascript" src="https://cdn.iamport.kr/js/iamport.payment-1.2.0.js"></script>
<<<<<<< HEAD
=======
	<script>
	// 주문번호 만들기
	const IMP=window.IMP;
	IMP.init("imp");
>>>>>>> refs/remotes/origin/yongtae
	
<<<<<<< HEAD
	<script type="text/javascript">
		function payment(pg_provider){
			//step2
			IMP.init('발급받은키 설정');
			var pg_mid;
			var store_seq= "${store_seq}";
			var buyer_name= "${buyer_name}";
			var buyer_email= "${buyer_email}";
			var buyer_tel="${buyer_tel}";
			var buyer_addr="${buyer_addr}";
			var district="${district}";
			var list_date=$('input[name="list_date"]').val();
			if(pg_provider=='inicis'){
					pg_mid='html5_inicis';
			}else if(pg_provider=='kcp'){
					pg_mid='kcp';
			}			
			alert(pg_mid);
			
			//step3
			const data={
					pg: pg_mid,
					pay_method: "card",
					merchant_uid: "advlist_"+new Date().getTime()+"_"+store_seq,
					name: "먹풍리스트",
					amount: 1000,
					buyer_email: buyer_email,
					buyer_name: buyer_name,
					buyer_tel: buyer_tel,
					buyer_addr: buyer_addr					
			};
			
			IMP.request_pay(data, response => {
				alert('callback!: '+JSON.stringify(response));
				
				//step4
				$.ajax({
					url:"/ownerpage/payment/callback_receive",
					method: "POST",
					headers: {"Content-Type": "application/json"},
					data: JSON.stringify({
				        imp_uid: response.imp_uid,
				        merchant_uid: response.merchant_uid,
				        success: response.success,
				        error_msg: response.error_msg,
				        district: district,
				        list_date: list_date
				    })					
				}).done(function (data) {
					//step6
					alert("please, Check your pament result page!!");
				})
			});
		}	
=======
	var today = new Date();   
    var hours = today.getHours(); // 시
    var minutes = today.getMinutes();  // 분
    var seconds = today.getSeconds();  // 초
    var milliseconds = today.getMilliseconds();
    var makeMerchantUid = hours +  minutes + seconds + milliseconds;
	
    function requestPay() {
        IMP.request_pay({
            pg : 'html5_inicis',
            pay_method : 'card',
            merchant_uid: "IMP"+makeMerchantUid, 
            name : '당근 10kg',
            amount : 1004,
            buyer_email : '',
            buyer_name : '',
            buyer_tel : '',
            buyer_addr : '서울특별시 강남구 삼성동',
            buyer_postcode : '123-456'
        }, function (rsp) { // callback
            if (rsp.success) {
                console.log(rsp);
            } else {
                console.log(rsp);
            }
        });
    }
>>>>>>> refs/remotes/origin/yongtae
	</script>
    <title>광고리스트신청페이지</title>  
</head>
<body>
	<div>
		<div>광고를 원하시는 날짜를 선택하신 후 결제해주세요!</div>
		<div>광고 날짜 선택</div>
		<div>광고 비용 : 1000원</div>
		<input type="date" id="list_date" name="list_date"/>
		<button type="button" onClick="payment('kcp')">kcp 결제</button>
		<button type="button" onClick="payment('inicis')">inicis 결제</button>
	</div>
</body>
</html>
