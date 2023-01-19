package com.course.app.dao.factories;

import com.course.app.dao.GenreDataBaseDAO;
import com.course.app.dao.api.IGenresDAO;

public class GenreDAODataBaseSingleton {

	private volatile static IGenresDAO instance;

	private GenreDAODataBaseSingleton(){};

	public static IGenresDAO getInstance() {
		if(instance == null) {
			synchronized (GenreDAODataBaseSingleton.class) {
				if (instance == null) {
					instance = new GenreDataBaseDAO();
				}
			}
		}
		return instance;
	}
}
