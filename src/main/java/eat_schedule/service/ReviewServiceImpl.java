package eat_schedule.service;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import eat_schedule.dao.ReviewDAO;
import eat_schedule.dto.ReviewDTO;
import eat_schedule.dto.ReviewFileDTO;
import eat_schedule.dto.WishDTO;

@Service
public class ReviewServiceImpl implements ReviewService {
	
	@Autowired
	ReviewDAO dao;
	
	@Override
	public List<ReviewDTO> ReviewSelect(String user_id) {
		return dao.ReviewSelect(user_id);
	}
	@Override
	public int ReviewInsert(HashMap<String, Object> map) {
		return dao.ReviewInsert(map);
	}
	@Override
	public ReviewDTO ReviewEdit(int no) {
		return dao.ReviewEdit(no);
	}
	@Override
	public int ReviewEditUpdate(ReviewDTO dto) {
		return dao.ReviewEditUpdate(dto);
	}
	@Override
	public int ReviewDelete(int no) {
		return dao.ReviewDelete(no);
	}	
	@Override
	public Double AvgScore(int no) {
		return dao.AvgScore(no);
	}
	@Override
	public int AvgScoreUpdate(WishDTO wdto) {
		return dao.AvgScoreUpdate(wdto);
	}
}

