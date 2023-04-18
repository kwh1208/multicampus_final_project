package eat_schedule.service;

import eat_schedule.dto.RegisterDTO;


public interface RegisterService {
	public RegisterDTO loginOk(String user_id, String user_password);
	public int idCheckCount(String user_id);
	public int nicknameCheckCount(String nickname);
	public int registerInsert(RegisterDTO dto);
	public int phoneCheckCount(String phone_number);
}
