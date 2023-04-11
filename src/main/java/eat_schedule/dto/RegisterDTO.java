package eat_schedule.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RegisterDTO {
	private String user_name;
	private String user_id;
    private String user_password;
    private String gender; 
    private String nickname;
    private boolean is_owner;
    private String phone_number;
    private String address;
    private String email;
    private String joindate;
}
