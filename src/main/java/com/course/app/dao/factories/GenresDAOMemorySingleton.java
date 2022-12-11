package com.course.app.dao.factories;

import com.course.app.dao.GenresDAO;
import com.course.app.dao.api.IGenresDAO;

import java.util.Arrays;
import java.util.List;

/**
 * Класс для получения уникального объекта GenresDAO
 * Реализован с помощью паттерна синглтон
 */
public class GenresDAOMemorySingleton {
	private volatile static IGenresDAO instance;
	private static List<String> genres = Arrays.asList(
			"Pop",
			"Hip hop",
			"Rock",
			"Rhythm and blues",
			"Soul",
			"Reggae",
			"Country",
			"Funk",
			"Folk",
			"Jazz");

	private GenresDAOMemorySingleton(){};

	public static IGenresDAO getInstance() {
		if(instance == null) {
			synchronized (GenresDAOMemorySingleton.class) {
				if(instance == null){
					instance = new GenresDAO(genres);
				}
			}
		}
		return instance;
	}
}
