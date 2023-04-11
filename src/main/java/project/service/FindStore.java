package project.service;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Service;
import project.AppConfig;
import project.Mapper.StoreMapper;
import project.dto.Store;
import java.util.ArrayList;

@Service
public class FindStore {
    ApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);

    public ArrayList<Store> findStore(String district){

        StoreMapper StoreMapper = ac.getBean(StoreMapper.class);

        return StoreMapper.findAll(district);
    }
}
