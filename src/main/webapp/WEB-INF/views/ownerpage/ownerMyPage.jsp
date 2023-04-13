<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.3/jquery.min.js"></script>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>사장님 마이페이지</title>
<style>
a{
	text-decoration:none;
	color:#FF7100;
}


.nav_bar{
		display:flex;
		flex-direction:row;
		justify-content:space-between;
		position:fixed;
		top:0;
		align-items:center;
		width:100%;
		height:40px;
		line-height:40px;
		padding:10px 10px;
		border-bottom:1px solid #FF7100;
}
.nav_bar ul{
	list-style:none;
	margin:0;
	padding:0;
}
.nav_bar li{
	float:left;
	position:relative;
}
.nav_logo{
	padding-left:0;
	flex:1;
}
.nav_middle{
		display:flex;
		flex:4;
		text-align:center;
}
.nav_middle li{
	padding-left:40px;
	padding-right:10px;
	margin:0;
}
.dropdown a:nth-child(1){
	margin-left:30px;
	padding-left:10px;
}
.nav_right{
	display:flex;
	flex:1;
}
.nav_right li{
	padding-left:10px;
	padding-right:10px;
	margin:0;
}
.nav_bar ul ul{
	display:none;
	position:absolute;
	top:100%;
	left:0;
}
.nav_bar li:hover>ul{
	display:block;
}
.nav_bar a{
	display:block;
	padding:10px;
}
.nav_bar ul ul a{
	width:55px;
	color:#9B4500;
	background-color:#FF7100;
}
.nav_bar ul ul a:hover{
	background-color:#FF9139;
}


.owner-mypage{
  display: flex;
  flex-direction: row;
  max-width: 800px;
  margin-top:80px;
  margin-left:45px;
}

.box1 {
  width: 300px;
  height: 100px;
  background-color: #C65800;
  margin-right: 1px;
  text-align: center;
  line-height : 100px;
  color:#fff;
  font-size: 1.1rem;
  font-weight: bold;
  text-decoration: none;
}
.box {
  width: 300px;
  height: 100px;
  background-color: #FF9139;
  margin-right: 1px;
  text-align: center;
  line-height : 100px;
  color:#fff;
  font-size: 1.1rem;
  font-weight: bold;
  text-decoration: none;
}

.container {
  display: flex;
  flex-direction: row;
  max-width: 800px;
  margin:20px;
}
.nav-menubar{
  width: 30%;
  background-color: #fff;
  padding: 2rem;
}


.nav-menu {
  list-style: none;
  margin: 0;
  padding: 0;
}

.nav-menu li {
  margin-bottom: 1rem;
}

.nav-menu li a {
  color: #FF9139;
  text-decoration: none;
  font-weight: bold;
  font-size: 1.1rem;
}

.nav-menu li a:hover {
  color: #007bff;
}

.content {
  width: 80%;
  padding: 1rem;
}
</style>
</head>
<body>	
	 <nav class="nav_bar">
	
		<div class="nav_logo"> 
			<a href="/">로고</a>
		</div>
		
		<ul class="nav_middle">
			<li><a href="">이벤트</a></li>
			<li class="dropdown"><a href="">게시판</a>
				<ul class="submenu">
					<li><a href="">FAQ</a></li>
					<li><a href="">1:1문의</a></li>
				</ul>
			</li>
		</ul>
		
		<ul class="nav_right">
				<li><a href="/register/loginForm">로그인</a></li>
				<li><a href="/register/joinForm">회원가입</a></li>
		</ul>
	</nav>


  <div class="owner-mypage">
    <div class="box1">사장님페이지</div>
    <div class="box">예약미승인</div>
    <div class="box">2건</div>
    <div class="box">식사완료미체크</div>
    <div class="box">5건</div>
  </div>
    <div class="container">
        <nav class="nav-menubar">
          <ul class="nav-menu">
            <li><a href="/ownerpage/storeRegister">가게등록</a></li>
            <li><a href="#information">내가게정보보기</a></li> <!--예약페이지에 가게 링크 가져옴-->
            <li><a href="/ownerpage/storeInfoEdit">가게정보수정</a></li>
            <li><a href="/ownerpage/reservation">예약확인</a></li>
            <li><a href="/ownerpage/commentManager">리뷰확인 및 쿠폰증정</a></li>
            <li><a href="/ownerpage/advApply">광고리스트신청</a></li>
            <li><a href="/ownerpage/userInfoEdit">회원정보수정</a></li>
            <li><a href="/ownerpage/failResult">실패페이지</a></li>
          </ul>
        </nav>
        <div class="content">
          <section id="register">
            <h2>가게등록</h2>
            <p>가게등록내용</p>
          </section>
        </div>
      </div>
</body>
</html>