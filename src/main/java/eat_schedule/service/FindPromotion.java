package eat_schedule.service;

import eat_schedule.mapper.PromotionMapper;
import eat_schedule.dto.Store;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;


@Service
@RequiredArgsConstructor
public class FindPromotion {

    private final PromotionMapper promotionMapper;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    LocalDate now = LocalDate.now();

    public ArrayList<Store> findPromotionList(String district){
        return promotionMapper.findAll(district, now);
    }
}