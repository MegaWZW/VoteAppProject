package com.course.app.dao.memory;

import com.course.app.dao.api.IVotesDAO;
import com.course.app.dto.VoteDTO;

import java.util.ArrayList;
import java.util.List;

public class VotesMemoryDAO implements IVotesDAO {
	private List<VoteDTO> votes;

	public VotesMemoryDAO() {
		votes = new ArrayList<>();
	}

	@Override
	public List<VoteDTO> getAll() {
		return votes;
	}

	@Override
	public void save(VoteDTO vote) {
		votes.add(vote);
	}
}
