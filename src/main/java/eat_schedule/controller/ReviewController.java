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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import eat_schedule.dto.ReviewDTO;
import eat_schedule.service.ReviewService;

@Controller
@RequestMapping("/user")
public class ReviewController {
	@Autowired
	ReviewService service;
	
	// 리뷰 목록 불러오기
	@GetMapping("user/myReview")
	public ModelAndView myReview(HttpSession session) { 
		ModelAndView mav = new ModelAndView();
		
		List<ReviewDTO> list = service.ReviewSelect((String)session.getAttribute("logId"));
		mav.addObject("list", list);
		mav.setViewName("user/user/myReview");	
		return mav;
	}

	// 리뷰 저장 (DB)
	@RequestMapping(value="user/ReviewWriteOk", method=RequestMethod.POST)
	public ModelAndView ReviewWriteOk(ReviewDTO dto, HttpSession session) {
		ModelAndView mav = new ModelAndView();
		
		dto.setUser_id((String)session.getAttribute("logId"));
		
		int result = service.ReviewInsert(dto);
		
		if(result>0) {
			mav.setViewName("redirect:/user/user/myReview"); //리뷰등록 성공
		}else {
			mav.addObject("msg", "리뷰 등록 실패");
			mav.setViewName("user/user/joinOkResult");	// 리뷰 등록 실패
		}
		// joinOkResult -> msg알림, 이전페이지
		return mav;
	}
	
	// 리뷰 수정
	@GetMapping("user/reviewEdit")
	public ModelAndView ReviewEditForm(Integer no) {
		ModelAndView mav = new ModelAndView();
		
		ReviewDTO dto = service.ReviewEdit(no);
		mav.addObject("dto", dto);
		mav.setViewName("user/user/reviewEditForm");
		
		return mav;
	}
	                
	// 리뷰 수정 저장(DB)
	@RequestMapping(value="user/reviewEditOk", method=RequestMethod.POST)
	public ModelAndView ReviewEditOk(ReviewDTO dto, HttpSession session) {
		ModelAndView mav = new ModelAndView();
		
		dto.setUser_id((String)session.getAttribute("logId"));
		
		int result = service.ReviewEditOk(dto);
		
		if(result>0) {
			mav.setViewName("redirect:/user/user/myReview"); //리뷰등록 성공
		}else {
			mav.addObject("msg", "리뷰 등록 실패");
			mav.setViewName("user/user/joinOkResult");	// 리뷰 등록 실패
		}
		// joinOkResult -> msg알림, 이전페이지
		return mav;
	}
	
	// 리뷰 삭제
	@GetMapping("user/reviewDel")
	public ModelAndView reviewDel(int no, HttpSession session) {
		ModelAndView mav = new ModelAndView();
		
		int result = service.ReviewDelete(no);
		
		if(result>0) {
			mav.setViewName("redirect:/user/user/myReview"); //리뷰등록 성공
		}else {
			mav.addObject("msg", "리뷰 삭제 실패");
			mav.setViewName("user/user/joinOkResult");	// 리뷰 등록 실패
		}
		return mav;
	}
}
