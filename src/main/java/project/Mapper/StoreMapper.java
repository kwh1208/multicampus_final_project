package project.Mapper;

import project.dto.Store;

import java.util.ArrayList;

public interface StoreMapper {
    ArrayList<Store> findAll(String district, String category);

    Store findStore(int seq);
}
