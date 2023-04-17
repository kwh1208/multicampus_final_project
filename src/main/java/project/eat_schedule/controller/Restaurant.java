package project.eat_schedule.controller;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import project.eat_schedule.AppConfig;
import project.eat_schedule.Mapper.MenuMapper;
import project.eat_schedule.dto.Menu;
import project.eat_schedule.dto.Reservation;
import project.eat_schedule.dto.Store;
import project.eat_schedule.dto.User;
import project.eat_schedule.service.FindStore;
import project.eat_schedule.service.UpdateReservation;

import java.util.List;

@RequestMapping("/store")
@Controller
public class Restaurant {

    @GetMapping("/{seq}")
    public String showRestaurant(@PathVariable int seq,
                                 Model model){
        ApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);
        FindStore findStore = ac.getBean(FindStore.class);
        Store store = findStore.findStoreBySeq(seq);
        List<Menu> menu = ac.getBean(MenuMapper.class).findMenu(seq);
        model.addAttribute("menu", menu);

        model.addAttribute("store", store);


        return "/search/store";
    }

    //예약임.
    @PostMapping("/{seq}")
    public String showRestaurant(@PathVariable String seq,
                                 @SessionAttribute("user") User user,
                                 @RequestParam("reservation") Reservation reservation,
                                 Model model){
        ApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);
        UpdateReservation updateReservation  = ac.getBean(UpdateReservation.class);
        if(user.getUser_name().isEmpty()){
            return "WEB-INF/views/register/loginForm";//로그인페이지 url
        }
        //인원수, user, 기타등등 받아서 update 하고 마이페이지로 넘어감.
        model.addAttribute("reservation", reservation);

        updateReservation.updateReservation(reservation);

        return "redirect:/store/"+seq;
    }
}
