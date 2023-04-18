<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ include file="/resources/header.jspf" %>
<!DOCTYPE html>
<html>
<head>
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.3/jquery.min.js"></script>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>사장님 마이페이지</title>
<style>
body{
        padding: 0;
        margin: 0;
      }
      div{
        box-sizing: border-box;
      }
      
      /* alert badge */
      .circle{
        display: inline-block;
        width: 5px;
        height: 5px;
        border-radius: 2.5px;
        background-color: #ff0000;
        position: absolute;
        top: -5px;
        left: 110%;
      }
      
      /* 녹색 텍스트 */
      .green{
        color: #000;
      }
      
      .all-menu{
        max-width: 1200px;
        margin: 0 auto; 
      }
      .wrap{
        background-color: #F8F8F8; 
      }
      /* 녹색배경 */
      .orangeContainer{  
        height: 132px;
        background-color: #FF9139;    
        
        display: flex;
        align-items: flex-end;
        padding: 16px;
      }
      
      .orangeContainer .name{
        font-size: 20px;
        font-weight: bold;
        color: #ffffff;
      } 
      .orangeContainer .modify{
        margin-left: auto;
      }
      
      /* 단골상점 , 상품후기 , 적립금 박스 */
    .summaryContainer{
    background-color: white;  
    display: flex;  
    padding: 21px 16px;  
    height: 90px;
    margin-bottom: 10px;
    }
    /* 단골상점 , 상품후기 , 적립금 */
    .summaryContainer .item{
    flex-grow: 1
    }
    /* 녹색 숫자 */
    .summaryContainer .number{
    font-size: 19px;
    font-weight: bold;
    color: #000;
    }
    /* 텍스트 */
    .summaryContainer .item > div:nth-child(2){
    font-size: 13px;
    }
      
      /*=================== 주문목록 ~ 찜한상품 리스트 ==================*/
      .listContainer{  
        padding: 0;
        background-color: #ffffff;
        margin-bottom: 10px;
      }
      .listContainer .item{  
        display: flex;
        align-items: center;
        padding: 16px;
        color: black;
        text-decoration: none;  
        height: 56px;
        box-sizing: border-box;
      }
      .listContainer .icon{  
        margin-right: 14px;
      }
      .listContainer .text{
        font-size: 16px;
        position: relative;
      }
      .listContainer .right{
        margin-left: auto;
      }
      
      
      /*=================== 내지갑의 보유 적립금 들어가는 부분 ================*/
      .listContainer .smallLight{
        font-size: 14px;
        color: #c2c2c2;  
      }
      .listContainer .smallLight > span{
        margin-left: 10px;
      }
      
      .listContainer .right .blct{
        font-size: 14px;
        font-weight: bold;
        margin-right: 5px;
      }
      
      /*  */
      .listContainer .item:hover{
        background-color: #f8f8f8; 
      }
      .infoContainer .item:hover{
         background-color: #f8f8f8; 
      }
</style>
</head>
<body>	
  <div class="all-menu">
    <div class="wrap">
        <div class="orangeContainer">
          <div>
            <div class="name">${logName }</div>
          </div>    
        </div>
        <div class="summaryContainer">
            <div class="item">
                <div class="number">15</div>
                <div>예약미확인</div>
              </div>
              <div class="item">
                <div class="number">5</div>
                <div>방문미확인</div>
              </div>
          </div>  
          
        </div>  
        <div class="listContainer">
          <a href="/ownerpage/storeRegister" class="item">
              <div class="icon">ii</div>
              <div class="text">가게등록<span class="circle"></span></div>
              <div class="right"> > </div>
          </a>
          <a href="/ownerpage/storeInfoEdit" class="item">
              <div class="icon">ii</div>
              <div class="text">가게정보수정</div>
              <div class="right"> > </div>
          </a>
          <a href="#information" class="item">
              <div class="icon">ii</div>
              <div class="text">가게정보보기</div>
              <div class="right"> > </div>
          </a>
          <a href="/ownerpage/reservation" class="item">
              <div class="icon">ii</div>
              <div class="text">예약확인</div>
              <div class="right"> > </div>
          </a>
          <a href="/ownerpage/commentManager" class="item">
              <div class="icon">ii</div>
              <div class="text">댓글관리 및 쿠폰주기</div>
              <div class="right"> > </div>
          </a>
          <a href="/ownerpage/advApply" class="item">
            <div class="icon">ii</div>
            <div class="text">먹풍리스트 신청</div>
            <div class="right"> > </div>
        </a>
        </div>
        <div class="listContainer">
          <a href="/ownerpage/userInfoEdit" class="item">
              <div class="icon">ii</div>
              <div class="text">회원정보수정</div>
              <div class="right"> > </div>
          </a>   
        </div>
        
      </div>
</body>
</html>