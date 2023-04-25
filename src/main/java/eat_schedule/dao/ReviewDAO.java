package eat_schedule.dao;

import java.util.List;

import eat_schedule.dto.ReviewDTO;


public interface ReviewDAO {
	// 리뷰 확인
	public List<ReviewDTO> ReviewSelect(String user_id);
	// 리뷰 작성
	public int ReviewInsert(ReviewDTO dto);
	// 리뷰 수정 폼
	public ReviewDTO ReviewEdit(int no);
	// 리뷰 수정 (DB update)
	public int ReviewEditOk(ReviewDTO dto);
	// 리뷰 삭제
	public int ReviewDelete(int no);
}
