package eat_schedule.mapper;

import eat_schedule.dto.Store;

import java.util.ArrayList;

public interface StoreMapper {
    ArrayList<Store> findAll(String district, String category);

    Store findStore(int seq);
}
