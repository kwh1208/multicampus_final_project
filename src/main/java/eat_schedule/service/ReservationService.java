package eat_schedule.service;

import eat_schedule.dto.ReservationDTO;

public interface ReservationService {
	public ReservationDTO reservationInfo(String user_id);
}
