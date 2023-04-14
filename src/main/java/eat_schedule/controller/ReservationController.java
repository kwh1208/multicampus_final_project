package eat_schedule.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import eat_schedule.dto.ReservationDTO;
import eat_schedule.service.ReservationService;

@Controller
public class ReservationController {
	@Autowired
	ReservationService service;
	
	@GetMapping("user/myReservation")
	public ModelAndView reservationInfo(HttpSession session) { 
		ModelAndView mav = new ModelAndView();
		
		ReservationDTO dto = service.ReservationSelect((String)session.getAttribute("logId"));
		mav.addObject("dto", dto);
		mav.setViewName("user/myReservation");	
		return mav;

	}
}
