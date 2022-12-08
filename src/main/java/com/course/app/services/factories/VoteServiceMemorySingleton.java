package com.course.app.services.factories;

import com.course.app.dao.factories.VotesDAOMemorySingleton;
import com.course.app.services.VoteService;

public class VoteServiceMemorySingleton {
	private volatile static VoteService instance;

	private VoteServiceMemorySingleton(){};

	public static VoteService getInstance(){
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
