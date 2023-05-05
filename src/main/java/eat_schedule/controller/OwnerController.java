package eat_schedule.controller;

import java.util.List;
import java.util.Map;
import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
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

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import com.siot.IamportRestClient.IamportClient;
import com.siot.IamportRestClient.response.IamportResponse;
import com.siot.IamportRestClient.response.Payment;

import eat_schedule.dto.StoreDTO;
import eat_schedule.dto.BalloonDTO;
import eat_schedule.dto.CouponDTO;
import eat_schedule.dto.FilenameDTO;
import eat_schedule.dto.MenuDTO;
import eat_schedule.dto.PromotionListDTO;
import eat_schedule.dto.RegisterDTO;
import eat_schedule.dto.ReservationDTO;
import eat_schedule.dto.ReviewDTO;
import eat_schedule.service.OwnerService;

@Controller
@RequestMapping("/ownerpage")
public class OwnerController {
	@Autowired
	OwnerService service;
	
	// 가게 파일 삭제
	public void deleteDirectory(File directory) {
	    if (directory.exists()) {
	        File[] files = directory.listFiles();
	        if (files != null) {
	            for (File file : files) {
	                if (file.isDirectory()) {
	                    deleteDirectory(file); // 재귀 호출로 하위 폴더도 삭제
	                } else {
	                    file.delete(); // 파일 삭제
	                }
	            }
	        }
	        directory.delete(); // 폴더 삭제
	    }
	}
	
	@GetMapping("ownerStart")
	public String ownerStart(Model model, HttpSession session) {
		List<StoreDTO> store=service.storeSelect((String)session.getAttribute("logId"));
		for (StoreDTO s : store) {
		    FilenameDTO file=service.storePicture(s.getSeq());
		    if (file != null) {
	            s.setPicture_location(file.getFilename());
	        } else {
	            s.setPicture_location("/imgbin/balloons.png");
	        }
		}
		model.addAttribute("store", store);
		return "ownerpage/ownerStart";
	}
	
	@GetMapping("ownerMyPage")
	public String ownerPage(Integer no, Model model, HttpSession session) {
		//사장님 마이페이지 가게 선택시
		List<StoreDTO> storeList=service.storeSelect((String)session.getAttribute("logId"));
		model.addAttribute("store", storeList);
		StoreDTO store=service.storeInfoEdit(no);
		session.setAttribute("storeSeq", store.getSeq());
		session.setAttribute("storeName", store.getStore_name());
		session.setAttribute("storeStatus", "Y");
		int reservationNoCheck=service.reservationNoCheck(store.getSeq());
		int noShowCheckNum=service.noShowCheckNum(store.getSeq());
		model.addAttribute("reservationNoCheck", reservationNoCheck);
		model.addAttribute("noShowCheckNum", noShowCheckNum);
		return "ownerpage/ownerMyPage";
	}
	@GetMapping("reservation")
	public String reservation(Model model, HttpSession session, String date) {
		//사장님 마이페이지 중 예약확인페이지
		if(date!=null) {
			StoreDTO store=service.storeInfoEdit((Integer)session.getAttribute("storeSeq"));
			List<ReservationDTO> reservation=service.reservationDateSelect((Integer)store.getSeq(),date);
			model.addAttribute("reservation", reservation);
			return "ownerpage/reservation";
		}else {
			StoreDTO store=service.storeInfoEdit((Integer)session.getAttribute("storeSeq"));
			List<ReservationDTO> reservation=service.reservationSelect((Integer)store.getSeq());
			model.addAttribute("reservation", reservation);
			return "ownerpage/reservation";
		}
		
	}
	@GetMapping("storeRegister")
	public String storeRegister() {
		//사장님 마이페이지 중 가게등록 페이지
		return "ownerpage/storeRegister";
	}
	@GetMapping("menuRegister")
	public String menuRegister() {
		//사장님 마이페이지 중 메뉴등록 페이지		
		return "ownerpage/menuRegister";
	}
	@GetMapping("menuSelect")
	public String menuSelect(HttpSession session, Model model) {
		//사장님 마이페이지 중 가게등록 페이지
		List<MenuDTO> menuList=service.menuLoad((Integer)session.getAttribute("storeSeq"));
		model.addAttribute("menuList", menuList);
		return "ownerpage/menuSelect";
	}
	@GetMapping("menuEdit")
	public String menuEdit(Integer no, HttpSession session, Model model) {
		//사장님 마이페이지 중 가게등록 페이지
		MenuDTO menu=service.menuInfo(no);
		model.addAttribute("menu", menu);
		return "ownerpage/menuEdit";
	}
	@PostMapping("menuDelete")
	public ModelAndView menuDelete(@ModelAttribute("MenuDTO") MenuDTO menu, HttpSession session) {
		ModelAndView mav= new ModelAndView();
		MenuDTO menu2=service.menuInfo(menu.getSeq());
		String picture_location=menu2.getPicture_location();
		File picture=new File(picture_location);
	    if(picture.delete()) {
	    	System.out.println("삭제완료");
	    }else {
	    	System.out.println("삭제미완료");
	    }
		int result=service.menuDelete(menu.getSeq());
		if(result>0) {
			List<MenuDTO> menuList=service.menuLoad((Integer)session.getAttribute("storeSeq"));
			mav.addObject("menuList", menuList);
			mav.setViewName("ownerpage/menuSelect");
		}else {
			mav.addObject("msg","가게등록실패!!");
			mav.setViewName("ownerpage/failResult");
		}
		return mav;
	}
	
	@PostMapping("storeRegisterOk")
	public ModelAndView storeRegisterOk(HttpServletRequest req,@ModelAttribute("StoreDTO") StoreDTO store, HttpSession session){
		int result=service.storeRegisterOk(store);
		
		//request: 폼의 데이터들과 첨부파일이 있다.
		
		//MultiPartHttpServletRequest <- request이용하여 구한다.
		
		//1. 파일업로드
		MultipartHttpServletRequest mr=(MultipartHttpServletRequest)req;
		
		//2. mr에서 MultipartFile 객체를 얻어오기 (업로드한 파일의 수만큼 있다.)
		List<MultipartFile> files= mr.getFiles("filename");
		
		//3. 파일을 서버에 업로드할 위치의 절대주소가 필요하다.
		String folderName="/storeuploadfile/store"+store.getSeq()+"/storepicture"; //여기서 알아서 경로 바꾸면 됨
		String path="home/ec2-user/test"+folderName; //"home/ec2-user/test"+folderName
		
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
		}else{//if 업로드 파일이 없을때
			
		}//----------------------------------------------------------------
		
		ModelAndView mav=new ModelAndView();
		for(FilenameDTO i : fileList) { //for문을 통한 전체출력
			i.setStore_seq(store.getSeq());
		    i.setFilename(path+"/"+i.getFilename());
		    int result2=service.pictureInsert(i);
		    if(result2>0) {		    	
		    }
		    else {
				mav.addObject("msg","가게등록실패!!");
				mav.setViewName("ownerpage/failResult");
		    }
		}

		if(result>0) {//가게등록 성공
			List<StoreDTO> store1=service.storeSelect((String)session.getAttribute("logId"));
			mav.addObject("store", store1);
			mav.setViewName("ownerpage/ownerStart");
		}else {//가게등록 실패
			mav.addObject("msg","가게등록실패!!");
			mav.setViewName("ownerpage/failResult");
		}		
		return mav;
	}
	
	@PostMapping("storeInfoEditOk")
	public ModelAndView storeInfoEditOk(HttpServletRequest req, @ModelAttribute("StoreDTO") StoreDTO store, HttpSession session){
		store.setSeq((Integer)session.getAttribute("storeSeq"));
		int result=service.storeInfoEditOk(store);
		//request: 폼의 데이터들과 첨부파일이 있다.
		
		//MultiPartHttpServletRequest <- request이용하여 구한다.
		
		//1. 파일업로드
		MultipartHttpServletRequest mr=(MultipartHttpServletRequest)req;
		
		//2. mr에서 MultipartFile 객체를 얻어오기 (업로드한 파일의 수만큼 있다.)
		List<MultipartFile> files= mr.getFiles("filename");
		
		//3. 파일을 서버에 업로드할 위치의 절대주소가 필요하다.
		String folderName="/storeuploadfile/store"+store.getSeq()+"/storepicture"; //여기서 알아서 경로 바꾸면 됨
		String path="home/ec2-user/test"+folderName; //"home/ec2-user/test"+folderName
		
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
		}else{//if 업로드 파일이 없을때
			
		}//----------------------------------------------------------------
		
		ModelAndView mav=new ModelAndView();
		for(FilenameDTO i : fileList) { //for문을 통한 전체출력
			i.setStore_seq(store.getSeq());
		    i.setFilename(path+"/"+i.getFilename());
		    int result2=service.pictureInsert(i);
		    if(result2>0) {		    	
		    }
		    else {
				mav.addObject("msg","가게등록실패!!");
				mav.setViewName("ownerpage/failResult");
		    }
		}
		if(result>0) {//가게정보수정 성공
			List<StoreDTO> storeList=service.storeSelect((String)session.getAttribute("logId"));
			mav.addObject("store", storeList);
			mav.addObject("reservationNoCheck", service.reservationNoCheck(store.getSeq())); // 모델에 reservationNoCheck 추가
			mav.addObject("noShowCheckNum", service.noShowCheckNum(store.getSeq()));
			mav.setViewName("ownerpage/ownerMyPage");
		}else {//가게등록 실패
		mav.addObject("msg","가게수정실패!!");
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
		String path="home/ec2-user/test"+folderName; //"home/ec2-user/test"+folderName
		
		Path directoryPath = Paths.get(path);
		
		try {
            // 디렉토리 생성
            Files.createDirectories(directoryPath);
 
 
        }catch (IOException e) {
            e.printStackTrace();
        }
		
		//-------업로드 시작 -> 같은 파일이 존재할 때 파일명을 만들어 주어야 한다. ------
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
		String fileName = "";
		if (!fileList.isEmpty()) {
		    fileName = fileList.get(0).getFilename();
		    menu.setPicture_location(path+"/"+fileName);
		}
		menu.setStore_seq((Integer)session.getAttribute("storeSeq"));
		int result=service.menuInsert(menu);
		
		ModelAndView mav= new ModelAndView();
		if(result>0) {
			StoreDTO store=service.storeInfoEdit((Integer)session.getAttribute("storeSeq"));
			List<StoreDTO> storeList=service.storeSelect((String)session.getAttribute("logId"));
			mav.addObject("store", storeList);
			int reservationNoCheck=service.reservationNoCheck(store.getSeq());
		    int noShowCheckNum=service.noShowCheckNum(store.getSeq());
		    mav.addObject("reservationNoCheck", reservationNoCheck);
		    mav.addObject("noShowCheckNum", noShowCheckNum);
			mav.setViewName("ownerpage/ownerMyPage");
		}else {
			mav.addObject("msg","가게등록실패!!");
			mav.setViewName("ownerpage/failResult");
		}
		
		return mav;
	}
	
	@PostMapping("menuEditOk")
	public ModelAndView menuEditOk(HttpServletRequest req,@ModelAttribute("MenuDTO") MenuDTO menu, HttpSession session){
		//request: 폼의 데이터들과 첨부파일이 있다.
		String before_filename=req.getParameter("before_filename");
		System.out.println(before_filename);
		//MultiPartHttpServletRequest <- request이용하여 구한다.
		
		//1. 파일업로드
		MultipartHttpServletRequest mr=(MultipartHttpServletRequest)req;
		
		if(mr.getFiles("filename")!=null) {
			
			
			//2. mr에서 MultipartFile 객체를 얻어오기 (업로드한 파일의 수만큼 있다.)
			List<MultipartFile> files= mr.getFiles("filename");
			//3. 파일을 서버에 업로드할 위치의 절대주소가 필요하다.
			String folderName="/storeuploadfile/store"+(Integer)session.getAttribute("storeSeq")+"/menupicture";
			String path="home/ec2-user/test"+folderName; //"home/ec2-user/test"+folderName
			
			Path directoryPath = Paths.get(path);
			
			try {
	            // 디렉토리 생성
	            Files.createDirectories(directoryPath);
	 
	 
	        }catch (IOException e) {
	            e.printStackTrace();
	        }
			
			//-------업로드 시작 -> 같은 파일이 존재할 때 파일명을 만들어 주어야 한다. ------
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
			String fileName = "";
			if (!fileList.isEmpty()) {
			    fileName = fileList.get(0).getFilename();
			    try {
					File before_picture=new File(before_filename);
					boolean result2=before_picture.delete();
					if(result2) {
						System.out.println("삭제완료");
					}else {
						System.out.println("삭제안됨");
					}
				}
				catch(Exception e) {
					System.out.println("삭제안됨");
				}
			    menu.setPicture_location(path+"/"+fileName);
			}else {
				menu.setPicture_location(before_filename);
			}
		}
		int result=service.menuEditOk(menu);
		
		ModelAndView mav= new ModelAndView();
		if(result>0) {
			StoreDTO store=service.storeInfoEdit((Integer)session.getAttribute("storeSeq"));
			List<StoreDTO> storeList=service.storeSelect((String)session.getAttribute("logId"));
			mav.addObject("store", storeList);
			int reservationNoCheck=service.reservationNoCheck(store.getSeq());
		    int noShowCheckNum=service.noShowCheckNum(store.getSeq());
		    mav.addObject("reservationNoCheck", reservationNoCheck);
		    mav.addObject("noShowCheckNum", noShowCheckNum);
			mav.setViewName("ownerpage/ownerMyPage");
		}else {
			mav.addObject("msg","가게등록실패!!");
			mav.setViewName("ownerpage/failResult");
		}
		
		return mav;
	}
	
	@GetMapping("storeInfoEdit")
	public String storeInfoEdit(Model model,HttpSession session) {
		StoreDTO store=service.storeInfoEdit((Integer)session.getAttribute("storeSeq"));		
		model.addAttribute("store",store);
		List<FilenameDTO> fileList=service.fileList((Integer)session.getAttribute("storeSeq"));
		for (FilenameDTO file : fileList) {
			String string = file.getFilename();
			String[] parts = string.split("/");
			String filename = parts[parts.length - 1];
		    file.setFilename(filename);
		}
		model.addAttribute("fileList", fileList);
		return "ownerpage/storeInfoEdit";
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
			List<StoreDTO> storeList=service.storeSelect((String)session.getAttribute("logId"));
			mav.addObject("store", storeList);
		    mav.addObject("reservationNoCheck", reservationNoCheck);
		    mav.addObject("noShowCheckNum", noShowCheckNum);
			mav.setViewName("ownerpage/ownerMyPage");
		}else{// 수정실패시 -> 이전페이지 (알림)
			mav.addObject("msg","회원정보수정 실패!!");
			mav.setViewName("ownerpage/failResult");
		}
		return mav;
	}
	@GetMapping("commentManager")
	public String commentmanager(Model model, HttpSession session, String searchKey, String use) {
		StoreDTO store=service.storeInfoEdit((Integer)session.getAttribute("storeSeq"));
		Double storeScore=service.storeScore((Integer)session.getAttribute("storeSeq"));
		model.addAttribute("storeScore", storeScore);
		
		if(searchKey!=null && use!=null) {
			if(searchKey.equals("oc")) {
				List<ReviewDTO> review=service.reviewOwnerCommentSelect((Integer)store.getSeq(), 1);
				model.addAttribute("review", review);
			}else {
				List<ReviewDTO> review=service.reviewCouponSelect((Integer)store.getSeq(), 0);
				model.addAttribute("review", review);
			}
		}else {
		List<ReviewDTO> review=service.reviewSelect((Integer)store.getSeq());
		model.addAttribute("review", review);
		}
		return "ownerpage/commentManager";
	}
	@GetMapping("advApply")
	public String advapply(HttpSession session, Model model) {
		StoreDTO store=service.storeInfoEdit((Integer)session.getAttribute("storeSeq"));
		model.addAttribute("store", store);
		RegisterDTO user=service.userInfoEdit((String)session.getAttribute("logId"));
		model.addAttribute("user", user);
		return "ownerpage/advApply";
	}
	@GetMapping("advList")
	public String advList(HttpSession session, Model model) {
		StoreDTO store=service.storeInfoEdit((Integer)session.getAttribute("storeSeq"));
		model.addAttribute("store", store);
		RegisterDTO user=service.userInfoEdit((String)session.getAttribute("logId"));
		model.addAttribute("user", user);
		List<PromotionListDTO> promotion=service.advList((Integer)session.getAttribute("storeSeq"));
		model.addAttribute("promotion", promotion);
		return "ownerpage/advList";
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
			List<StoreDTO> storeList=service.storeSelect((String)session.getAttribute("logId"));
			mav.addObject("store", storeList);
			mav.setViewName("ownerpage/ownerMyPage");
		}else{// 수정실패시 -> 이전페이지 (알림)
			mav.addObject("msg","쿠폰주기 실패!!");
			mav.setViewName("ownerpage/failResult");
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
			List<StoreDTO> storeList=service.storeSelect((String)session.getAttribute("logId"));
			mav.addObject("store", storeList);
		    mav.addObject("reservationNoCheck", reservationNoCheck);
		    mav.addObject("noShowCheckNum", noShowCheckNum);
			mav.setViewName("ownerpage/ownerMyPage");
		}else{// 수정실패시 -> 이전페이지 (알림)
			mav.addObject("msg","댓글달기 실패!!");
			mav.setViewName("ownerpage/failResult");
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
		    int noShowCheckNum=service.noShowCheckNum(store.getSeq());
			List<StoreDTO> storeList=service.storeSelect((String)session.getAttribute("logId"));
			mav.addObject("store", storeList);
		    mav.addObject("reservationNoCheck", reservationNoCheck);
		    mav.addObject("noShowCheckNum", noShowCheckNum);
			mav.setViewName("ownerpage/ownerMyPage");
		}else{// 수정실패시 -> 이전페이지 (알림)
			mav.addObject("msg","예약확인 실패!!");
			mav.setViewName("ownerpage/failResult");
		}
		return mav;
	}
	
	@PostMapping("reservationFailOk")
	public ModelAndView reservationFailOk(@ModelAttribute("ReservationDTO") ReservationDTO reservation, HttpSession session) {
		ModelAndView mav= new ModelAndView();
        int cnt=service.reservationCheck(reservation);
        if(cnt>0){// 예약거절(취소) 완료
        	StoreDTO store=service.storeInfoEdit((Integer)session.getAttribute("storeSeq"));
		    int reservationNoCheck=service.reservationNoCheck(store.getSeq());
			List<StoreDTO> storeList=service.storeSelect((String)session.getAttribute("logId"));
			mav.addObject("store", storeList);
		    mav.addObject("reservationNoCheck", reservationNoCheck);
			mav.setViewName("ownerpage/ownerMyPage");
		}else{// 수정실패시 -> 이전페이지 (알림)
			mav.addObject("msg","예약확인 실패!!");
			mav.setViewName("ownerpage/failResult");
		}
		return mav;
	}
	
	@GetMapping("storeDelete")
	public String alertMessage(Model model) {
		model.addAttribute("msg", "정말삭제하시겠습니까?");
		return "ownerpage/storeDelete";
	}
	
	@GetMapping("storeDeleteOk")
	public ModelAndView storeDeleteOk(Model model, HttpSession session) {
		ModelAndView mav=new ModelAndView();
		Integer store_seq=(Integer)session.getAttribute("storeSeq");
		String folder_path="home/ec2-user/test/storeuploadfile/store"+store_seq; //"home/ec2-user/test"+folderName
		System.out.println(folder_path);
		File storeDirectory = new File(folder_path);
		deleteDirectory(storeDirectory);
		int cnt=service.storeDelete(store_seq);
		if(cnt>0) {//가게등록 성공
			List<StoreDTO> store1=service.storeSelect((String)session.getAttribute("logId"));
			mav.addObject("store", store1);
			mav.setViewName("ownerpage/ownerStart");
		}else {//가게등록 실패
			mav.addObject("msg","가게등록실패!!");
			mav.setViewName("ownerpage/failResult");
		}
		return mav;
	}
	
	
	@PostMapping("showCheckOk")
	public ModelAndView showCheckOk(@ModelAttribute("ReservationDTO") ReservationDTO reservation, HttpSession session) {
		ModelAndView mav= new ModelAndView();
		BalloonDTO bDTO = new BalloonDTO();
		StoreDTO store=service.storeInfoEdit((Integer)session.getAttribute("storeSeq"));
        int cnt=service.showCheck(reservation);
        int balloon=service.balloonNowNumber(reservation.getUser_id());
        int new_balloon=balloon+8;
        bDTO.setUser_id(reservation.getUser_id());
        bDTO.setContent(store.getStore_name()+" 방문 확인");      
        int cnt2=service.balloonGive(reservation.getUser_id(), new_balloon);
        int cnt3=service.balloonListUpdate(bDTO);
        if(cnt2>0 && cnt>0 && cnt3>0){// 확인 완료
        	int reservationNoCheck=service.reservationNoCheck(store.getSeq());
		    int noShowCheckNum=service.noShowCheckNum(store.getSeq());
			List<StoreDTO> storeList=service.storeSelect((String)session.getAttribute("logId"));
			mav.addObject("store", storeList);
		    mav.addObject("reservationNoCheck", reservationNoCheck);
		    mav.addObject("noShowCheckNum", noShowCheckNum);
			mav.setViewName("ownerpage/ownerMyPage");
		}else{// 수정실패시 -> 이전페이지 (알림)
			mav.addObject("msg","방문확인 실패!!");
			mav.setViewName("ownerpage/failResult");
		}
		return mav;
	}
	@PostMapping("noShowCheckOk")
	public ModelAndView noShowCheckOk(@ModelAttribute("ReservationDTO") ReservationDTO reservation, HttpSession session) {
		ModelAndView mav= new ModelAndView();
        int cnt=service.showCheck(reservation);
        if(cnt>0){// 노쇼확인 완료
        	StoreDTO store=service.storeInfoEdit((Integer)session.getAttribute("storeSeq"));
        	int reservationNoCheck=service.reservationNoCheck(store.getSeq());
		    int noShowCheckNum=service.noShowCheckNum(store.getSeq());
			List<StoreDTO> storeList=service.storeSelect((String)session.getAttribute("logId"));
			mav.addObject("store", storeList);
		    mav.addObject("reservationNoCheck", reservationNoCheck);
		    mav.addObject("noShowCheckNum", noShowCheckNum);
			mav.setViewName("ownerpage/ownerMyPage");
		}else{// 수정실패시 -> 이전페이지 (알림)
			mav.addObject("msg","방문확인 실패!!");
			mav.setViewName("ownerpage/failResult");
		}
		return mav;
	}
	

	@PostMapping("/payment/callback_receive")
	public ResponseEntity<?> callback_recieve(@RequestBody Map<String, Object> model, HttpSession session){
		
		PromotionListDTO promotion = new PromotionListDTO();
		String process_result="결제 성공!";
		//응답 header 생성
		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.add("Content-Type", "application/json; charset=UTF-8");
		JSONObject responseObj=new JSONObject();
		
		try {
			String imp_uid = (String)model.get("imp_uid");
			String merchant_uid = (String)model.get("merchant_uid");
			boolean success = (Boolean)model.get("success");
			String error_msg = (String)model.get("error_msg");
			promotion.setStore_seq((Integer)session.getAttribute("storeSeq"));
			promotion.setOwner_id((String)session.getAttribute("logId"));
			promotion.setDistrict((String)model.get("district"));
			promotion.setList_date((String)model.get("list_date"));
			
			System.out.println("---callback receive---");
			System.out.println("----------------------");
			System.out.println("imp_uid : " + imp_uid);
			System.out.println("merchant_uid : " + merchant_uid);
			System.out.println("success : " + success);
			
			System.out.println("---insert promotion---");
			System.out.println("----------------------");
			System.out.println("store_seq : " + promotion.getStore_seq());
			System.out.println("owner_id : " + promotion.getOwner_id());
			System.out.println("district : " + promotion.getDistrict());
			System.out.println("list_date : " + promotion.getList_date());
			
			if(success == true) {
				
				//db select ( select amount from oder_table where merchant_uid = ?)
				
				//step5
				String api_key ="0768415534736602";
				String api_secret ="qwDxOfUXWEt5zkWOFM2xm8rtTq8DTnhuB2jrFlfxEdwzozXOUlWSvblcmC3pHOnZq48OPRyyVYTycFtU";
				
				IamportClient ic=new IamportClient(api_key, api_secret);
				IamportResponse<Payment> response = ic.paymentByImpUid(imp_uid);
				
				BigDecimal iamport_amount = response.getResponse().getAmount();
				//compare db_amount and api_amount
				//if(db_amount==api_amount)
				
				responseObj.put("process_result", "결제성공");
				//db save (Update oder_table set pay_result = 'success', imp_uid=? where merchant_uid=?)
				//responseObj.put("process_result", "결제위변조");
				//else{ result = "fail"; cancel API}
				
				int cnt=service.promotionInsert(promotion);
			}else {
				System.out.println("error_msg : "+ error_msg);
				responseObj.put("process_result", "결제실패 : " + error_msg);
			}			
		}catch(Exception e) {
			e.printStackTrace();
			responseObj.put("process_result", "결제실패 : 관리자에게 문의해 주세요.");
		}
		return new ResponseEntity<String>(responseObj.toString(), responseHeaders, HttpStatus.OK);
	}
	
	@PostMapping("storePictureDel")
	@ResponseBody
	public String storePictureDel(@RequestParam("fileSeq") String fileSeq, HttpSession session) {
	    // 파일 db 삭제
	    Integer seq=Integer.parseInt(fileSeq);
	    FilenameDTO file=service.selectPicture(seq);
	    String path=file.getFilename(); //"home/ec2-user/test"+folderName
	    int result=service.deletePicture(seq);
	    // 파일 삭제 로직 구현하기
	    if(result>0) {
		    File picture=new File(path);
		    if(picture.delete()) {
		    	return "success";
		    }else {
		    	return "fail";
		    }
	    }else {
	    	return "fail";
	    }
	}
}
