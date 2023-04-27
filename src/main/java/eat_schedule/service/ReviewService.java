package eat_schedule.service;

import java.util.List;

import eat_schedule.dto.ReviewDTO;
import eat_schedule.dto.ReviewFileDTO;

public interface ReviewService {
	public List<ReviewDTO> ReviewSelect(String user_id);
	public int ReviewInsert(ReviewDTO dto);
	public ReviewDTO ReviewEdit(int no);
	public int ReviewEditOk(ReviewDTO dto);
	public int ReviewDelete(int no);
	
	public int saveData(ReviewFileDTO dto);
	public List<ReviewFileDTO> ReviewFileSelect();
}
