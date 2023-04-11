package eat_schedule.dao;

import org.apache.ibatis.annotations.Mapper;

import eat_schedule.dto.RegisterDTO;

@Mapper
public interface RegisterDAO {
	public RegisterDTO loginOk(String user_id, String user_password);
}
