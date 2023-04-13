package project.service;


import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Service;
import project.AppConfig;
import project.Mapper.ReservationMapper;
import project.dto.Reservation;

@Service
public class UpdateReservation {
    ApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);
    ReservationMapper reservationMapper = ac.getBean(ReservationMapper.class);
    public void updateReservation(Reservation reservation){
        reservationMapper.insertReservation(reservation);
        reservationMapper.updateReservation(reservation);
        //트랜잭션 커밋
    }

}
