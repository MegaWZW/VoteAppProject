package com.course.app.services.factories;

import com.course.app.dao.factories.ArtistsDAOMemorySingleton;
import com.course.app.services.ArtistService;

public class ArtistServiceMemorySingleton {
	private volatile static ArtistService instance;

	private ArtistServiceMemorySingleton(){};

	public static ArtistService getInstance() {
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
