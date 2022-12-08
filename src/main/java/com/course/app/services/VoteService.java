package com.course.app.services;

import com.course.app.dao.api.IVotesDAO;
import com.course.app.dto.VoteDTO;
import com.course.app.services.api.IVoteService;

public class VoteService implements IVoteService {

	private final IVotesDAO dao;

	public VoteService (IVotesDAO dao) {
		this.dao = dao;
	}

	@Override
	public void save(Vote vote) {
		dao.save(vote);
	}

	@Override
	public Vote validate(VoteDTO dto) {
		return null;
	}

	@Override
	public void sort(Result result) {

	}

	public IVotesDAO getDao() {
		return dao;
	}
}
