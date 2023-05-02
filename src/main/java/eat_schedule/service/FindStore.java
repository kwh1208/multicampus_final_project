package eat_schedule.service;

import eat_schedule.mapper.StoreMapper;
import eat_schedule.dto.Store;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
@RequiredArgsConstructor
public class FindStore {
    private final StoreMapper storeMapper;

    public Store findStoreBySeq(int seq){
        return storeMapper.findStore(seq);
    }
    public ArrayList<Store> findAllScore(String district, String category){
        return storeMapper.findAllScore(district, category);
    }
    public ArrayList<Store> findAllReview(String district, String category){
        return storeMapper.findAllReview(district, category);
    }
    public ArrayList<Store> findAllWish(String district, String category){
        return storeMapper.findAllWish(district, category);
    }
}
