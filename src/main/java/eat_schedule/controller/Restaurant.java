package eat_schedule.controller;

import eat_schedule.dto.Reservation;
import eat_schedule.dto.Store;
import eat_schedule.mapper.MenuMapper;
import eat_schedule.mapper.ReviewMapper;
import eat_schedule.service.FindStore;
import eat_schedule.service.UpdateReservation;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/restaurant")
@Controller
@RequiredArgsConstructor
public class Restaurant {
    FindStore findStore;
    MenuMapper menuMapper;
    ReviewMapper reviewMapper;
    UpdateReservation updateReservation;
    @GetMapping("/{seq}")
    public String showRestaurant(@PathVariable int seq,
                                 Model model){

        Store store = findStore.findStoreBySeq(seq);
        StringBuilder facility = facility(store);




        model.addAttribute("facility", facility);

        model.addAttribute("reviews", reviewMapper.findAllReviewByStore(seq));

        model.addAttribute("avgScore", reviewMapper.findAvgScore(seq));

        model.addAttribute("menus", menuMapper.findMenu(seq));

        model.addAttribute("type", menuMapper.findMenuType(seq));

        model.addAttribute("store", store);

        return "/search/store";
    }

    //예약임.
    @PostMapping("/{seq}")
    public String showRestaurant(@PathVariable int seq,
                                 @SessionAttribute("logStatus") boolean login,
                                 @RequestParam("reservation") Reservation reservation,
                                 Model model){
        if(!login){
            return "WEB-INF/views/register/loginForm";//로그인페이지 url
        }
        //인원수, user, 기타등등 받아서 update 하고 마이페이지로 넘어감.
        model.addAttribute("reservation", reservation);

        updateReservation.updateReservation(reservation);

        return "redirect:/store/"+seq;
    }


    private static StringBuilder facility(Store store) {
        StringBuilder sb = new StringBuilder();
        if(store.isParking()) sb.append("주차가능");
        if(store.isAnimal()) sb.append("애완동물 동반, ");
        if(store.isGroup_customer()) sb.append("단체석, ");
        if(store.isPlayroom()) sb.append("놀이방, ");
        if(store.isDisabled()) sb.append("장애인편의시설, ");
        if(store.isWifi()) sb.append("무선 인터넷");
        return sb;
    }

}
