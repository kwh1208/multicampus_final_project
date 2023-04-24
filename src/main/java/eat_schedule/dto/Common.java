package eat_schedule.dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

@Component
@Getter
@Setter
public class Common {
    private String[] area = {"강남역", "가로수길"};

    private String[] category = {"한식", "일식"};
}
