package com.course.app.dao.db.factories;

import com.course.app.dao.api.IArtistsDAO;
import com.course.app.dao.db.ArtistsDataBaseDAO;
import com.course.app.dao.db.orm.hibernate.factories.EntityManagerFactorySingleton;

import java.beans.PropertyVetoException;

public class ArtistsDataBaseDAOSingleton {

	private volatile static IArtistsDAO instance;

	private ArtistsDataBaseDAOSingleton(){};

	public static IArtistsDAO getInstance() throws PropertyVetoException {
		if(instance == null) {
			synchronized (ArtistsDataBaseDAOSingleton.class) {
				if (instance == null) {
					instance = new ArtistsDataBaseDAO(EntityManagerFactorySingleton.getInstance());
				}
			}
		}
		return instance;
	}
}
