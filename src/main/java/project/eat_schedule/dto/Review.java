package project.eat_schedule.dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

@Component
@Getter
@Setter
public class Review {
    String user_id;
    Integer store_seq;

    String owner_id;

    Integer score;

    String review;

    String review_time;

    String owner_comment;

    String comment_time;

    String picture_location;


}
