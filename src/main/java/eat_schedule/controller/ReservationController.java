package eat_schedule.controller;

import java.util.List;

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
	
	// 예약내역 확인
	@GetMapping("user/myReservation")
	public ModelAndView myReservation(HttpSession session) { 
		ModelAndView mav = new ModelAndView();
		
		List<ReservationDTO> list = service.ReservationSelect((String)session.getAttribute("logId"));
		mav.addObject("list", list);
		mav.setViewName("user/myReservation");	
		return mav;

	}
}
