package eat_schedule.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import eat_schedule.dao.ReviewDAO;
import eat_schedule.dto.ReviewDTO;

@Service
public class ReviewServiceImpl implements ReviewService {
	
	@Autowired
	ReviewDAO dao;
	
	@Override
	public ReviewDTO ReviewSelect(String user_id) {
		return dao.ReviewSelect(user_id);
	}

}
