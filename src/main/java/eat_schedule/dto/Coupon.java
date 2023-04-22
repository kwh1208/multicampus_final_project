package eat_schedule.dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
@Getter
@Setter
public class Coupon {
    String user_id;
    LocalDateTime expired_period;
    int discount_rate;
    int store_seq;
}
