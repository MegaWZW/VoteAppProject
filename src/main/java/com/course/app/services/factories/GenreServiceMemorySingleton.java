package com.course.app.services.factories;

import com.course.app.dao.factories.GenresDAOMemorySingleton;
import com.course.app.services.GenreService;

public class GenreServiceMemorySingleton {
	private volatile static GenreService instance;

	private GenreServiceMemorySingleton(){}

	public static GenreService getInstance(){
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
