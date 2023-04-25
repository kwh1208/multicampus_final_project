package eat_schedule.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ReviewDTO {
	private int seq;
	private int store_seq;
	private String user_id;
	private String owner_id;
	private String score;	//라디오버튼 스트링으로 받아야함
	private String review;
	private String review_time;
	private String owner_comment;
	private String comment_time;
	
	private String store_name;

	public void setUser_id(String attribute) {
		// TODO Auto-generated method stub
		
	}
}
