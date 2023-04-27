package eat_schedule.controller;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Calendar;
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
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import eat_schedule.dto.ReviewDTO;
import eat_schedule.dto.ReviewFileDTO;
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

	// 리뷰 쓰기 저장 (DB)
	@PostMapping("user/ReviewWriteOk")
	public ModelAndView ReviewWriteOk(HttpServletRequest request, ReviewDTO dto, HttpSession session) {
		ReviewFileDTO rfdto = new ReviewFileDTO();
		dto.setUser_id((String)request.getSession().getAttribute("logId"));
		// 1. 파일업로드
		MultipartHttpServletRequest mr = (MultipartHttpServletRequest)request;
		// 2. mr에서 MultipartFile객체 얻어오기(업로드한 파일 수 만큼)
		MultipartFile file = mr.getFile("filename");
		// 3. 파일을 서버에 업로드할 위치의 절대주소
		String path = request.getSession().getServletContext().getRealPath("/uploadfile");
		System.out.println("path: " + path);
		
		if(file.getSize()!=0) {
			SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmss-");
			Calendar calender = Calendar.getInstance();
			String sysFileName = format.format(calender.getTime());
			
			sysFileName += file.getOriginalFilename();
			rfdto.setFilename(sysFileName);
			
			File saveFile = new File(path + "/" + sysFileName);

			try {
				file.transferTo(saveFile);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}else {
			rfdto.setFilename("nan");
		}
		
		ModelAndView mav = new ModelAndView();
		// dto.setFilename(path);
		int result = service.ReviewInsert(dto);
		int fileResult = service.saveData(rfdto);
		System.out.println(rfdto.getFilename());
	
		mav.setViewName("redirect:/user/user/myReview");

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
