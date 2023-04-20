package eat_schedule.controller;



import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import eat_schedule.dto.StoreDTO;
import eat_schedule.service.StoreService;



@Controller
public class HomeController {

	@Autowired
	StoreService service;
	
	 @RequestMapping("/")
	 public String home() {

	     return "home";
	 }
	 
	 @GetMapping("/")
	 public String storeList(Model model) {
		 
		 List<StoreDTO> list = service.selectDistrict();
		 model.addAttribute("list", list);
		 
		 return "home";
	 }
	
}
