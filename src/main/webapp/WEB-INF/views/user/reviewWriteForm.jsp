<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<link href="/table/inc/star.css" rel="stylesheet"/>
  <style>
  	/* 창 여분 없애기 */
  	body{
  		margin : 0;
  	}
  	/* 전체 배경화면 색상 */
  	.wrapper_div{
		background-color: #FCFCFC;
	    height: 100%;  	
  	}
 	/* 팝업창 제목 */
  	.subject_div{
	    width: 100%;
	    background-color: #FF7100;
	    color: #fff;
	    padding: 10px;
	    font-weight: bold;
	    text-align: center;
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
  	
  	/* 버튼 영역 */
  	.cancel_btn{
  		margin-right:5px;
  	    display: inline-block;
	    width: 130px;
	    background-color: #fff;
	    padding-top: 10px;
	    height: 27px;
	    color: #7d7d7d;
	    font-size: 14px;
	    line-height: 18px; 
	    border: 1px solid #dadada;
  	}
  	.enroll_btn{
   	    display: inline-block;
	    width: 130px;
	    background-color: #FF7100;
	    padding-top: 10px;
	    height: 27px;
	    color: #fff;
	    font-size: 14px;
	    line-height: 18px;   	
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
	    color: #a9a9a9;
	    resize: none;
	    margin-top: 10px;  	
  	}
  
  </style>
<script>
	
	/* 취소 버튼 */
	$(".cancel_btn").on("click", function(e){
		window.close();
	});	
	
</script>
<body>

<form method="post" name="myform" id="myform" action="reviewWriteOk" id="reviewForm">
	<div class="wrapper_div">
		<div class="subject_div">
			리뷰 등록
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
		<a class="cancel_btn">취소</a><a class="enroll_btn">등록</a>
		</div>
	</div>

		<!--  
		<li>
			<input type="submit" value="등록"/>
		</li>
		-->
</form>
</body>


