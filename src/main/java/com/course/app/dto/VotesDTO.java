package com.course.app.dto;

import java.util.ArrayList;
import java.util.List;

public class VotesDTO {
	private List<VoteDTO> votes;

	public VotesDTO(List<VoteDTO> votes) {
		this.votes = votes;
	}

	public VotesDTO() {
		votes = new ArrayList<>();
	}

	public List<VoteDTO> getAll() {
		return votes;
	}

	public void add(VoteDTO vote) {
		votes.add(vote);
	}
}
