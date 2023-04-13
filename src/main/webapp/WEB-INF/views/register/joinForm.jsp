<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ include file="/resources/header.jspf" %>
<link type="text/css" rel="stylesheet" href="/style/joinFormStyle.css"/>

<script>
	// 정규표현식
	var reg_id = /^[A-Za-z]{1}\w{7,14}$/;
	var reg_pass = /^(?=.*[A-Za-z])(?=.*\d)(?=.*[$@$!^%#?&])[A-Za-z\d$@$!%^*#?&]{8,}$/; // 최소 8 자, 최소 하나의 문자, 하나의 숫자 및 하나의 특수 문자 
	var reg_name = /^[가-힣]{2,5}$/;
	var reg_email = /^([0-9a-zA-Z_-]+){3,20}@([0-9a-zA-Z_-]+)(\.[0-9a-zA-Z_-]+){1,2}$/; // 이메일 형식 + @ 앞에 길이제한 3,20
	var reg_phone = /^\d{3}-\d{4}-\d{4}$/;
	var reg_nickname = /^[가-힣\dA-Za-z\d$@$!%^*#?&]{2,20}$/;
	var reg_addr = /^[가-힣]{5,100}$/;
	
	function keyevent(){
		
		const id_element = document.getElementById('login_param_check_txt_id');
	    const pass_element = document.getElementById('login_param_check_txt_pass');
	    const pass_chk_element = document.getElementById('login_param_check_txt_pass_chk');
	    const name_element = document.getElementById('login_param_check_txt_name');
	    const email_element = document.getElementById('login_param_check_txt_email');
	    const phone_element = document.getElementById('login_param_check_txt_phone');
	    const login_otp_element = document.getElementById('otp_request');
	    const value_element = document.getElementById('login_param_check_txt_value');
	    
		
		// 아이디
		$('#user_id').keyup(function(){
	       var txt_val = $('#user_id').val();
	       $("#idStatus").val("N");
	       
	       if(!reg_id.test(txt_val)){
	          id_element.style.color="red";
	          id_element.innerHTML= "아이디의 첫번째 문자는 영어대소문자로 작성해야하며,\n영대소문자, 숫자, _ 가 포함가능하고 길이는 8~15글자이어야 해요 .";
	       }else{
	          id_element.style.color="blue";
	          id_element.innerHTML= '아이디를 올바르게 입력 했어요 !';
	       }
	    });
		
		// 비밀번호
		$('#user_password').keyup(function(){
	         var txt_val = $('#user_password').val();
	         var txt_val_chk = $('#user_password_chk').val();
	         
	         if(!reg_pass.test(txt_val)){
	            pass_element.style.color="red";
	            pass_element.innerHTML= "비밀번호는 최소 8 자, 최소 하나의 문자,숫자,특수문자가 포함 되어야해요 !";
	         }else{
	            if(txt_val_chk.length > 0 && (!(txt_val == txt_val_chk))){
	               pass_element.style.color="red";
	               pass_element.innerHTML= "비밀번호와 재확인 비밀번호와 값이 달라요 .";
	            }else{
	               pass_element.style.color="blue";
	               pass_element.innerHTML= '비밀번호를 올바르게 입력 했어요 !';
	            }
	         }
	      });
	      
	      // 비밀번호 재확인
	      $('#user_password_chk').keyup(function(){
	         var txt_val = $('#user_password').val();
	         var txt_val_chk = $('#user_password_chk').val();
	         
	         if(txt_val.length > 0 && (!(txt_val == txt_val_chk))){
	            pass_chk_element.style.color="red";
	            pass_chk_element.innerHTML= "비밀번호와 재확인 비밀번호와 값이 달라요 .";
	         }else{
	            if(txt_val.length > 0){
	               pass_chk_element.style.color="blue";
	               pass_chk_element.innerHTML= '비밀번호 재확인 되었어요 !';
	            }else{
	               pass_chk_element.style.color="red";
	               pass_chk_element.innerHTML= "비밀번호를 먼저 입력해주세요 .";
	            }
	         }
	      });
	      
	      // 이름
	      $('#user_name').keyup(function(){
	          var txt_val = $('#user_name').val();
	          
	          if(!reg_name.test(txt_val)){
	             name_element.style.color="red";
	             name_element.innerHTML= "이름은 한글 2~5글자만 입력 가능해요 !";
	          }else{
	             name_element.style.color="blue";
	             name_element.innerHTML= '이름을 올바르게 입력 했어요 !';
	          }
	       });
	      
	      // 닉네임
	      $('#nickname').keyup(function(){
	    	  $("#nicknameStatus").val("N");
	      });
	      
	      // 이메일
	      $('#email').keyup(function(){
	          var txt_val = $('#email').val();
	          
	          if(!reg_email.test(txt_val)){
	             email_element.style.color="red";
	             email_element.innerHTML= "이메일 형식으로 @ 앞에 3~20글자만 가능해요 !";
	          }else{
	             email_element.style.color="blue";
	             email_element.innerHTML= '이메일을 올바르게 입력 했어요 !';
	          }
	       });	 
	      
	      // 전화번호
	      $('#phone_number').keyup(function(){
	         var txt_val = $('#phone_number').val().replace(/^(\d{2,3})(\d{3,4})(\d{4})$/, `$1-$2-$3`);
	         var txt_sub = txt_val.substr(0,3);
	         
	         if(!reg_phone.test(txt_val) || txt_sub !='010'){
	            phone_element.style.color="red";
	            login_otp_element.style.background="gray";
	            $('.otp_request').attr("disabled" , true);
	            phone_element.innerHTML='010부터 하이폰 (-) 없이 입력 해야해요 !';
	         }else{
	            phone_element.style.color="blue";
	            login_otp_element.style.background="red";
	            $('.otp_request').attr("disabled" , false);
	            phone_element.innerHTML=  txt_val + ' > 인증번호를 요청해주세요   ';
	         }
	      });
	}
	
	      // 회원가입
	      function new_face_event(){
	          
	          var id_val = $('#user_id').val();
	          var pass_val = $('#user_password').val();
	          var pass_chk_val = $('#user_password_chk').val();
	          var name_val = $('#user_name').val();
	          var nickname_val = $('#nickname').val();
	          var email_val = $('#email').val();
	          var phone_val = $('#phone_number').val().replace(/^(\d{2,3})(\d{3,4})(\d{4})$/, `$1-$2-$3`);
	          var address_val = $('#address').val();
	          var otp_val = $('#otp').val();
	          
	          if( !(reg_id.test(id_val)) || !(reg_pass.test(pass_val)) || !(pass_val == pass_chk_val) || !(reg_name.test(name_val)) || !(reg_nickname.test(nickname_val)) || 
	        		!(reg_email.test(email_val)) || !(reg_phone.test(phone_val)) || !(reg_addr.test(address_val)) || (otp_val == "") ||  (otp_val == null)){
					alert('입력이 안되었거나 잘못 입력된 값이 있어요.');
					return false;
	          }else if($("#idStatus").val()=="N"){
	        	  alert("아이디 중복검사를 하세요");
				  return false;
	          }else if($("#nicknameStatus").val()=="N"){
	        	  alert("닉네임 중복검사를 하세요");
				  return false;
	          }else{
	        	  $("#joinForm").attr("action", "joinOk");
	          }
	       }
	    
	    // 아이디 중복검사
	   	function id_double_chk(){
			if($("#user_id").val()!=""){
				window.open("/register/idCheck?user_id="+$("#user_id").val(), "chk", "width=400, height=300");
			}else{
				alert("아이디를 입력 후 중복검사하세요.");
			}
		}
		// 닉네임 중복검사
		function nickname_double_chk(){
			if($("#nickname").val()!=""){
				window.open("nicknameCheck?nickname="+$("#nickname").val(), "chk", "width=400, height=300");
			}else{
				alert("닉네임을 입력 후 중복검사하세요.");
			}
		}
		
	// 전화번호 인증
	function create_otp(){
      var txt_val = $('#phone_number').val().replace(/^(\d{2,3})(\d{3,4})(\d{4})$/, `$1-$2-$3`);
      const otp_element = document.getElementById('#otp');
      const phone_element = document.getElementById('login_param_check_txt_phone');
      const otp_request_element = document.getElementById('otp_request');
      
      phone_element.innerHTML=  txt_val + '(으)로 인증번호를 요청했어요.';
      otp_request_element.style.background="gray";
      $('#otp').css('display','block');
      $('#create_timer').css("display" , 'block');
      $('.otp_request').attr("disabled" , true);
      
      var ctn_val = $('#phone_number').val();
      
      if(ctn_val > 10){
         $.ajax({
              type: "post",
              url: "/sendSMS",
              //async: false,
              dataType : "json",
              data: {
                  "phone_number" : ctn_val, 
              },
              success: function(data){
                    var otp_num = data.otp_num;
                    
                    $('#otp').keyup(function(){
                       
                    var otp_txt = $('#otp').val();
                    
                    // 인증 후 keyup 이벤트 막기 위함.
                    if(otp_flag){
                       return false;
                    }
                    
                    if(otp_num == otp_txt){
                       alert('인증에 성공했어요!');
                       otp_flag = true;
                       $('#otp').attr("readonly" , true);
                       $('.new_face_btn').css('display','inline-block');
                       $('#create_timer').css("display" , 'none');
                    }else if(otp_num != otp_txt && otp_txt.length > 5){
                       alert('인증번호가 일치하지 않아요');
                    }
                 });
            },beforeSend:function(){
                 $(':input').attr("readonly" , true);
            },complete:function(){
                 $(':input').attr("readonly" , false);
            }
         });
      }else{
         alert('휴대폰 번호를 확인해주세요!');
      }
      
      var time = 180;
      var min = "";
      var sec = "";
      
      var timer = setInterval(function(){
         min = parseInt(time/60);
         sec = time%60;
         
         document.getElementById('create_timer').innerHTML = min + "분 " + sec + "초";
         time --;
         
         if( time < 0 ){
            clearInterval(timer);
            alert('인증 시간이 초과되었어요');
            phone_element.innerHTML=  txt_val + '  --  인증번호를 요청해주세요   ';
            $('.new_face_btn').css('display','none');
            $('#otp').css('display','none');
            $('#create_timer').css("display" , 'none');
            $('.otp_request').attr("disabled" , false);
            otp_request_element.style.background="red";
         }
      }, 1000);
   }
	
</script>
<div class="all">
<div class="join">
	<h1>회원가입</h1>
	<form method="post" id="joinForm">
		<ul>
			<li>
				<input type="text" id="user_id" name="user_id" class="" placeholder="아이디" onkeyup="keyevent(this)" autocomplete='off'/>
				<input type="button" value="아이디중복검사" onclick="id_double_chk()"/>
				<input type="hidden" id="idStatus" value="N"/>
				<div class="login_param_check" id="login_pass_param_check">
                	<span id="login_param_check_txt_id" class="login_param_check_txt" ></span>
                </div>
			</li>
			<li>
				<input type="password" id="user_password" name="user_password" class="" placeholder="비밀번호" onkeyup="keyevent(this)"  autocomplete='off'/>
			    <div class="login_param_check" id="login_pass_param_check">
                	<span id="login_param_check_txt_pass" class="login_param_check_txt" ></span>
                </div>
			</li>
			<li>
				<input type="password" id="user_password_chk" name="user_password_chk" class="" placeholder="비밀번호확인" onkeyup="keyevent(this)" autocomplete='off'/>
				<div class="login_param_check" id="login_pass_param_check">
                	<span id="login_param_check_txt_pass_chk" class="login_param_check_txt" ></span>
                </div>
			</li>
			<li>
				<input type="text" id="user_name" name="user_name" class="" placeholder="이름" onkeyup="keyevent(this)" autocomplete='off'/>
			 	<div class="login_param_check" id="login_pass_param_check">
                	<span id="login_param_check_txt_name" class="login_param_check_txt" ></span>
                </div>
			</li>
			<li>
				<input type="text" id="nickname" name="nickname" class="" placeholder="닉네임" onkeyup="keyevent(this)" autocomplete='off'/>
				<input type="button" value="닉네임중복검사" onclick="nickname_double_chk()"/>
				<input type="hidden" id="nicknameStatus" value="N"/>
			</li>
			<li>
				<input type="text" id="email" name="email" class="" placeholder="이메일" onkeyup="keyevent(this)" autocomplete='off'/>
			    <div class="login_param_check" id="login_pass_param_check">
                	<span id="login_param_check_txt_email" class="login_param_check_txt" ></span>
                </div>
			</li>
			<li>
				<input type="text" id="address" name="address" class="" placeholder="주소" onkeyup="keyevent(this)" autocomplete='off'/>
			</li>
			<li>성별</li>
			<li>
				<input type="radio" name="gender" value="1"/>남
				<input type="radio" name="gender" value="0"/>여
			</li>
			<li>계정 유형 선택</li>
			<li>
				<input type="radio" name="is_owner" value="1"/>사장님
				<input type="radio" name="is_owner" value="0"/>고객
			</li>
			
			<li>
				<input type="text" id="phone_number" name="phone_number" class="" placeholder="전화번호( - 없이 입력)" onkeyup="keyevent(this)" autocomplete='off'/>
				<div class="login_param_check" id="login_pass_param_check">
                	<span id="login_param_check_txt_phone" class="login_param_check_txt" ></span>
                </div>
				<input type="button" id="otp_request" class="otp_request" value="인증번호요청" autocomplete='off' onclick="create_otp()"/>
				<div class="login_param_check" id="login_pass_param_check">
                	<span id="login_param_check_txt_value" class="login_param_check_txt" ></span>
                </div>
			</li>
		</ul>
	</form>
	
	<form method="post" id="frm_otp" name="frm_otp" action="/sendSMS">
		<ul>
			<li>
				 <input type="hidden" name="phone_number" value=''/>
				 <div class="login_param_check" id="login_pass_param_check">
				 <input type="text" id="otp" name="otp" class="otp" placeholder="인증번호 입력" onkeyup="otpCheck(this)" autocomplete='off'/>
				 </div>
			</li>
		</ul>
	</form>
	
	<div class="timer" id="timer">
    	<div id="create_timer" class="timer"></div>
    </div>
    
		<input type="submit" id="new_face" class="new_face_btn" value="회원가입" onclick="new_face_event()"/> 
		
</div>
</div>
</body>
</html>