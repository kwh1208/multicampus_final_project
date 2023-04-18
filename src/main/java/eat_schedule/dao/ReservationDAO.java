package eat_schedule.dao;

import java.util.List;

import eat_schedule.dto.ReservationDTO;

public interface ReservationDAO {
	//예약내역확인
	public List<ReservationDTO> ReservationSelect(String user_id);
}
