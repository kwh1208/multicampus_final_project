package project.eat_schedule.Mapper;

import project.eat_schedule.dto.Store;

import java.util.ArrayList;


public interface PromotionMapper {
    ArrayList<Store> findAll(String district, String today);
}
