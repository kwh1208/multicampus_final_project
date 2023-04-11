package eat_schedule.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
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
		//System.out.println(id);
		//System.out.println(password);
		
		RegisterDTO dto = service.loginOk(user_id, user_password);
		
		ModelAndView mav = new ModelAndView();
		
		if(dto!=null) {
			session.setAttribute("logId", dto.getUser_id());
			session.setAttribute("logName", dto.getUser_name());
			session.setAttribute("logStatus", "Y");
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
	

}