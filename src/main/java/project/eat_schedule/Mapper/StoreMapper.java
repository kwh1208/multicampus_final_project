package project.eat_schedule.Mapper;

import project.eat_schedule.dto.Store;

import java.util.ArrayList;

public interface StoreMapper {
    ArrayList<Store> findAll(String district, String category);

    Store findStore(int seq);
}
