package eat_schedule.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CouponDTO {
	private String user_id;
	private int store_seq;
	private String expired_period;
	private int discount_rate;
	private int seq;
	private String store_name;	
}
