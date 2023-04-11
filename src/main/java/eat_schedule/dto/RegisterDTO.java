package eat_schedule.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RegisterDTO {
	private String name;
	private String id;
    private String password;
    private String gender; 
    private int age;
    private String phone_Number;
    private String address;
    private String email;
    private String joindate;
}
