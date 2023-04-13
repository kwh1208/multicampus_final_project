package project.Mapper;

import project.dto.Reservation;


public interface ReservationMapper {
    int insertReservation(Reservation reservation);

    int updateReservation(Reservation reservation);
}
