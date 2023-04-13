package eat_schedule.dao;

import org.apache.ibatis.annotations.Mapper;

import eat_schedule.dto.RegisterDTO;

@Mapper
public interface RegisterDAO {
	public RegisterDTO loginOk(String user_id, String user_password);
	public int idCheckCount(String user_id);
	public int nicknameCheckCount(String nickname);
	public int registerInsert(RegisterDTO dto);
}
