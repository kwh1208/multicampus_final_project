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
	private String score;
	private String review;
	private String review_time;
	private String owner_comment;
	private String comment_time;
	private String store_name;
	private String file_location;
	private int coupon_status;

	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	public int getStore_seq() {
		return store_seq;
	}
	public String getScore() {
		return score;
	}
	public String getReview() {
		return review;
	}
	public String getFile_location() {
		return file_location;
	}
	public void setFile_location(String file_location) {
		this.file_location = file_location;
	}

}
