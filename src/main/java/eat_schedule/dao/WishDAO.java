package eat_schedule.dao;

import java.util.List;

import eat_schedule.dto.WishDTO;


public interface WishDAO {
	// 위시리스트
	public List<WishDTO> WishSelect(String user_id);
}
