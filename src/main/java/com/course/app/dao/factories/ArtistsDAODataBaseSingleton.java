package com.course.app.dao.factories;

import com.course.app.dao.ArtistsDataBaseDAO;
import com.course.app.dao.api.IArtistsDAO;

public class ArtistsDAODataBaseSingleton {

	private volatile static IArtistsDAO instance;

	private ArtistsDAODataBaseSingleton(){};

	public static IArtistsDAO getInstance() {
		if(instance == null) {
			synchronized (ArtistsDAODataBaseSingleton.class) {
				if (instance == null) {
					instance = new ArtistsDataBaseDAO();
				}
			}
		}
		return instance;
	}
}
