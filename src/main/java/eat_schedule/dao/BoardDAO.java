package eat_schedule.dao;



import org.apache.ibatis.annotations.Mapper;

import eat_schedule.dto.BoardDTO;



@Mapper
public interface BoardDAO {
	public int inquiryInsert(BoardDTO dto);
}
