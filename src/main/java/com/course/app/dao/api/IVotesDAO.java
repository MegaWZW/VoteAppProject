package com.course.app.dao.api;

import com.course.app.dto.VoteDTO;

import java.util.List;

public interface IVotesDAO {
	List<VoteDTO> getAll();
	void save(VoteDTO vote);
}
