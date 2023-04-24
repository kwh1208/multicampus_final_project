package eat_schedule.service;

import eat_schedule.mapper.StoreMapper;
import eat_schedule.dto.Store;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
@RequiredArgsConstructor
public class FindStore {
    private final StoreMapper StoreMapper;

    public ArrayList<Store> findAllStore(String district, String category){

        return StoreMapper.findAll(district, category);
    }

    public Store findStoreBySeq(int seq){
        return StoreMapper.findStore(seq);
    }
}
