package com.course.app.services.factories;

import com.course.app.dao.memory.factroies.ArtistsMemoryDAOSingleton;
import com.course.app.dao.memory.factroies.GenresMemoryDAOSingleton;
import com.course.app.dao.provider.ChoiceDaoProvider;
import com.course.app.dao.provider.api.IDaoProvider;
import com.course.app.services.StatisticService;
import com.course.app.services.api.IStatisticService;

public class StatisticServiceSingleton {
	private volatile static IStatisticService instance;

	private StatisticServiceSingleton(){};

	public static IStatisticService getInstance() {
		if(instance == null) {
			synchronized (StatisticServiceSingleton.class) {
				if (instance == null) {
					IDaoProvider daoProvider = ChoiceDaoProvider.getInstance();
					instance = new StatisticService(VoteServiceSingleton.getInstance(),
							daoProvider.artistsDao(),
							daoProvider.genresDao());
				}
			}
		}
		return instance;
	}
}
