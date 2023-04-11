package project.controller;



import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import project.AppConfig;
import project.dto.Store;
import project.service.FindPromotion;
import project.service.FindStore;

import java.util.ArrayList;


@Controller
@RequestMapping
public class search {
    ApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);
    @GetMapping("search")
    public String searchDistrict(@RequestParam("district") String district,
                         Model model){

        model.addAttribute("promotionList", findPromotion(district));

        model.addAttribute("storeList", findStore(district));

        return "search/find-location";
    }
    ArrayList<Store> findPromotion(String district){
        return ac.getBean(FindPromotion.class).findPromotionList(district);
    }
    ArrayList<Store> findStore(String district){
        return ac.getBean(FindPromotion.class).findPromotionList(district);
    }
}