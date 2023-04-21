package eat_schedule.service;

import eat_schedule.AppConfig;
import eat_schedule.dto.Reservation;
import eat_schedule.mapper.ReservationMapper;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Service;

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
