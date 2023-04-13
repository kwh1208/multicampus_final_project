package project.service;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Service;
import project.AppConfig;
import project.Mapper.PromotionMapper;
import project.dto.Store;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;


@Service
public class FindPromotion {
    ApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);

    public ArrayList<Store> findPromotionList(String district){
        LocalDate now = LocalDate.now();

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-mm-dd");

        String today = now.format(formatter);

        PromotionMapper promotionMapper = ac.getBean(PromotionMapper.class);

        return promotionMapper.findAll(district, today);
    }
}
