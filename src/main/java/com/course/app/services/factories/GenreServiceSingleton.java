package com.course.app.services.factories;

import com.course.app.dao.provider.ChoiceDaoProvider;
import com.course.app.services.GenreService;
import com.course.app.services.api.IGenreService;

public class GenreServiceSingleton {
	private volatile static IGenreService instance;

	private GenreServiceSingleton(){}

	public static IGenreService getInstance(){
		if(instance == null) {
			synchronized (GenreServiceSingleton.class) {
				if (instance == null) {
					instance = new GenreService(ChoiceDaoProvider.getInstance().genresDao());
				}
			}
		}
		return instance;
	}
}
