package eat_schedule.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import eat_schedule.dto.StoreDTO;
import eat_schedule.dto.RegisterDTO;
import eat_schedule.mapper.OwnerDAO;

@Service
public class OwnerSeriviceImpl implements OwnerService {
	@Autowired
	OwnerDAO dao;
	@Override
	public int storeRegisterOk(StoreDTO store) {
		return dao.storeRegisterOk(store);
	}
	@Override
	public StoreDTO storeInfoEdit(String owner_id) {
		return dao.storeInfoEdit(owner_id);
	}
	@Override
	public int storeInfoEditOk(StoreDTO store) {
		return dao.storeInfoEditOk(store);
	}
	@Override
	public StoreDTO storeSelect(String ownerId) {
		return dao.storeSelect(ownerId);
	}
	@Override
	public RegisterDTO userInfoEdit(String ownerId) {
		return dao.userInfoEdit(ownerId);
	}
	@Override
	public int userInfoEditOk(RegisterDTO user) {
		return dao.userInfoEditOk(user);
	}
}
