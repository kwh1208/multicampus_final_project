package eat_schedule.service;

import eat_schedule.AppConfig;
import eat_schedule.dto.Store;
import eat_schedule.mapper.StoreMapper;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class FindStore {


    public ArrayList<Store> findAllStore(String district, String category){
        ApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);
        StoreMapper StoreMapper = ac.getBean(StoreMapper.class);

        return StoreMapper.findAll(district, category);
    }

    public Store findStoreBySeq(int seq){
        ApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);
        StoreMapper StoreMapper = ac.getBean(StoreMapper.class);
        return StoreMapper.findStore(seq);
    }
}
