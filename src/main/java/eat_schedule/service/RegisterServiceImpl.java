package eat_schedule.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import eat_schedule.dao.RegisterDAO;
import eat_schedule.dto.RegisterDTO;

@Service
public class RegisterServiceImpl implements RegisterService {

	@Autowired
	RegisterDAO dao;
	
	@Override
	public RegisterDTO loginOk(String user_id, String user_password) {
		return dao.loginOk(user_id, user_password);
	}

}
