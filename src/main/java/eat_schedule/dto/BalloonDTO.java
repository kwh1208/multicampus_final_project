package eat_schedule.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BalloonDTO {
	private int seq;
	private String user_id;
	private int store_seq;
	private String use_get;
	private String use_get_time;
	private int balloon;
	private int total_balloon;		
	private String content;
	private String store_name;
}
