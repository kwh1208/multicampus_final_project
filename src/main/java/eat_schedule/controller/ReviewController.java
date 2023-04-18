package eat_schedule.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
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
		
		List<ReviewDTO> list = service.ReviewSelect((String)session.getAttribute("logId"));
		mav.addObject("list", list);
		mav.setViewName("user/myReview");	
		return mav;
	}
	
	// 리뷰 쓰기 폼
	@GetMapping("user/reviewWrite")
	public ModelAndView reviewWrite() {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("user/reviewWriteForm");
		return mav;
	}	
	
	// 리뷰 쓰기(DB 저장)
	@PostMapping("/answerWriteOk")
	public ResponseEntity<String> reviewWriteOk(ReviewDTO dto, HttpServletRequest request) {
		ResponseEntity<String> entity = null;
		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-Type", "text/html; charset=UTF-8;");
		// ip
		// dto.setIp(request.getRemoteAddr());
		// logId
		dto.setUser_id((String)request.getSession().getAttribute("logId"));
		
		try {// 등록 성공시
			service.ReviewInsert(dto);
		
			String body = "<script> location.href='/table/user/myReview'; </script>";
			entity = new ResponseEntity<String>(body, headers, HttpStatus.OK);
		}catch(Exception e){// 등록 실패시
			String body = "<script>";
			body += "alert('글등록이 실패하였습니다.');";
			body += "history.go(-1);";
			body += "</script>";
			
			entity = new ResponseEntity<String>(body, headers, HttpStatus.BAD_REQUEST);
		}
		
		return entity;
	}
}
