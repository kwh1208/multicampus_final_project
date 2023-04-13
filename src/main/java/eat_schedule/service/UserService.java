package eat_schedule.service;

import eat_schedule.dto.UserDTO;

public interface UserService {
	public UserDTO UserEdit(String user_id);
	public int UserEditOk(UserDTO dto);
}
