package eat_schedule.dao;

import java.util.List;

import eat_schedule.dto.CouponDTO;

public interface CouponDAO {
	//  쿠폰 내역 확인
	 public List<CouponDTO> CouponSelect(String user_id);
}
