package com.course.app.dao.factories;

import com.course.app.core.Genre;
import com.course.app.dao.GenresMemoryDAO;
import com.course.app.dao.api.IGenresDAO;

import java.util.Arrays;
import java.util.List;

/**
 * Класс для получения уникального объекта GenresDAO
 * Реализован с помощью паттерна синглтон
 */
public class GenresDAOMemorySingleton {
	private volatile static IGenresDAO instance;
	private static List<Genre> genres = Arrays.asList(
			new Genre("Pop", 0, 21),
			new Genre("Hip hop", 0, 22),
			new Genre("Rock", 0, 23),
			new Genre("Rhythm and blues", 0, 24),
			new Genre("Soul", 0, 25),
			new Genre("Reggae", 0, 26),
			new Genre("Country", 0, 27),
			new Genre("Funk", 0, 28),
			new Genre("Folk", 0, 29),
			new Genre("Jazz", 0, 31)
			);

	private GenresDAOMemorySingleton(){};

	public static IGenresDAO getInstance() {
		if(instance == null) {
			synchronized (GenresDAOMemorySingleton.class) {
				if(instance == null){
					instance = new GenresMemoryDAO(genres);
				}
			}
		}
		return instance;
	}
}
