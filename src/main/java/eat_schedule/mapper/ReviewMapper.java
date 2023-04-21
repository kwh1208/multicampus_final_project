package eat_schedule.mapper;

import eat_schedule.dto.Review;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ReviewMapper {
    List<Review> findAllReviewByStore(int seq);

    Double findAvgScore(int seq);
}
