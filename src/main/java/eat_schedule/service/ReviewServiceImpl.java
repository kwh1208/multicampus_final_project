package eat_schedule.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import eat_schedule.dao.ReviewDAO;
import eat_schedule.dto.ReviewDTO;

@Service
public class ReviewServiceImpl implements ReviewService {
	
	@Autowired
	ReviewDAO dao;
	
	@Override
	public List<ReviewDTO> ReviewSelect(String user_id) {
		return dao.ReviewSelect(user_id);
	}
	
	@Override
	public int ReviewInsert(ReviewDTO dto) {
		return dao.ReviewInsert(dto);
	}

	public ReviewDTO ReviewEdit(int no) {
		return dao.ReviewEdit(no);
	}
	
	public int ReviewEditOk(ReviewDTO dto) {
		return dao.ReviewEditOk(dto);
	}

}
