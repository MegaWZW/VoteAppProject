package com.course.app.services.factories;

import com.course.app.services.StatisticService;
import com.course.app.services.api.IStatisticService;

public class StatisticServiceSingleton {
	private volatile static IStatisticService instance;

	private StatisticServiceSingleton(){}

	public static IStatisticService getInstance() {
		if(instance == null) {
			synchronized (StatisticServiceSingleton.class) {
				if (instance == null) {
					instance = new StatisticService(VoteServiceSingleton.getInstance(),
							ArtistServiceSingleton.getInstance(),
							GenreServiceSingleton.getInstance());
				}
			}
		}
		return instance;
	}
}
