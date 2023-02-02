package com.course.app.dao.memory.factroies;

import com.course.app.core.Genre;
import com.course.app.dao.memory.GenresMemoryDAO;
import com.course.app.dao.api.IGenresDAO;

import java.util.Arrays;
import java.util.List;

public class GenresMemoryDAOSingleton {
	private volatile static IGenresDAO instance;

	private static List<Genre> genres = Arrays.asList(
			new Genre("Pop", 0, 1),
			new Genre("Hip hop", 0, 2),
			new Genre("Rock", 0, 3),
			new Genre("Rhythm and blues", 0, 4),
			new Genre("Soul", 0, 5),
			new Genre("Reggae", 0, 6),
			new Genre("Country", 0, 7),
			new Genre("Funk", 0, 8),
			new Genre("Folk", 0, 9),
			new Genre("Jazz", 0, 10)
			);

	private GenresMemoryDAOSingleton(){};

	public static IGenresDAO getInstance() {
		if(instance == null) {
			synchronized (GenresMemoryDAOSingleton.class) {
				if(instance == null){
					instance = new GenresMemoryDAO(genres);
				}
			}
		}
		return instance;
	}
}
