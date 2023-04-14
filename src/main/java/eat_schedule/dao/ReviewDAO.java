package eat_schedule.dao;

import eat_schedule.dto.ReviewDTO;

public interface ReviewDAO {
	//리뷰 확인
	public ReviewDTO ReviewSelect(String user_id);
}
