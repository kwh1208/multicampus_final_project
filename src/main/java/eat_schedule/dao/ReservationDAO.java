package eat_schedule.dao;

import java.util.List;

import eat_schedule.dto.ReservationDTO;

public interface ReservationDAO {
	// 예약 내역 확인
	public List<ReservationDTO> ReservationSelect(String user_id);
	// 리뷰 쓸때 가게 불러오기
	public ReservationDTO ReviewWrite(String user_id);
}
