package eat_schedule.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDTO {

	private String user_id;
	private String user_password;
	private String user_name;
	private String nickname;
	private int isowner;
	private String phone_number;
	private String address;
	private int gender;
	private String email;
	private int last_month_balloon;
	private int now_balloon;
	private String grade;
	private String join_date;


	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}


	public Object getUser_id() {
		// TODO Auto-generated method stub
		return null;
	}


	public Object getIs_owner() {
		// TODO Auto-generated method stub
		return null;
	}


	
}
