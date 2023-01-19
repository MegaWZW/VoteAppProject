package com.course.app.dao.api;

import com.course.app.core.Vote;

import java.util.List;

public interface IVotesDAO {
	List<Vote> getAll();
	void save(Vote vote);
}
