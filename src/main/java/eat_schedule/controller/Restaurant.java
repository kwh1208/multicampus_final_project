package eat_schedule.controller;

import eat_schedule.dao.UserDAO;
import eat_schedule.dao.WishDAO;
import eat_schedule.dto.*;
import eat_schedule.mapper.*;
import eat_schedule.service.EmailService;
import eat_schedule.service.FindStore;
import eat_schedule.service.UpdateReservation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/restaurant")
@Controller
@Slf4j
@RequiredArgsConstructor
public class Restaurant {

    private final FindStore findStore;
    private final MenuMapper menuMapper;
    private final ReviewMapper reviewMapper;
    private final UpdateReservation updateReservation;
    private final ReservationMapper reservationMapper;
    private final CouponMapper couponMapper;
    private final ImgMapper imgMapper;
    private final EmailService emailService;
    private final UserDAO userMapper;
    private final WishDAO wishMapper;
    private final Reservation reservation;

    private final Mail mail;



    @GetMapping("/{seq}")
    public String showRestaurant(@PathVariable int seq,
                                 @SessionAttribute(value = "logId", required = false) String user_id,
                                 Model model){

        Store store = findStore.findStoreBySeq(seq);

        StringBuilder facility = facility(store);


        model.addAttribute("facility", facility);

        model.addAttribute("reviews", reviewMapper.findAllReviewByStore(seq));

        model.addAttribute("avgScore", reviewMapper.findAvgScore(seq));

        model.addAttribute("menus", menuMapper.findMenu(seq));

        model.addAttribute("type", menuMapper.findMenuType(seq));

        model.addAttribute("store", store);

        model.addAttribute("left", reservationMapper.findReservationLeft(seq));

        model.addAttribute("img", imgMapper.findImg(seq));

        List<WishDTO> wish = wishMapper.WishSelectSpecific(user_id, seq);
        int wishChk;
        if(wish.size()==0){
            wishChk=0;
        }
        else {
            wishChk=1;
        }

        model.addAttribute("wish", wishChk);

        model.addAttribute("coupon", couponMapper.findCoupon(user_id, seq));

        return "thymeleaf/store";
    }

    //예약임.
    @PostMapping("/{seq}")
    public String showRestaurant(@PathVariable int seq,
                                 @SessionAttribute(value = "logStatus", required = false) String login,
                                 @RequestParam("date") String date,
                                 @RequestParam("time") String time,
                                 Model model) throws Exception {
        if(!login.equals("Y")){
            return "redirect:/register/loginForm";//로그인페이지 url
        }
        //인원수, user, 기타등등 받아서 update 하고 마이페이지로 넘어감.
        reservation.setReservation_time(date+time);

        model.addAttribute("reservation", reservation);

        updateReservation.updateReservation(reservation);

        mailSendToUser(reservation);

        mailSendToOwner(reservation);

        return "redirect:/user/user/myReservation";
    }

    private void mailSendToUser(Reservation reservation) {
        UserDTO user = userMapper.UserSelect(reservation.getUser_id());

        Store store = findStore.findStoreBySeq(reservation.getStore_seq());

        mail.setTitle(user.getUser_name()+"님 "+store.getStore_name()+"의 예약이 완료되었습니다.");
        mail.setAddress(user.getEmail());
        mail.setMessage(user.getUser_name()+"님\n"+ reservation.getReservation_time()+" "+store.getStore_name()+"의 예약이 완료되었습니다.\n"+"확인하러 가기 "+"localhost:8080/user/mypage/reservation");
        emailService.sendEmail(mail);
    }
    private void mailSendToOwner(Reservation reservation) {
        String ownerId = findStore.findStoreBySeq(reservation.getStore_seq()).getOwner_id();
        UserDTO owner = userMapper.UserSelect(ownerId);
        Store store = findStore.findStoreBySeq(reservation.getStore_seq());

        mail.setTitle(owner.getUser_name()+"님 "+store.getStore_name()+"에 새로운 예약이 들어왔습니다.");
        mail.setAddress(owner.getEmail());
        mail.setMessage(owner.getUser_name()+"님\n"+ reservation.getReservation_time()+" "+store.getStore_name()+"의 예약이 들어왔습니다.\n"+"확인하러 가기 "+"localhost:8080/user/mypage/reservation");
        emailService.sendEmail(mail);
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
