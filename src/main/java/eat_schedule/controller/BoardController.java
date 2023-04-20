package eat_schedule.controller;


import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import eat_schedule.dto.BoardDTO;
import eat_schedule.dto.FilenameDTO;
import eat_schedule.service.BoardService;

@Controller
@RequestMapping("/board")
public class BoardController {
	@Autowired
	BoardService service;
	
	@GetMapping("FAQ")
	public String faq() {
		return "board/FAQ";
	}
	
	@GetMapping("inquiry")
	public String inquiry(HttpSession session) {
		
		Object id = session.getAttribute("logId");
		String user_id = (String)id;
		
		if(user_id=="" || user_id==null) {
			return "redirect:/register/loginForm";
		}else {
			return "board/inquiry";
		}

		
	}
	@PostMapping("inquiryWriteOk")
	public ModelAndView inquiryWriteOk(HttpServletRequest req, BoardDTO dto) {
		dto.setUser_id((String)req.getSession().getAttribute("logId"));
		ModelAndView mav = new ModelAndView();
		
		MultipartHttpServletRequest mr = (MultipartHttpServletRequest)req;
		
		List<MultipartFile> files = mr.getFiles("filename");
		
		String path = req.getSession().getServletContext().getRealPath("/uploadfile");
		//System.out.println("path: " + path); 
		
		List<FilenameDTO> fileList = new ArrayList<FilenameDTO>(); // 업로드한 파일명들을 담을 컬렉션
		
		if(files!=null){// 업로드 파일이 있을때
			for(int i=0; i<files.size(); i++) {
				MultipartFile mf = files.get(i);
				
				String orgFilename = mf.getOriginalFilename(); // 원래의 파일명
				if(orgFilename!=null && !orgFilename.equals("")) {// rename
					
					File f = new File(path, orgFilename);
					
					if(f.exists()) {
						for(int renameNum=1; ; renameNum++) {
							
							int point = orgFilename.lastIndexOf("."); // 마지막 .의 위치
							String orgFile = orgFilename.substring(0, point); // 확장자를 뺀 파일명
							String orgExt = orgFilename.substring(point+1); // 확장자
						
							String newFilename = orgFile+" ("+renameNum+")."+orgExt;
							f = new File(path, newFilename);
							if(!f.exists()) {
								orgFilename = newFilename;
								break;
							}
						}
						
					}
					try {
						mf.transferTo(new File(path, orgFilename));
					}catch(Exception e) {}
					
					FilenameDTO dfDTO = new FilenameDTO();
					dfDTO.setFilename(orgFilename);
					fileList.add(dfDTO);
					
				}
			}
		}
		
		
		try {
			
			int result = service.inquiryInsert(dto);
			
			mav.setViewName("redirect:/"); // 마이페이지로 수정필요!!!!!!!!!!!!!!!!!!!!!!!!
		}catch(Exception e) {
			e.printStackTrace();
		}
		return mav;
		
	}
}

