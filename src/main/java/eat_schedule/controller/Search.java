package eat_schedule.controller;

import eat_schedule.mapper.CommonMapper;
import eat_schedule.dto.Store;
import eat_schedule.mapper.RegionMapper;
import eat_schedule.service.FindPromotion;
import eat_schedule.service.FindStore;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttribute;

import java.util.ArrayList;


@Controller
@RequestMapping
@RequiredArgsConstructor
public class Search {
    private final FindPromotion findPromotion;

    private final FindStore findStore;

    private final CommonMapper CommonMapper;

    private final RegionMapper regionMapper;

    @GetMapping("search")
    public String searchDistrict(@RequestParam(value = "district") String district,
                                 @RequestParam(value = "category", required = false) String category,
                                 @RequestParam(value = "sort", required = false) String sort,
                                 Model model){
        model.addAttribute("district", district);

        model.addAttribute("promotionList", findPromotion(district));

        model.addAttribute("region", CommonMapper.findArea());

        model.addAttribute("AllCategory", CommonMapper.findCategory());

        model.addAttribute("area", regionMapper.findRegion());


        if(sort==null){
            model.addAttribute("storeList", sortStore(district, category, "score"));
            model.addAttribute("sort", "score");
        }
        else {
            model.addAttribute("storeList", sortStore(district, category, sort));
            model.addAttribute("sort", sort);
        }

        return "find-location";
    }
    ArrayList<Store> findPromotion(String district){
        return findPromotion.findPromotionList(district);
    }
    ArrayList<Store> findAllStore(String district, String category, String sort){
        return findStore.findAllStore(district, category, sort);
    }

    ArrayList<Store> sortStore(String district, String category, String sort){

        ArrayList<Store> store = findAllStore(district, category, sort);
        System.out.println(store.get(0).getScore());
        return store;
    }
}