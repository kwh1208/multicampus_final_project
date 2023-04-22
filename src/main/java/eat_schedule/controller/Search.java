package eat_schedule.controller;

import eat_schedule.dto.Store;
import eat_schedule.mapper.CommonMapper;
import eat_schedule.service.FindPromotion;
import eat_schedule.service.FindStore;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;


@Controller
@RequestMapping
public class Search {
    FindPromotion findPromotion;

    FindStore findStore;

    CommonMapper CommonMapper;

    public Search(FindPromotion findPromotion, FindStore findStore, CommonMapper CommonMapper) {
        this.findPromotion = findPromotion;
        this.findStore = findStore;
        this.CommonMapper = CommonMapper;
    }

    @GetMapping("search")
    public String searchDistrict(@RequestParam("district") String district,
                                 @RequestParam("category") String category,
                                 @RequestParam("sort") String sort,
                                 Model model){
        model.addAttribute("district", district);

        model.addAttribute("category", category);

        model.addAttribute("promotionList", findPromotion(district));

        model.addAttribute("region", CommonMapper.findArea());

        model.addAttribute("AllCategory", CommonMapper.findCategory());


        if(sort.isEmpty()){
            model.addAttribute("sort", "score");
            model.addAttribute("storeList", sortStore(district, category, "score"));
        }
        else {
            model.addAttribute("sort", sort);
            model.addAttribute("storeList", sortStore(district, category, sort));
        }

        return "find-location";
    }
    ArrayList<Store> findPromotion(String district){
        return findPromotion.findPromotionList(district);
    }
    ArrayList<Store> findAllStore(String district, String category){

        return findStore.findAllStore(district, category);
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