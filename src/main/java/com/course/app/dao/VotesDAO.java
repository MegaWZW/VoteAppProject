package com.course.app.dao;

import com.course.app.dao.api.IVotesDAO;
import com.course.app.dao.factories.VotesDAOMemorySingleton;
import com.course.app.services.Vote;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class VotesDAO implements IVotesDAO {
	private List<Vote> votes;

	public VotesDAO(List<Vote> votes) {
		this.votes = votes;
	}

	@Override
	public List<Vote> getData() {
		List<Vote> dataCopy = new ArrayList<>();
		Collections.copy(dataCopy, votes);
		return dataCopy;
	}

	@Override
	public void save(Vote vote) {
		VotesDAOMemorySingleton.getInstance().votes.add(vote);
	}
}
