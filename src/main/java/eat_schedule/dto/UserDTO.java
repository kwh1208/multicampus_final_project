package eat_schedule.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class UserDTO {
	private String name;
	private String id;
    private String password;
    private String gender; 
    private Integer age;
    private String phone_Number;
    private String address;
    private String email;
    private String joindate;
}

