package eat_schedule.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;


import eat_schedule.dto.StoreDTO;
import eat_schedule.service.OwnerService;

@Controller
@RequestMapping("/ownerpage")
public class OwnerController {
	@Autowired
	OwnerService service;
	
	@GetMapping("ownerMyPage")
	public String ownerPage() {
		//사장님 마이페이지(기본)접속
		return "ownerpage/ownerMyPage";
	}
	@GetMapping("reservation")
	public String reservation() {
		//사장님 마이페이지 중 예약확인페이지
		return "ownerpage/reservation";
	}
	@GetMapping("storeRegister")
	public String storeRegister() {
		//사장님 마이페이지 중 가게등록 페이지
		return "ownerpage/storeRegister";
	}
	@RequestMapping(value="/storeRegisterOk", method=RequestMethod.POST)
	public ModelAndView storeRegisterOk(StoreDTO store){
		ModelAndView mav=new ModelAndView();
		int result=service.storeRegisterOk(store);
		if(result>0) {//가게등록 성공
			mav.setViewName("rediect:ownerMyPage");
		}else {//가게등록 실패
			mav.addObject("msg","가게등록실패!!");
			mav.setViewName("ownerpage/failResult");
		}		
		return mav;
	}
	@GetMapping("storeInfoEdit")
	public String storeInfoEdit(Model model,HttpSession session) {
		StoreDTO store=service.storeInfoEdit((String)session.getAttribute("logId"));		
		model.addAttribute("store",store);
		return "ownerpage/storeInfoEdit";
	}
	@RequestMapping(value="/storeInfoEditOk", method=RequestMethod.POST)
	public ModelAndView storeInfoEditOk(StoreDTO store, HttpSession session){
		
		ModelAndView mav=new ModelAndView();
		int result=service.storeInfoEditOk(store);
		if(result>0) {//가게정보수정 성공
			mav.setViewName("rediect:storeInfoEdit");
		}else {//가게등록 실패
			mav.addObject("msg","가게수정실패!!");
			mav.setViewName("ownerpage/failResult");
		}		
		return mav;
	}
	@GetMapping("userInfoEdit")
	public String userInfoEdit() {
		return "ownerpage/userInfoEdit";
	}
	@GetMapping("commentManager")
	public String commentmanager() {
		return "ownerpage/commentManager";
	}
	@GetMapping("advApply")
	public String advapply() {
		return "ownerpage/advApply";
	}
	@GetMapping("menuRegister")
	public String menuRegister() {
		return "ownerpage/menuRegister";
	}
	@RequestMapping("failResult")
	public String failResult(Model model) {
		String msg="실패확인";
		model.addAttribute("msg",msg);
		return "ownerpage/failResult";
	}
}