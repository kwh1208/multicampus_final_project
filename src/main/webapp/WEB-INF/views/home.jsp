<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ include file="/resources/header.jspf" %>


<style>

	.container{
		width:1200px;
		margin:0 auto;
		display:flex;
	}
	#search{
		width:100px;
		height:50px;
		line-height:50px;
		background-color:#FF7100;
		border:none;
		border-radius:30px;
		color:white;
		font-size:1.2em;
	}
	#select_box{
		margin-top:350px;
		display:flex;
		flex-direction: row;
		margin-left:100px;
	}
	#inner_box{
		margin:0 auto;
	
	}
	#text_box{
		margin:30px auto 100px 400px;
		text-align:center;
		font-size:1.2em;
		color:gray;
	}
	select{
		appearance: none;
        -webkit-appearance: none;
        -moz-appearance: none;
        width:400px;
        height:50px;
        line-height:50px;
        border: 2px solid #FF7100;
        border-radius:20px;
        padding:0 10px;
	}
	select::-ms-expand{ 
		display: none;
    } 
    select:focus{
		outline:none;
	}
	#foodImg{
		margin-left:120px;
		margin-top:-140px;
	}
</style>
<script>
	const selectBox = document.querySelector('#select_box');
	const searchBtn = selectBox.querySelector('#search');
	const storeList = document.querySelector('#store_list');
	
	// 검색 버튼 클릭 시 AJAX 요청 보내기
	searchBtn.addEventListener('click', function() {
	  const district = selectOption.value;
	  fetch('/api/stores?district=' + district)
	    .then(response => response.json())
	    .then(data => {
	      // 응답 처리
	      const stores = data.stores;
	      storeList.innerHTML = ''; // 이전 검색 결과 삭제
	      stores.forEach(store => {
	        // 검색 결과를 동적으로 생성하여 HTML에 추가
	        const li = document.createElement('li');
	        li.textContent = store.name;
	        storeList.appendChild(li);
	      });
	    })
	    .catch(error => console.error(error));
	});


</script>
<div class="container">
	<div id="select_box">
		<div id="inner_box">
			<select>
				<option>지역 선택&nbsp;&nbsp;(&nbsp;클릭하여 지역을 선택해주세요.&nbsp;)</option>
				<option value="홍대">홍대</option>
				<option value="강남">강남</option>
				<option value="왕십리">왕십리</option>
			</select>
			<input id="search" type="submit" value="검색하기" onclick="location.href='storeList'"/>
			<p style="text-align:center; margin-left:-90px; margin-top:30px; color:gray;">
				Search restaurants where you want to go,<br/>
				browse menus and reviews.<br/>
				Then you can find places you like, and<br/>
				Make a reservation  with <b>MUKSCHEDULE!</b>
			</p>
		</div>
		<div id="foodImg">
			<img src="/img/pizza.png" style="width:400px; height:400px;">
		</div>
	</div>
</div>
<!-- 
	<c:forEach var="storeDTO" items="${list}">
		<ul>
			<li>${storeDTO.district}</li>
		</ul>
	</c:forEach>
 -->
</body>
</html>