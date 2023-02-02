package com.course.app.dao.db.factories;

import com.course.app.dao.db.GenreDataBaseDAO;
import com.course.app.dao.api.IGenresDAO;
import com.course.app.dao.db.ds.factories.DataSourceC3p0Singleton;

import java.beans.PropertyVetoException;

public class GenreDataBaseDAOSingleton {

	private volatile static IGenresDAO instance;

	private GenreDataBaseDAOSingleton(){};

	public static IGenresDAO getInstance() throws PropertyVetoException {
		if(instance == null) {
			synchronized (GenreDataBaseDAOSingleton.class) {
				if (instance == null) {
					instance = new GenreDataBaseDAO(DataSourceC3p0Singleton.getInstance());
				}
			}
		}
		return instance;
	}
}
