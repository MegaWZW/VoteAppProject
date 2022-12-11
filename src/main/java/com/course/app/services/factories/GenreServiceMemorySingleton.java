package com.course.app.services.factories;

import com.course.app.dao.factories.GenresDAOMemorySingleton;
import com.course.app.services.GenreService;
import com.course.app.services.api.IGenreService;

public class GenreServiceMemorySingleton {
	private volatile static IGenreService instance;

	private GenreServiceMemorySingleton(){}

	public static IGenreService getInstance(){
		if(instance == null) {
			synchronized (GenreServiceMemorySingleton.class) {
				if (instance == null) {
					instance = new GenreService(GenresDAOMemorySingleton.getInstance());
				}
			}
		}
		return instance;
	}
}
