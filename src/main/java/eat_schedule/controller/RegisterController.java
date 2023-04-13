package eat_schedule.controller;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import eat_schedule.dto.RegisterDTO;
import eat_schedule.service.RegisterService;

@Controller
@RequestMapping("/register")
public class RegisterController {
	@Autowired
	RegisterService service;
	
	// 로그인
	@GetMapping("loginForm")
	public String login() {
		return "register/loginForm";
	}
	
	@PostMapping("loginOk")
	public ModelAndView loginOk(String user_id, String user_password, HttpServletRequest request, HttpSession session ,HttpServletResponse res) {
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
	
	// 회원가입
	@RequestMapping(value="/joinOk", method=RequestMethod.POST)
	public ModelAndView joinOk(RegisterDTO dto) {
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


}