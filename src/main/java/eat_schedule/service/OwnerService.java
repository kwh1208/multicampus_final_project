package eat_schedule.service;

import eat_schedule.dto.StoreDTO;

public interface OwnerService {
	//가게선택
	public StoreDTO storeSelect(String ownerId);
	//가게등록
	public int storeRegisterOk(StoreDTO store);
	//가게정보수정시 원래 데이터 받아오기
	public StoreDTO storeInfoEdit(String owner_id);
	//가게정보수정
	public int storeInfoEditOk(StoreDTO store);	
}
