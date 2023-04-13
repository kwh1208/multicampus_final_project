package project.dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

@Component
@Getter
@Setter
public class User {
    private String user_id;
    private String user_password;
    private String user_name;
    private String nickname;
    private int isOwner;
    private String phone_number;
    private String address;
    private int gender;
    private String email;
    private int last_month_balloon;
    private int now_balloon;
    private String grade;
    private String join_date;

}
