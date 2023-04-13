package project.controller;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import project.AppConfig;
import project.dto.Reservation;
import project.dto.Store;
import project.dto.User;
import project.service.FindStore;
import project.service.UpdateReservation;

@RequestMapping("/store")
@Controller
public class Restaurant {
    ApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);
    FindStore findStore = ac.getBean(FindStore.class);


    UpdateReservation updateReservation  = ac.getBean(UpdateReservation.class);
    @GetMapping("/{seq}")
    public String showRestaurant(@PathVariable int seq,
                                 Model model){
        Store store = findStore.findStoreBySeq(seq);

        model.addAttribute("store", store);

        return "/search/store";
    }

    //예약임.
    @PostMapping("/{seq}")
    public String showRestaurant(@PathVariable String seq,
                                 @SessionAttribute("user") User user,
                                 @RequestParam("reservation") Reservation reservation,
                                 Model model){
        if(user.getUser_name().isEmpty()){
            return "WEB-INF/views/register/loginForm";//로그인페이지 url
        }
        //인원수, user, 기타등등 받아서 update 하고 마이페이지로 넘어감.
        model.addAttribute("reservation", reservation);

        updateReservation.updateReservation(reservation);

        //사장님한테 알림보내기

        return "redirect:/store/"+seq;
    }
}
