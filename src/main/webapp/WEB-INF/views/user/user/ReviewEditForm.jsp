<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<link href="/table/inc/star.css" rel="stylesheet"/>
  <style>
  	/* 창 여분 없애기 */
  	body{
  		margin : 0 auto;
  	}
  	/* 전체 배경화면 색상 */
  	.wrapper_div{
		background-color: #FCFCFC;
	    height: 100%;  	
	    max-width: 1200px;
	    margin : 0 auto;
  	}
 	/* 팝업창 제목 */
  	.subject_div{
	    background-color: #FF7100;
	    color: #fff;
	    padding: 10px;
	    font-weight: bold;
	    text-align: center;
	    max-width: 1200px;
	    margin : 0 auto;
  	}
  	
  	/* 컨텐츠, 버튼 영역 padding */
  	.input_wrap{
  		padding: 30px;
  		text-align: center;
  	}
  	.btn_wrap{
  		padding: 5px 30px 30px 30px;
  		text-align: center;
  	}
  	
  	/* 취소 버튼 */
  	.cancel_btn{
  		margin-right:5px;
  	    display: inline-block;
  	   	height:40px;
	    width: 150px;
	    background-color: #fff;
	    color: #7d7d7d;
	    font-size: 14px;
	    line-height: 20px; 
	    border: 1px solid #dadada;
  	}
  	
  	/* 등록 버튼 */
  	.enroll_btn{
   	    display: inline-block;
   	    line-height:20px;
   	    height:40px;
	    width: 150px;
	    background-color: #FF7100;
	    color: #fff;
	    font-size: 14px;
	    border:none; 	
  	}

	/* 파일 영역 */
	.file_div h2{
		margin : 0;
	}
  	/* 평점 영역 */
  	.rating_div{
  		padding-top: 10px;
  	}
  	.rating_div h4{
  		margin : 0;
  	}
  	select{
  	margin: 15px;
    width: 100px;
    height: 40px;
    text-align: center;
    font-size: 16px;
    font-weight: 600;
  	}
  	/* 리뷰 작성 영역 */
  	.content_div{
  		padding-top: 10px;
  	}
  	.content_div h4{
  		margin : 0;
  	}
  	textarea{
		width: 100%;
	    height: 100px;
	    border: 1px solid #dadada;
	    padding: 12px 8px 12px 8px;
	    font-size: 15px;
	    color: #99999;
	    resize: none;
	    margin-top: 10px;  	
  	}
  
  </style>
<script>
function cancelCheck(){
	if(confirm("정말 취소하시겠습니까 ?")==true){
		history.back();
	}else{
		return false;
	}
	
}

/*function sendData(){
	document.review.submit();
}*/

</script>
<body>
<form method="post" name="myform" id="myform" action="reviewEditOk">
<c:forEach var="review" items="${list }">
	<div class="wrapper_div">
		<div class="subject_div">
			${review.store_name }에 대한 리뷰를 써주세요
			<input type="hidden" name="store_seq" value="${dto.store_seq}"/>
		</div>
		
		<div class="input_wrap">
			<div class="rating_div">
				<filedset>
					<h4>별점을 선택해 주세요.</h4>
					<input type="radio" name="score" value="5" id="rate1"><label
						for="rate1">★</label>
					<input type="radio" name="score" value="4" id="rate2"><label
						for="rate2">★</label>
					<input type="radio" name="score" value="3" id="rate3"><label
						for="rate3">★</label>
					<input type="radio" name="score" value="2" id="rate4"><label
						for="rate4">★</label>
					<input type="radio" name="score" value="1" id="rate5"><label
						for="rate5">★</label>
				</filedset>
			</div>
			<div class="file_div">
				<h4>사진업로드</h4>
				<p>(사진첨부칸)</p>
			</div>
			<div class=content_div">
				<h4>리뷰 작성</h4>
				<textarea name="review" id="review"
						  placeholder="레스토랑과 유저들에게 도움이 되는 따뜻한 리뷰를 작성해주세요."></textarea>
			</div>		
		</div>
		
		<div class="btn_wrap">
			<input type="button" value="취소" class="cancel_btn" onclick="cancelCheck()"/>
			<input type="submit" value="등록" class="enroll_btn"/>
		</div>
	</div>
</c:forEach>
</form>
</body>