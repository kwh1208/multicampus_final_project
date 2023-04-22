package eat_schedule.service;

import eat_schedule.dto.Store;
import eat_schedule.mapper.StoreMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class FindStore {
    StoreMapper StoreMapper;

    public FindStore(StoreMapper storeMapper) {
        StoreMapper = storeMapper;
    }

    public ArrayList<Store> findAllStore(String district, String category){

        return StoreMapper.findAll(district, category);
    }

    public Store findStoreBySeq(int seq){
        return StoreMapper.findStore(seq);
    }
}
