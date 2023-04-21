package eat_schedule.mapper;

import eat_schedule.dto.Reservation;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ReservationMapper {
    int insertReservation(Reservation reservation);

    int updateReservation(Reservation reservation);

//    AbleTime findAbleTime(int seq, String day);
}
