package eat_schedule.mapper;

import eat_schedule.dto.Store;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
@Mapper
@Component
public interface StoreMapper {
    ArrayList<Store> findAllScore(String district, String category);

    ArrayList<Store> findAllReview(String district, String category);

    ArrayList<Store> findAllWish(String district, String category);

    Store findStore(int seq);
}
