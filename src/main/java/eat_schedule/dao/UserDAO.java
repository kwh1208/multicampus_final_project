package eat_schedule.dao;

import eat_schedule.dto.UserDTO;

public interface UserDAO {
	// 회원정보 수정폼
	public UserDTO UserEdit(String user_id);
	// 회원정보 수정 DB 업데이트
	public int UserEditOk(UserDTO dto);
}
