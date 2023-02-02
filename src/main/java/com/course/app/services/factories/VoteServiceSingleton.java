package com.course.app.services.factories;

import com.course.app.dao.provider.ChoiceDaoProvider;
import com.course.app.services.VoteService;
import com.course.app.services.api.IVoteService;

public class VoteServiceSingleton {
	private volatile static IVoteService instance;

	private VoteServiceSingleton(){};

	public static IVoteService getInstance(){
		if(instance == null) {
			synchronized (VoteServiceSingleton.class) {
				if (instance == null) {
					instance = new VoteService(ChoiceDaoProvider.getInstance().votesDao());
				}
			}
		}
		return instance;
	}
}
