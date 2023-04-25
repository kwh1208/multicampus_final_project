package eat_schedule.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import eat_schedule.dto.BalloonDTO;
import eat_schedule.service.BalloonService;


@Controller
public class BalloonController {
	@Autowired
	BalloonService service;
	
	// 먹풍 내역 확인
	@GetMapping("user/user/myBalloon")
	public ModelAndView myCoupon(HttpSession session) {
		ModelAndView mav = new ModelAndView();
		
		List<BalloonDTO> list= service.BalloonSelect((String)session.getAttribute("logId"));
		mav.addObject("list", list);
		mav.setViewName("user/user/myBalloon");	
		return mav;

	}
}
