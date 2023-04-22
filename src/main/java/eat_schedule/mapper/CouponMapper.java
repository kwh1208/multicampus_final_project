package eat_schedule.mapper;

import eat_schedule.dto.Coupon;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
@Component
public interface CouponMapper {
    public List<Coupon> findCoupon(String user_id);
}
