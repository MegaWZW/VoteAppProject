package com.course.app.dao.factories;

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
	private static List<String> artists = Arrays.asList(
			"Adele",
			"Billie Eilish",
			"Taylor Swift",
			"Ed Sheeran");


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
