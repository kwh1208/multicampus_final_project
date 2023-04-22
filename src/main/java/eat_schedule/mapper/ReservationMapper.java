package eat_schedule.mapper;

import eat_schedule.dto.Reservation;
import eat_schedule.dto.ReservationLeft;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
@Mapper
@Component
public interface ReservationMapper {
    int insertReservation(Reservation reservation);

    int updateReservation(Reservation reservation);

    List<ReservationLeft> findReservationLeft(int seq);
}
