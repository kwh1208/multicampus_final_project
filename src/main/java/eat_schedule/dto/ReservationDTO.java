package eat_schedule.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ReservationDTO {

	private int seq;
	private int store_seq;
	private String user_id;
	private int number_of_people;
	private int coupon;
	private String reservation_time;
	private String reservation_status;
	private String reservation_comment;
	

	
}
