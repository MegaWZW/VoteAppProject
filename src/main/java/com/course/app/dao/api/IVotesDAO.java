package com.course.app.dao.api;

import com.course.app.services.Vote;

import java.util.List;

public interface IVotesDAO {
	List<Vote> getData();
	void save(Vote vote);
}
