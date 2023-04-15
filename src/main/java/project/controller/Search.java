package project.controller;



import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import project.AppConfig;
import project.dto.Common;
import project.dto.Store;
import project.service.FindPromotion;
import project.service.FindStore;

import java.util.ArrayList;

import static project.dto.Common.area;


@Controller
@RequestMapping
public class Search {

    @GetMapping("search")
    public String searchDistrict(@RequestParam("district") String district,
                                 @RequestParam("category") String category,
                                 @RequestParam("sort") String sort,
                         Model model){
        model.addAttribute("district", district);

        model.addAttribute("category", category);

        model.addAttribute("promotionList", findPromotion(district));

        model.addAttribute("area", area);
        model.addAttribute("AllCategory", Common.category);


        if(sort.isEmpty()){
            model.addAttribute("sort", "score");
            model.addAttribute("storeList", sortStore(district, category, "score"));
        }
        else {
            model.addAttribute("sort", sort);
            model.addAttribute("storeList", sortStore(district, category, sort));
        }

        return "search/find-location";
    }
    ArrayList<Store> findPromotion(String district){
        ApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);
        return ac.getBean(FindPromotion.class).findPromotionList(district);
    }
    ArrayList<Store> findAllStore(String district, String category){
        ApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);
        return ac.getBean(FindStore.class).findAllStore(district, category);
    }

    ArrayList<Store> sortStore(String district, String category, String sort){
        ArrayList<Store> store = findAllStore(district, category);
        if(sort.equals("score")){
            store.sort(Store::compareScore);
        } else if (sort.equals("review")) {
            store.sort(Store::compareReview);
        } else if (sort.equals("wish")) {
            store.sort(Store::compareWish);
        }
        return store;
    }
}