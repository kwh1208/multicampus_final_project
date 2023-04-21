package eat_schedule.mapper;

import eat_schedule.dto.Store;

import java.util.ArrayList;


public interface PromotionMapper {
    ArrayList<Store> findAll(String district, String today);
}
