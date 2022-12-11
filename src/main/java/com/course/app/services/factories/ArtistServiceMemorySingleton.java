package com.course.app.services.factories;

import com.course.app.dao.factories.ArtistsDAOMemorySingleton;
import com.course.app.services.ArtistService;
import com.course.app.services.api.IArtistService;

public class ArtistServiceMemorySingleton {
	private volatile static IArtistService instance;

	private ArtistServiceMemorySingleton(){};

	public static IArtistService getInstance() {
		if (instance == null) {
			synchronized (ArtistServiceMemorySingleton.class) {
				if (instance == null) {
					instance = new ArtistService(ArtistsDAOMemorySingleton.getInstance());
				}
			}
		}
		return instance;
	}
}
