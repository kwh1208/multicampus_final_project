package eat_schedule.service;

import eat_schedule.dto.Store;
import eat_schedule.mapper.StoreMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class FindStoreTest {

    @Autowired
    StoreMapper storeMapper;


    @Test
    void findStoreBySeq() {
        Store store = storeMapper.findStore(10);
        System.out.println(store);
    }
}