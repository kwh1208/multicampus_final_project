package eat_schedule.service;

import eat_schedule.dto.ReservationDTO;

public interface ReservationService {
	public ReservationDTO ReservationSelect(String user_id);
}
