package eat_schedule.controller;

import java.util.List;
import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;


import eat_schedule.dto.StoreDTO;
import eat_schedule.dto.CouponDTO;
import eat_schedule.dto.FilenameDTO;
import eat_schedule.dto.MenuDTO;
import eat_schedule.dto.RegisterDTO;
import eat_schedule.dto.ReservationDTO;
import eat_schedule.dto.ReviewDTO;
import eat_schedule.service.OwnerService;

@Controller
@RequestMapping("/ownerpage")
public class OwnerController {
	@Autowired
	OwnerService service;
	
	@GetMapping("ownerStart")
	public String ownerStart(Model model, HttpSession session) {
		List<StoreDTO> store=service.storeSelect((String)session.getAttribute("logId"));
		model.addAttribute("store", store);
		return "ownerpage/ownerStart";
	}
	
	@GetMapping("ownerMyPage")
	public String ownerPage(Integer no, Model model, HttpSession session) {
		//사장님 마이페이지(기본)접속
		StoreDTO store=service.storeInfoEdit(no);
		session.setAttribute("storeSeq", store.getSeq());
		int reservationNoCheck=service.reservationNoCheck(store.getSeq());
		int noShowCheckNum=service.noShowCheckNum(store.getSeq());
		model.addAttribute("reservationNoCheck", reservationNoCheck);
		model.addAttribute("noShowCheckNum", noShowCheckNum);
		return "ownerpage/ownerMyPage";
	}
	@GetMapping("reservation")
	public String reservation(Model model, HttpSession session) {
		//사장님 마이페이지 중 예약확인페이지
		StoreDTO store=service.storeInfoEdit((Integer)session.getAttribute("storeSeq"));
		List<ReservationDTO> reservation=service.reservationSelect((Integer)store.getSeq());
		model.addAttribute("reservation", reservation);
		return "ownerpage/reservation";
	}
	@GetMapping("storeRegister")
	public String storeRegister() {
		//사장님 마이페이지 중 가게등록 페이지
		return "ownerpage/storeRegister";
	}
	@PostMapping("storeRegisterOk")
	public ModelAndView storeRegisterOk(HttpServletRequest req,@ModelAttribute("StoreDTO") StoreDTO store, HttpSession session){
		int result=service.storeRegisterOk(store);
		System.out.println(store.toString());
		
		//request: 폼의 데이터들과 첨부파일이 있다.
		
		//MultiPartHttpServletRequest <- request이용하여 구한다.
		
		//1. 파일업로드
		MultipartHttpServletRequest mr=(MultipartHttpServletRequest)req;
		
		//2. mr에서 MultipartFile 객체를 얻어오기 (업로드한 파일의 수만큼 있다.)
		List<MultipartFile> files= mr.getFiles("filename");
		
		//3. 파일을 서버에 업로드할 위치의 절대주소가 필요하다.
		String folderName="/storeuploadfile/store"+store.getSeq()+"/storepicture"; //여기서 알아서 경로 바꾸면 됨
		String path=req.getSession().getServletContext().getRealPath(folderName);
		System.out.println("path->"+path);
		
		Path directoryPath = Paths.get(path);
		 
        try {
            // 디렉토리 생성
            Files.createDirectories(directoryPath);
 
            System.out.println(directoryPath + " 디렉토리가 생성되었습니다.");
 
        }catch (IOException e) {
            e.printStackTrace();
        }
		
		//-------업로드 시자 -> 같은 파일이 존재할 때 파일명을 만들어 주어야 한다. ------
		List<FilenameDTO> fileList = new ArrayList<FilenameDTO>();
		if(files!=null){//업로드 파일이 있을때
		
			for(int i=0; i<files.size(); i++){//업로드한 파일의 수만큼 반복수행
				MultipartFile mf = files.get(i);
				
				String orgFilename = mf.getOriginalFilename();//클라이언트가 업로드한 원래 파일명을 구한다.
				if(orgFilename !=null && !orgFilename.equals("")) {
					//파일객체가 있는지 확인후 같은 파일이 있으면 파일명을 변경한다.
					File f = new File(path, orgFilename);
					
					if(f.exists()) {// file 있으면 true, 없으면 false
						//    a.gif -> a(1).gif ->a(2).gif -> ...
						for(int renameNum=1;;renameNum++) {// 1,2,3,4......
							// 파일명, 확장자를 나눈다.
							// 마지막 .의 위치구한다.
							int point = orgFilename.lastIndexOf(".");
							String orgFile = orgFilename.substring(0, point);// 확장자 뺀 파일명
							String orgExt = orgFilename.substring(point+1);// 확장자 gif
							
							String newFilename= orgFile+" ("+renameNum+")."+orgExt; //새로만드는 파일명
							f = new File(path, newFilename);
							if(!f.exists()) {// 새로만들 파일이 존재하지 않으면 반복문 중단
								orgFilename= newFilename;
								break;
							}
						}//for
						//새로운 파일명을 찾았을때
						//업로드 수행, 파일명보관
						
					}// if -> f.exists()
					try {
						mf.transferTo(new File(path, orgFilename));
					} catch(Exception e) {}
					
					FilenameDTO fnDTO=new FilenameDTO();
					fnDTO.setFilename(orgFilename);
					fileList.add(fnDTO);
				}//if->rename
			}
		}//if 업로드 파일이 있을때
		//----------------------------------------------------------------
		ModelAndView mav=new ModelAndView();
		store.setPicture_location(path);
		System.out.println(store.getPicture_location());
		int result2=service.pictureDirInsert(store);
		if(result>0 && result2>0) {//가게등록 성공
			List<StoreDTO> store1=service.storeSelect((String)session.getAttribute("logId"));
			mav.addObject("store", store1);
			mav.setViewName("ownerpage/ownerStart");
		}else {//가게등록 실패
			mav.addObject("msg","가게등록실패!!");
			mav.setViewName("ownerpage/failResult");
		}		
		return mav;
	}
	@PostMapping("menuRegisterOk")
	public ModelAndView menuRegisterOk(HttpServletRequest req,@ModelAttribute("MenuDTO") MenuDTO menu, HttpSession session){
		//request: 폼의 데이터들과 첨부파일이 있다.
		
		//MultiPartHttpServletRequest <- request이용하여 구한다.
		
		//1. 파일업로드
		MultipartHttpServletRequest mr=(MultipartHttpServletRequest)req;
		
		//2. mr에서 MultipartFile 객체를 얻어오기 (업로드한 파일의 수만큼 있다.)
		List<MultipartFile> files= mr.getFiles("filename");
		
		//3. 파일을 서버에 업로드할 위치의 절대주소가 필요하다.
		String folderName="/storeuploadfile/store"+(Integer)session.getAttribute("storeSeq")+"/menupicture";
		String path=req.getSession().getServletContext().getRealPath(folderName);
		System.out.println("path->"+path);
		
		Path directoryPath = Paths.get(path);
		
		try {
            // 디렉토리 생성
            Files.createDirectories(directoryPath);
 
            System.out.println(directoryPath + " 디렉토리가 생성되었습니다.");
 
        }catch (IOException e) {
            e.printStackTrace();
        }
		
		//-------업로드 시자 -> 같은 파일이 존재할 때 파일명을 만들어 주어야 한다. ------
		List<FilenameDTO> fileList = new ArrayList<FilenameDTO>();
		if(files!=null){//업로드 파일이 있을때
		
			for(int i=0; i<files.size(); i++){//업로드한 파일의 수만큼 반복수행
				MultipartFile mf = files.get(i);
				
				String orgFilename = mf.getOriginalFilename();//클라이언트가 업로드한 원래 파일명을 구한다.
				if(orgFilename !=null && !orgFilename.equals("")) {
					//파일객체가 있는지 확인후 같은 파일이 있으면 파일명을 변경한다.
					File f = new File(path, orgFilename);
					
					if(f.exists()) {// file 있으면 true, 없으면 false
						//    a.gif -> a(1).gif ->a(2).gif -> ...
						for(int renameNum=1;;renameNum++) {// 1,2,3,4......
							// 파일명, 확장자를 나눈다.
							// 마지막 .의 위치구한다.
							int point = orgFilename.lastIndexOf(".");
							String orgFile = orgFilename.substring(0, point);// 확장자 뺀 파일명
							String orgExt = orgFilename.substring(point+1);// 확장자 gif
							
							String newFilename= orgFile+" ("+renameNum+")."+orgExt; //새로만드는 파일명
							f = new File(path, newFilename);
							if(!f.exists()) {// 새로만들 파일이 존재하지 않으면 반복문 중단
								orgFilename= newFilename;
								break;
							}
						}//for
						//새로운 파일명을 찾았을때
						//업로드 수행, 파일명보관
						
					}// if -> f.exists()
					try {
						mf.transferTo(new File(path, orgFilename));
					} catch(Exception e) {}
					
					FilenameDTO fnDTO=new FilenameDTO();
					fnDTO.setFilename(orgFilename);
					fileList.add(fnDTO);
				}//if->rename
			}
		}//if 업로드 파일이 있을때
		//----------------------------------------------------------------
		//4. 메인 글 insert 구현하기 - 생성된 시퀀스를 구해오기

		// 원글의 시퀀스를 번호를 파일명이 있는 dto에 셋팅하기
//		for(FilenameDTO fDTO:fileList) {
//			fDTO.setSeq(menu.getSeq());
//		}
		//5. 원글 시퀀스, 파일명 DB에 추가
		
		ModelAndView mav= new ModelAndView();
		
		StoreDTO store=service.storeInfoEdit((Integer)session.getAttribute("storeSeq"));
		int reservationNoCheck=service.reservationNoCheck(store.getSeq());
	    int noShowCheckNum=service.noShowCheckNum(store.getSeq());
	    mav.addObject("reservationNoCheck", reservationNoCheck);
	    mav.addObject("noShowCheckNum", noShowCheckNum);
		mav.setViewName("ownerpage/ownerMyPage");
		
		return mav;
	}
	
	@GetMapping("storeInfoEdit")
	public String storeInfoEdit(Model model,HttpSession session) {
		StoreDTO store=service.storeInfoEdit((Integer)session.getAttribute("storeSeq"));		
		model.addAttribute("store",store);
		return "ownerpage/storeInfoEdit";
	}
	@PostMapping("storeInfoEditOk")
	public ModelAndView storeInfoEditOk(@ModelAttribute("StoreDTO") StoreDTO store, HttpSession session){
		store.setSeq((Integer)session.getAttribute("logStoreSeq"));
		ModelAndView mav=new ModelAndView();
		int result=service.storeInfoEditOk(store);
		if(result>0) {//가게정보수정 성공
		mav.addObject("reservationNoCheck", service.reservationNoCheck(store.getSeq())); // 모델에 reservationNoCheck 추가
		mav.addObject("noShowCheckNum", service.noShowCheckNum(store.getSeq()));
		mav.setViewName("ownerpage/ownerMyPage");
		}else {//가게등록 실패
		mav.addObject("msg","가게수정실패!!");
		mav.setViewName("ownerpage/failResult");
		}
		return mav;
	}
	@GetMapping("userInfoEdit")
	public String userInfoEdit(Model model,HttpSession session) {
		RegisterDTO user=service.userInfoEdit((String)session.getAttribute("logId"));
		model.addAttribute("user", user);
		return "ownerpage/userInfoEdit";
	}
	@PostMapping("userInfoEditOk")
	public ModelAndView joinEditOk(@ModelAttribute("RegisterDTO") RegisterDTO user, HttpSession session) {
		user.setUser_id((String)session.getAttribute("logId"));
		
		int cnt=service.userInfoEditOk(user);
		ModelAndView mav=new ModelAndView();
		if(cnt>0){// 수정성공시 -> db에서 수정된 내용을 보여주고
			StoreDTO store=service.storeInfoEdit((Integer)session.getAttribute("storeSeq"));
			int reservationNoCheck=service.reservationNoCheck(store.getSeq());
		    int noShowCheckNum=service.noShowCheckNum(store.getSeq());
		    mav.addObject("reservationNoCheck", reservationNoCheck);
		    mav.addObject("noShowCheckNum", noShowCheckNum);
			mav.setViewName("ownerpage/ownerMyPage");
		}else{// 수정실패시 -> 이전페이지 (알림)
			mav.addObject("msg","회원정보수정 실패!!");
			mav.setViewName("register/failResult");
		}
		return mav;
	}
	@GetMapping("commentManager")
	public String commentmanager(Model model, HttpSession session) {
		StoreDTO store=service.storeInfoEdit((Integer)session.getAttribute("storeSeq"));
		List<ReviewDTO> review=service.reviewSelect((Integer)store.getSeq());
		model.addAttribute("review", review);
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
	@PostMapping("couponGift")
	public ModelAndView couponGift(@ModelAttribute("CouponDTO") CouponDTO coupon, HttpSession session) throws ParseException {
		ModelAndView mav= new ModelAndView();
		SimpleDateFormat sdfYMD = new SimpleDateFormat("yyyy-MM-dd");
		
		Calendar now = Calendar.getInstance();
		int year = now.get(Calendar.YEAR);
		int month = now.get(Calendar.MONTH)+1;  // 월: 0~11
		int day = now.get(Calendar.DAY_OF_MONTH);
		String year1=Integer.toString(year);
		String month1=Integer.toString(month);
		String day1=Integer.toString(day);
		String now_date = year1+"-"+month1+"-"+day1;

		//String을 날짜 연산을 위해 Date 객체로 변경
		Date date = sdfYMD.parse(now_date); 

		//날짜 연산을 위한 Calendar객체 생성 후 date 대입
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		
		cal.add(Calendar.MONTH, 1); // 한달 더하기
		String expired_period=sdfYMD.format(cal.getTime());
		
		coupon.setExpired_period(expired_period);
        coupon.setDiscount_rate(0);
        int cnt=service.couponGift(coupon);
        int cnt2=service.couponStatus(coupon.getReview_seq());
        if(cnt>0 && cnt2>0){// 수정성공
        	StoreDTO store=service.storeInfoEdit((Integer)session.getAttribute("storeSeq"));
        	int reservationNoCheck=service.reservationNoCheck(store.getSeq());
		    int noShowCheckNum=service.noShowCheckNum(store.getSeq());
		    mav.addObject("reservationNoCheck", reservationNoCheck);
		    mav.addObject("noShowCheckNum", noShowCheckNum);
			mav.setViewName("ownerpage/ownerMyPage");
		}else{// 수정실패시 -> 이전페이지 (알림)
			mav.addObject("msg","쿠폰주기 실패!!");
			mav.setViewName("register/failResult");
		}
		return mav;
	}
	@PostMapping("ownerCommentAdd")
	public ModelAndView ownerCommentAdd(@ModelAttribute("ReviewDTO") ReviewDTO review, HttpSession session) {
		ModelAndView mav= new ModelAndView();
        int cnt=service.ownerCommentAdd(review);
        if(cnt>0){// 수정성공
        	StoreDTO store=service.storeInfoEdit((Integer)session.getAttribute("storeSeq"));
        	int reservationNoCheck=service.reservationNoCheck(store.getSeq());
		    int noShowCheckNum=service.noShowCheckNum(store.getSeq());
		    mav.addObject("reservationNoCheck", reservationNoCheck);
		    mav.addObject("noShowCheckNum", noShowCheckNum);
			mav.setViewName("ownerpage/ownerMyPage");
		}else{// 수정실패시 -> 이전페이지 (알림)
			mav.addObject("msg","댓글달기 실패!!");
			mav.setViewName("register/failResult");
		}
		return mav;
	}
	@PostMapping("reservationCheckOk")
	public ModelAndView reservationCheckOk(@ModelAttribute("ReservationDTO") ReservationDTO reservation, HttpSession session) {
		ModelAndView mav= new ModelAndView();
        int cnt=service.reservationCheck(reservation);
        if(cnt>0){// 예약확인 완료
        	StoreDTO store=service.storeInfoEdit((Integer)session.getAttribute("storeSeq"));
		    int reservationNoCheck=service.reservationNoCheck(store.getSeq());
		    mav.addObject("reservationNoCheck", reservationNoCheck);
			mav.setViewName("ownerpage/ownerMyPage");
		}else{// 수정실패시 -> 이전페이지 (알림)
			mav.addObject("msg","예약확인 실패!!");
			mav.setViewName("register/failResult");
		}
		return mav;
	}
	@GetMapping("reviewContent")
	public ModelAndView reviewContent(Integer no) {
		ModelAndView mav= new ModelAndView();
		ReviewDTO review=service.reviewSelectDetail(no);
		mav.addObject("reviewDetail", review);
		mav.setViewName("ownerpage/reviewContent");
		return mav;
	}
	@GetMapping("reservationDetail")
	public ModelAndView reservationDetail(Integer no) {
		ModelAndView mav= new ModelAndView();
		ReservationDTO reservation=service.reservationSelectDetail(no);
		mav.addObject("reservationDetail", reservation);
		mav.setViewName("ownerpage/reservationDetail");
		return mav;
	}
	@PostMapping("showCheckOk")
	public ModelAndView showCheckOk(@ModelAttribute("ReservationDTO") ReservationDTO reservation, HttpSession session) {
		ModelAndView mav= new ModelAndView();
        int cnt=service.showCheck(reservation);
        int balloon=service.balloonNowNumber(reservation.getUser_id());
        int new_balloon=balloon+8;
        int cnt2=service.balloonGive(reservation.getUser_id(), new_balloon);
        if(cnt2>0 && cnt>0){// 예약확인 완료
        	StoreDTO store=service.storeInfoEdit((Integer)session.getAttribute("storeSeq"));
        	int reservationNoCheck=service.reservationNoCheck(store.getSeq());
		    int noShowCheckNum=service.noShowCheckNum(store.getSeq());
		    mav.addObject("reservationNoCheck", reservationNoCheck);
		    mav.addObject("noShowCheckNum", noShowCheckNum);
			mav.setViewName("ownerpage/ownerMyPage");
		}else{// 수정실패시 -> 이전페이지 (알림)
			mav.addObject("msg","방문확인 실패!!");
			mav.setViewName("register/failResult");
		}
		return mav;
	}
}