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
@RequestMapping()
@RequiredArgsConstructor
public class Search {
    private final FindPromotion findPromotion;

    private final FindStore findStore;

    private final CommonMapper CommonMapper;

    private final RegionMapper regionMapper;

    @GetMapping("/search")
    public String searchDistrict(@RequestParam(value = "district") String district,
                                 @RequestParam(value = "category", required = false) String category,
                                 @RequestParam(value = "sort", required = false) String sort,
                                 @SessionAttribute(value = "logId", required = false) String user_id,
                                 Model model){
        model.addAttribute("district", district);

        model.addAttribute("promotionList", findPromotion(district));

        model.addAttribute("region", CommonMapper.findArea());

        model.addAttribute("AllCategory", CommonMapper.findCategory());

        model.addAttribute("area", regionMapper.findRegion());

        model.addAttribute("stores", findAllStore(district, category, sort));

        return "thymeleaf/find-location";
    }
    ArrayList<Store> findPromotion(String district){
        return findPromotion.findPromotionList(district);
    }

    ArrayList<Store> findAllStore(String district, String category, String sort){
        if(sort==null||sort.equals("score")){
            return findStore.findAllScore(district, category);
        }
        else if (sort.equals("wish")){
            return findStore.findAllWish(district, category);
        }
        else {
            return findStore.findAllScore(district, category);
        }
    }
}