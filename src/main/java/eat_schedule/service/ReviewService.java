package eat_schedule.service;

import eat_schedule.dto.ReviewDTO;

public interface ReviewService {
	public ReviewDTO ReviewSelect(String user_id);
}
