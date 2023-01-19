package com.course.app.dao;

import com.course.app.dao.api.IVotesDAO;
import com.course.app.core.Vote;

import java.util.List;

public class VotesMemoryDAO implements IVotesDAO {
	private List<Vote> votes;

	public VotesMemoryDAO(List<Vote> votes) {
		this.votes = votes;
	}

	@Override
	public List<Vote> getAll() {
		return votes;
	}

	@Override
	public void save(Vote vote) {
		votes.add(vote);
	}
}
