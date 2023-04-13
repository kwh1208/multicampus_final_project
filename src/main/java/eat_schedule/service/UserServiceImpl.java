package eat_schedule.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import eat_schedule.dao.UserDAO;
import eat_schedule.dto.UserDTO;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	UserDAO dao;
	
	@Override
	public UserDTO UserEdit(String user_id) {
		
		return dao.UserEdit(user_id);
	}
	
	@Override
	public int UserEditOk(UserDTO dto) {
		return dao.UserEditOk(dto);
	}
}
