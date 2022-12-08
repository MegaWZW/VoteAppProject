package com.course.app.dao.factories;

import com.course.app.dao.GenresDAO;

import java.util.Arrays;
import java.util.List;

public class GenresDAOMemorySingleton {
	private volatile static GenresDAO instance;
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

	public static GenresDAO getInstance() {
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
