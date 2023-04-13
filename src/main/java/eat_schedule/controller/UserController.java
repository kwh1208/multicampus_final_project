package eat_schedule.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.ModelAndView;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import eat_schedule.dto.UserDTO;
import eat_schedule.service.UserService;

@Controller
public class UserController {
	@Autowired
	UserService service;
	
	// 회원정보 수정
	@GetMapping("user/joinEdit")
	public ModelAndView joinEdit(HttpSession session) {
		ModelAndView mav = new ModelAndView();
		
		UserDTO dto = service.UserEdit((String)session.getAttribute("logId"));
		mav.addObject("dto", dto);
		mav.setViewName("user/joinEdit");
		
		return mav;
	}
	
	@PostMapping("user/joinEditOk")
	public ModelAndView joinEditOk(UserDTO dto, HttpSession session) {
		ModelAndView mav = new ModelAndView();
		dto.setUser_id((String)session.getAttribute("logId"));
		
		int cnt = service.UserEditOk(dto);
		
		if(cnt>0) {
			mav.setViewName("redirect:joinEdit"); //회원정보수정 성공
		}else {
			mav.addObject("msg", "회원정보수정실패");
			mav.setViewName("user/joinOkResult");	//회원정보수정 실패 
		}
		// joinOkResult -> msg알림, 이전페이지
		return mav;

	}
	
}
