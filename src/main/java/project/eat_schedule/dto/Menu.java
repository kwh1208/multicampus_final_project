package project.eat_schedule.dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

@Component
@Getter
@Setter
public class Menu {
    String menu_name;
    Integer price;
    String category;
}
