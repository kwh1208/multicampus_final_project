package eat_schedule.mapper;

import eat_schedule.dto.Menu;

import java.util.List;

public interface MenuMapper {
    List<Menu> findMenu(int seq);

    List<String> findMenuType(int seq);
}
