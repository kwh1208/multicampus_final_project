package eat_schedule.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import eat_schedule.dao.CouponDAO;
import eat_schedule.dto.CouponDTO;

@Service
public class CouponServiceImpl implements CouponService {
	
	@Autowired
	CouponDAO dao;
	
	public List<CouponDTO> CouponSelect(String user_id) {
		return dao.CouponSelect(user_id);
	}
	
	public int CouponCount(String user_id) {
		return dao.CouponCount(user_id);
	}
}
