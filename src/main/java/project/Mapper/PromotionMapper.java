package project.Mapper;

import project.dto.Store;

import java.util.ArrayList;


public interface PromotionMapper {
    ArrayList<Store> findAll(String district, String today);
}
