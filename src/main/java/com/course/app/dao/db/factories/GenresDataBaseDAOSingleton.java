package com.course.app.dao.db.factories;

import com.course.app.dao.db.GenreDataBaseDAO;
import com.course.app.dao.api.IGenresDAO;
import com.course.app.dao.db.ds.factories.DataSourceC3p0Singleton;

import java.beans.PropertyVetoException;

public class GenresDataBaseDAOSingleton {

	private volatile static IGenresDAO instance;

	private GenresDataBaseDAOSingleton(){};

	public static IGenresDAO getInstance() throws PropertyVetoException {
		if(instance == null) {
			synchronized (GenresDataBaseDAOSingleton.class) {
				if (instance == null) {
					instance = new GenreDataBaseDAO(DataSourceC3p0Singleton.getInstance());
				}
			}
		}
		return instance;
	}
}
