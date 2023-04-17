package project.eat_schedule.Mapper;

import project.eat_schedule.dto.Reservation;


public interface ReservationMapper {
    int insertReservation(Reservation reservation);

    int updateReservation(Reservation reservation);
}
