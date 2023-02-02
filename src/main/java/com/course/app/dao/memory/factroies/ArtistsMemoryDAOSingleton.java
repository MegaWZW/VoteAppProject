package com.course.app.dao.memory.factroies;

import com.course.app.core.Artist;
import com.course.app.dao.memory.ArtistsMemoryDAO;
import com.course.app.dao.api.IArtistsDAO;

import java.util.Arrays;
import java.util.List;

public class ArtistsMemoryDAOSingleton {
	private volatile static IArtistsDAO instance;

	private static List<Artist> artists = Arrays.asList(
			new Artist("Adele", 0, 1),
			 new Artist("Billie Eilish", 0, 2),
			new Artist("Taylor Swift", 0, 3),
			new Artist("Ed Sheeran", 0, 4)
			);


	private ArtistsMemoryDAOSingleton(){};

	public static IArtistsDAO getInstance() {
		if(instance == null) {
			synchronized (ArtistsMemoryDAOSingleton.class) {
				if (instance == null) {
					instance = new ArtistsMemoryDAO(artists);
				}
			}
		}
		return instance;
	}
}
