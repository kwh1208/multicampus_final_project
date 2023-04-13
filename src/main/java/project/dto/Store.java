package project.dto;

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
