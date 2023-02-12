package com.course.app.services.factories;

import com.course.app.dao.provider.ChoiceDaoProvider;
import com.course.app.services.ArtistService;
import com.course.app.services.api.IArtistService;

public class ArtistServiceSingleton {
	private volatile static IArtistService instance;

	private ArtistServiceSingleton(){}

	public static IArtistService getInstance() {
		if (instance == null) {
			synchronized (ArtistServiceSingleton.class) {
				if (instance == null) {
					instance = new ArtistService(ChoiceDaoProvider.getInstance().artistsDao());
				}
			}
		}
		return instance;
	}
}
