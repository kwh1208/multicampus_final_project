package eat_schedule.controller;



import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import eat_schedule.dto.RegisterDTO;
import eat_schedule.service.RegisterService;


@Controller
@RequestMapping("/register")
public class RegisterController {
	@Autowired
	RegisterService service;
	
	/*
	@Autowired
	JavaMailSender mailSender;
	*/
	
	// 로그인
	@GetMapping("loginForm")
	public String login() {
		return "register/loginForm";
	}
	
	@PostMapping("loginOk")
	public ModelAndView loginOk(@RequestParam("user_id")String user_id, @RequestParam("user_password")String user_password, HttpServletRequest request, HttpSession session ,HttpServletResponse res) {
		
		//System.out.println(user_id);
		//System.out.println(user_password);
		
		RegisterDTO dto = service.loginOk(user_id, user_password);
		
		ModelAndView mav = new ModelAndView();
		
		if(dto!=null) {
			session.setAttribute("logId", dto.getUser_id());
			session.setAttribute("logName", dto.getUser_name());
			session.setAttribute("logStatus", "Y");
			session.setAttribute("isOwner", dto.getIs_owner());
			mav.setViewName("redirect:/");
		}else {
			mav.setViewName("redirect:loginForm");
		}
		return mav;
	}
	
	// 로그아웃
	@GetMapping("logout")
	public String logout() {
		return "register/logout";
	}
	/*
	// 로그아웃
	@GetMapping("logout")
	public ModelAndView logout(HttpSession session) {
		session.invalidate();
		ModelAndView mav = new ModelAndView();
		mav.setViewName("redirect:/");
		return mav;
	}
	*/
	
	// 회원가입 폼
	@GetMapping("/joinForm")
	public String join() {
		return "register/joinForm";
	}
	
	// 아이디 중복검사
	@GetMapping("idCheck")
	public String idCheck(String user_id, Model model) {

		int result = service.idCheckCount(user_id);

		model.addAttribute("user_id", user_id);
		model.addAttribute("result", result);
		
		return "register/idCheck";
	}
	// 닉네임 중복검사
	@GetMapping("nicknameCheck")
	public String nicknameCheck(String nickname, Model model) {

		int result = service.nicknameCheckCount(nickname);

		model.addAttribute("nickname", nickname);
		model.addAttribute("result", result);
		
		return "register/nicknameCheck";
	}
	// 전화번호 중복검사
	@GetMapping("phoneCheck")
	public String phoneCheck(String phone_number, Model model) {

		int result = service.phoneCheckCount(phone_number);

		model.addAttribute("phone_number", phone_number);
		model.addAttribute("result", result);
		
		return "register/phoneCheck";
	}
	
	// 회원가입
	@RequestMapping(value="/joinOk", method=RequestMethod.POST)
	public ModelAndView joinOk(RegisterDTO dto, HttpServletRequest req) {
		ModelAndView mav = new ModelAndView();

		int result = service.registerInsert(dto);
		
		if(result>0) {// 성공
			mav.setViewName("redirect:loginForm");
		}else {
			mav.addObject("msg", "회원등록을 실패하였습니다.\n다시 시도해주세요.");
			mav.setViewName("register/joinOkResult");
		}	
		return mav;
	}

	// 아이디 찾기
	@GetMapping("idSearchForm")
	public String idSearchForm() {
		return "register/idSearchForm";
	}
	// 비밀번호 찾기
	@GetMapping("passwordSearchForm")
	public String passwordSearchForm() {
		return "register/passwordSearchForm";
	}
	/* 아이디 찾기
	@PostMapping("idSearchEmailSend")
	@ResponseBody
	public String idSearchEmailSend(RegisterDTO dto) {
		String user_id = service.idSearch(dto.getUser_name(), dto.getEmail());
		
		if(user_id==null || user_id.equals("")){
			return "N";
		}else{
			String emailSubject = "아이디 찾기 결과";
			String emailContent = "<div style=background:pink; margin:50px; padding:50px; border:2px solid gray; font-size:2em; text-align:center;>";
			emailContent += "검색한 아이디입니다.";
			emailContent += "아이디: "+user_id;
			emailContent += "</div>"; 
			
			try {
				// mimeMessage -> mimeMessageHelper
				MimeMessage message = mailSender.createMimeMessage();
				MimeMessageHelper messageHelper = new MimeMessageHelper(message, true, "UTF-8");
				
				// 보내는 메일주소
				messageHelper.setFrom("guswldbs98@gmail.com");
				// 받는 쪽
				messageHelper.setTo(dto.getEmail());
				messageHelper.setSubject(emailSubject);
				messageHelper.setText("text/html; charset=UTF-8", emailContent);
				
				mailSender.send(message); // 보내기
				
				return "Y";
			}catch(Exception e) {
				e.printStackTrace();
				return "N";
			}
		}
	}
	*/

}