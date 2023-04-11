package project.Mapper;

import project.dto.Store;

import java.util.ArrayList;

public interface StoreMapper {
    public ArrayList<Store> findAll(String district);
}
