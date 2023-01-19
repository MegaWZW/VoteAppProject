package com.course.app.services.api;


import com.course.app.core.Result;
import com.course.app.core.Vote;
import com.course.app.dao.api.IVotesDAO;
import com.course.app.dto.VoteDTO;

public interface IVoteService {
	void save(VoteDTO dto);

	Vote validate(VoteDTO dto);

	void sort(Result result);
	IVotesDAO getDao();
}
