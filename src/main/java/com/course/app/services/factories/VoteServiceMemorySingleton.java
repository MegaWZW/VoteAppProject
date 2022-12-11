package com.course.app.services.factories;

import com.course.app.dao.factories.VotesDAOMemorySingleton;
import com.course.app.services.VoteService;
import com.course.app.services.api.IVoteService;

public class VoteServiceMemorySingleton {
	private volatile static IVoteService instance;

	private VoteServiceMemorySingleton(){};

	public static IVoteService getInstance(){
		if(instance == null) {
			synchronized (VoteServiceMemorySingleton.class) {
				if (instance == null) {
					instance = new VoteService(VotesDAOMemorySingleton.getInstance());
				}
			}
		}
		return instance;
	}
}
