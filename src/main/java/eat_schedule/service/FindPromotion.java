package eat_schedule.service;

import eat_schedule.mapper.PromotionMapper;
import eat_schedule.dto.Store;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;


@Service
@RequiredArgsConstructor
public class FindPromotion {

    private final PromotionMapper promotionMapper;

    public ArrayList<Store> findPromotionList(String district){
        LocalDate now = LocalDate.now();

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-mm-dd");

        String today = now.format(formatter);

        return promotionMapper.findAll(district, today);
    }
}