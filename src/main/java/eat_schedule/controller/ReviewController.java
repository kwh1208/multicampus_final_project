package eat_schedule.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import eat_schedule.dto.ReviewDTO;
import eat_schedule.service.ReviewService;

@Controller
public class ReviewController {
	@Autowired
	ReviewService service;
	
	@GetMapping("user/myReview")
	public ModelAndView myReview(HttpSession session) { 
		ModelAndView mav = new ModelAndView();
		
		ReviewDTO dto = service.ReviewSelect((String)session.getAttribute("logId"));
		mav.addObject("dto", dto);
		mav.setViewName("user/myReview");	
		return mav;
	}
}
