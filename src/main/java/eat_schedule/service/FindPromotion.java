package eat_schedule.service;

import eat_schedule.AppConfig;
import eat_schedule.dto.Store;
import eat_schedule.mapper.PromotionMapper;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;


@Service
public class FindPromotion {

    public ArrayList<Store> findPromotionList(String district){
        ApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);
        LocalDate now = LocalDate.now();

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-mm-dd");

        String today = now.format(formatter);

        PromotionMapper promotionMapper = ac.getBean(PromotionMapper.class);

        return promotionMapper.findAll(district, today);
    }
}