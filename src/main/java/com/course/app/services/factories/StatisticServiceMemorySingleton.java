package com.course.app.services.factories;

import com.course.app.dao.factories.ArtistsDAOMemorySingleton;
import com.course.app.dao.factories.GenresDAOMemorySingleton;
import com.course.app.services.StatisticService;
import com.course.app.services.api.IStatisticService;

public class StatisticServiceMemorySingleton {
	private volatile static IStatisticService instance;

	private StatisticServiceMemorySingleton(){};

	public static IStatisticService getInstance() {
		if(instance == null) {
			synchronized (StatisticServiceMemorySingleton.class) {
				if (instance == null) {
					instance = new StatisticService(VoteServiceMemorySingleton.getInstance(),
							ArtistsDAOMemorySingleton.getInstance(),
							GenresDAOMemorySingleton.getInstance());
				}
			}
		}
		return instance;
	}
}
