package project.eat_schedule.Mapper;

import project.eat_schedule.dto.Review;

import java.util.List;

public interface ReviewMapper {
    List<Review> findAllReviewByStore(int seq);

    Double findAvgScore(int seq);
}
