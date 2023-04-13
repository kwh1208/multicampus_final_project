package project.Mapper;

import project.dto.Reservation;


public interface ReservationMapper {
    public int insertReservation(Reservation reservation);

    public int updateReservation(Reservation reservation);
}
