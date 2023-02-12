package com.course.app.services.api;


import com.course.app.dao.api.IArtistsDAO;
import com.course.app.dao.api.IGenresDAO;
import com.course.app.dto.VoteDTO;

import java.util.List;

public interface IVoteService {
	void save(VoteDTO dto);
	void validate(VoteDTO dto);
	List<VoteDTO> getAllVotes();
}
