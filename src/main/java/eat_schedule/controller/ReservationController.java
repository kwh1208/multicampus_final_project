package eat_schedule.controller;

import javax.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

public class ReservationController {
	
	@GetMapping("user/reservationInfo")
	public ModelAndView reservationInfo(HttpSession session) {
		ModelAndView mav = new ModelAndView();
		
		return mav;
	}
}
