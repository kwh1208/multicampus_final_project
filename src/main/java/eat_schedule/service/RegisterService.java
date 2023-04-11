package eat_schedule.service;

import eat_schedule.dto.RegisterDTO;

public interface RegisterService {
	public RegisterDTO loginOk(String user_id, String user_password);
}
