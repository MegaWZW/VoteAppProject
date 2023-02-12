package com.course.app.services.factories;

import com.course.app.dao.provider.ChoiceDaoProvider;
import com.course.app.dao.provider.api.IDaoProvider;
import com.course.app.services.VoteService;
import com.course.app.services.api.IVoteService;

public class VoteServiceSingleton {
	private volatile static IVoteService instance;

	private VoteServiceSingleton(){}

	public static IVoteService getInstance(){
		if(instance == null) {
			synchronized (VoteServiceSingleton.class) {
				if (instance == null) {
					IDaoProvider daoProvider = ChoiceDaoProvider.getInstance();
					instance = new VoteService(daoProvider.votesDao(),
							ArtistServiceSingleton.getInstance(),
							GenreServiceSingleton.getInstance());
				}
			}
		}
		return instance;
	}
}
