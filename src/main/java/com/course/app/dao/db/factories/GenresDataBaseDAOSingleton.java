package com.course.app.dao.db.factories;

import com.course.app.dao.api.IGenresDAO;
import com.course.app.dao.db.GenreDataBaseDAO;
import com.course.app.dao.db.orm.hibernate.factories.EntityManagerFactorySingleton;

import java.beans.PropertyVetoException;

public class GenresDataBaseDAOSingleton {

	private volatile static IGenresDAO instance;

	private GenresDataBaseDAOSingleton(){};

	public static IGenresDAO getInstance() throws PropertyVetoException {
		if(instance == null) {
			synchronized (GenresDataBaseDAOSingleton.class) {
				if (instance == null) {
					instance = new GenreDataBaseDAO(EntityManagerFactorySingleton.getInstance());
				}
			}
		}
		return instance;
	}
}
