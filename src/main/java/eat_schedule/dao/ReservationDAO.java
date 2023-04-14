package eat_schedule.dao;

import eat_schedule.dto.ReservationDTO;

public interface ReservationDAO {
	//예약내역확인
	public ReservationDTO ReservationSelect(String user_id);
}
