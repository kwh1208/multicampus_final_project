package project.eat_schedule.service;


import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Service;
import project.eat_schedule.AppConfig;
import project.eat_schedule.Mapper.ReservationMapper;
import project.eat_schedule.dto.Reservation;

@Service
public class UpdateReservation {

    public void updateReservation(Reservation reservation){
        ApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);
        ReservationMapper reservationMapper = ac.getBean(ReservationMapper.class);
        reservationMapper.insertReservation(reservation);
        reservationMapper.updateReservation(reservation);
        //트랜잭션 커밋
    }

}
