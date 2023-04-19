package project.eat_schedule.controller;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import project.eat_schedule.AppConfig;
import project.eat_schedule.Mapper.MenuMapper;
import project.eat_schedule.Mapper.ReservationMapper;
import project.eat_schedule.Mapper.ReviewMapper;
import project.eat_schedule.dto.*;
import project.eat_schedule.service.FindStore;
import project.eat_schedule.service.UpdateReservation;

import java.util.List;

@RequestMapping("/restaurant")
@Controller
public class Restaurant {

    @GetMapping("/{seq}")
    public String showRestaurant(@PathVariable int seq,
                                 Model model){
        ApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);
        FindStore findStore = ac.getBean(FindStore.class);
        MenuMapper menuMapper = ac.getBean(MenuMapper.class);
        ReviewMapper reviewMapper = ac.getBean(ReviewMapper.class);

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
        ApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);
        UpdateReservation updateReservation  = ac.getBean(UpdateReservation.class);
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
