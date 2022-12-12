package com.course.app.dao.factories;

import com.course.app.core.Artist;
import com.course.app.dao.ArtistsDAO;
import com.course.app.dao.api.IArtistsDAO;

import java.util.Arrays;
import java.util.List;

/**
 * Класс для получения уникального объекта ArtistsDAO
 * Реализован с помощью паттерна синглтон
 */
public class ArtistsDAOMemorySingleton {
	private volatile static IArtistsDAO instance;
	private static List<Artist> artists = Arrays.asList(
			new Artist("Adele", 0, 11),
			 new Artist("Billie Eilish", 0, 12),
			new Artist("Taylor Swift", 0, 13),
			new Artist("Ed Sheeran", 0, 14)
			);


	private ArtistsDAOMemorySingleton(){};

	public static IArtistsDAO getInstance() {
		if(instance == null) {
			synchronized (ArtistsDAOMemorySingleton.class) {
				if (instance == null) {
					instance = new ArtistsDAO(artists);
				}
			}
		}
		return instance;
	}
}
