package eat_schedule.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import eat_schedule.dao.ReservationDAO;
import eat_schedule.dto.ReservationDTO;

@Service
public class ReservationServiceImpl implements ReservationService {
	
	@Autowired
	ReservationDAO dao;
	
	@Override
	public ReservationDTO reservationInfo(String user_id) {
		return dao.reservationInfo(user_id);
	}
}
