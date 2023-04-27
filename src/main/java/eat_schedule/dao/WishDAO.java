package eat_schedule.dao;

import java.util.List;

import org.springframework.stereotype.Component;

import eat_schedule.dto.WishDTO;

@Component
public interface WishDAO {
	// 위시리스트
	public List<WishDTO> WishSelect(String user_id);
}
