package eat_schedule.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class StoreDTO {
	
	private int seq;
	private String picture_location;
	private String owner_id;
	private String store_name;
	private String location;
	private String tel_number;
	private String owner_comment;
	private String open_time;
	private String close_time;
	private String how_to_come;
	private int parking;
	private int wifi;
	private int animal;
	private int group_customer;
	private int playroom;
	private int disabled;
	
}
