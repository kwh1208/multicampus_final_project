package eat_schedule.mapper;

import eat_schedule.dto.Store;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Mapper
@Component
public interface PromotionMapper {
    ArrayList<Store> findAll(String district, String today);
}
