<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ include file="/resources/header.jspf" %>
<!DOCTYPE html>
<html>
<head>
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.3/jquery.min.js"></script>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>가게등록페이지</title>
<style>
form {
    max-width: 800px;
    margin: 0 auto;
    padding: 20px;
    background-color: #fff;
    border-radius: 10px;
  }
  
  h1 {
    text-align: center;
    margin-top: 100px;
    margin-bottom: 20px;
  }
  
  fieldset {
    margin-bottom: 20px;
    padding: 20px;
    background-color: #fff;
    border-radius: 10px;
  }
  
  legend {
    font-weight: bold;
    font-size: 20px;
  }
  
  label {
    display: block;
    margin-bottom: 5px;
  }
  
  input,
  textarea {
    width: 95%;
    padding: 10px;
    border: 1px;
    border-radius: 5px;
    margin-bottom: 20px;
    /*background-color: #f5f5f5;*/
    border-style:solid;
  }
  
  input[type="checkbox"] {
    margin-right: 5px;
  }
  
  input[type="submit"] {
    background-color: #FF9139;
    color: #fff;
    padding: 10px 20px;
    border: none;
    border-radius: 5px;
    cursor: pointer;
    font-size: 18px;
  }
  
  input[type="submit"]:hover {
    background-color: #C65800;
  }

  div{
    max-width: 800px;
    margin: 0 auto;
    padding: 20px;
    background-color: #fff;
  }
</style>
</head>
<body>
	<div class="main-title">
  <h1>가게 등록</h1>
  </div>
  <form action="storeRegisterOk" ModelAttribute="StoreDTO" method="post">
    <fieldset>
      <legend>기본 정보</legend>
      <label for="owner_id">사장님 ID:</label>
      <input type="text" id="owner_id" name="owner_id" value="${logId}" readonly required>
      <label for="store_name">가게 이름:</label>
      <input type="text" id="store_name" name="store_name" required>
      <label for="location">위치:</label>
      <input type="text" id="location" name="location" required>
      <label for="district">구역:</label>
      <input type="text" id="district" name="district" required>
      <label for="tel_number">전화번호:</label>
      <input type="tel" id="tel_number" name="tel_number" required>
      <label for="owner_comment">사장님 코멘트:</label>
      <textarea id="owner_comment" name="owner_comment"></textarea>
    </fieldset>

    <fieldset>
      <legend>영업 정보</legend>
      <label for="open_time">오픈 시간:</label>
      <input type="text" id="open_time" name="open_time" required>
      <label for="close_time">마감 시간:</label>
      <input type="text" id="close_time" name="close_time" required>
      <label for="how_to_come">찾아오는 길:</label>
      <textarea id="how_to_come" name="how_to_come"></textarea>
      <label for="parking">주차 가능 여부:</label>
      <input type="checkbox" id="parking" name="parking">
      <label for="wifi">와이파이 가능 여부:</label>
      <input type="checkbox" id="wifi" name="wifi">
      <label for="animal">반려동물 출입 가능 여부:</label>
      <input type="checkbox" id="animal" name="animal">
      <label for="group_customer">단체 좌석 제공 여부:</label>
      <input type="checkbox" id="group_customer" name="group_customer">
      <label for="playroom">놀이방 유무:</label>
      <input type="checkbox" id="playroom" name="playroom">
      <label for="disabled">장애인 시설 유무:</label>
      <input type="checkbox" id="disabled" name="disabled">
    </fieldset>
<!--  
	<form action="ownerpage/registerOk" method="post" enctype="multipart/form-data"> 파일업로드시 작성할 폼 태그
    <fieldset>
      <legend>사진 업로드</legend>
      <label for="picture_location">가게 사진:</label>
      <input type="file" id="picture_location" name="picture_location">
    </fieldset>
-->
    <input type="submit" value="가게 등록">
  </form>
</body>
</html>