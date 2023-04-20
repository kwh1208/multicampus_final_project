package eat_schedule.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import eat_schedule.dao.ReservationDAO;
import eat_schedule.dto.ReservationDTO;

@Service
public class ReservationServiceImpl implements ReservationService {
	
	@Autowired
	ReservationDAO dao;
	
	@Override
	public List<ReservationDTO> ReservationSelect(String user_id) {
		return dao.ReservationSelect(user_id);
	}
	
	@Override
	public ReservationDTO ReviewWrite(String user_id) {
		return dao.ReviewWrite(user_id);
	}
}
