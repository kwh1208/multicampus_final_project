package eat_schedule.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import eat_schedule.dto.CouponDTO;
import eat_schedule.service.CouponService;

@Controller
public class CouponController {
	@Autowired
	CouponService service;
	
	// 쿠폰 내역 확인
	@GetMapping("user/myCoupon")
	public ModelAndView myCoupon(HttpSession session) {
		ModelAndView mav = new ModelAndView();
		
		List<CouponDTO> list= service.CouponSelect((String)session.getAttribute("logId"));
		mav.addObject("list", list);
		mav.setViewName("user/myCoupon");	
		return mav;

	}
}
