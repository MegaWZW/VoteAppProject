package com.course.app.services.api;


import com.course.app.dto.VoteDTO;
import com.course.app.services.Result;
import com.course.app.services.Vote;

public interface IVoteService {

	void save(Vote vote);
	Vote validate(VoteDTO dto);
	void sort(Result result);

}
