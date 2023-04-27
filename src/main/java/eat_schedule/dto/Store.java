package eat_schedule.dto;

import eat_schedule.mapper.CommonMapper;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@Getter
@Setter
public class Store {
    private Integer seq;

    private String category;

    private Double score;

    private Integer review;

    private Integer wish;

    private String store_name;

    private String tel_number;

    private String location;

    private String comment;

    private String open_time;

    private String close_time;

    private String how_to_come;

    private boolean parking;

    private boolean wifi;

    private boolean animal;

    private boolean group_customer;

    private boolean playroom;

    private boolean disabled;


    public void setLocation(String location) {
        this.location = "\'"+location+"\'";
    }

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
