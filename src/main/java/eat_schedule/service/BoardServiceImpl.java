package eat_schedule.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import eat_schedule.dao.BoardDAO;
import eat_schedule.dto.BoardDTO;

@Service
public class BoardServiceImpl implements BoardService {

	@Autowired
	BoardDAO dao;
	
	@Override
	public int inquiryInsert(BoardDTO dto) {
		return dao.inquiryInsert(dto);
	}


}
