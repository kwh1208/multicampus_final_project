package project.eat_schedule.dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

@Component
@Getter
@Setter
public class Store {
    Integer seq;

    String category;

    Integer score;

    Integer review;

    Integer wish;

    String store_name;

    String tel_number;

    String location;

    String comment;

    String open_time;

    String close_time;

    String how_to_come;

    boolean parking;

    boolean wifi;

    boolean animal;

    boolean group_customer;

    boolean playroom;

    boolean disabled;
    public int compareScore(Store o) {
        if(this.score.equals(o.score))
        return 0;
        else if (this.score < o.score) {
            return -1;
        }
        else return 1;
    }
    public int compareReview(Store o) {
        if(this.review.equals(o.review))
            return 0;
        else if (this.review < o.review) {
            return -1;
        }
        else return 1;
    }

    public int compareWish(Store o) {
        if(this.wish.equals(o.wish))
            return 0;
        else if (this.wish < o.wish) {
            return -1;
        }
        else return 1;
    }
}
