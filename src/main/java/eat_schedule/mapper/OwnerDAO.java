package eat_schedule.mapper;

import org.apache.ibatis.annotations.Mapper;

import eat_schedule.dto.StoreDTO;

@Mapper
public interface OwnerDAO {
	//가게선택
	public StoreDTO storeSelect(String ownerId);
	//가게등록
	public int storeRegisterOk(StoreDTO store);
	//가게정보수정시 원래 데이터 불러오기
	public StoreDTO storeInfoEdit(String ownerId);
	//가게정보수정
	public int storeInfoEditOk(StoreDTO store);
}
