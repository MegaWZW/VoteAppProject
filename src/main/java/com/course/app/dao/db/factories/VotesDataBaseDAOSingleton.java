package com.course.app.dao.db.factories;

import com.course.app.dao.api.IVotesDAO;
import com.course.app.dao.db.VotesDataBaseDAO;
import com.course.app.dao.db.ds.factories.DataSourceC3p0Singleton;

import java.beans.PropertyVetoException;

public class VotesDataBaseDAOSingleton {

	private volatile static IVotesDAO instance;

	private VotesDataBaseDAOSingleton(){};

	public static IVotesDAO getInstance() throws PropertyVetoException {
		if(instance == null) {
			synchronized (VotesDataBaseDAOSingleton.class) {
				if (instance == null) {
					instance = new VotesDataBaseDAO(DataSourceC3p0Singleton.getInstance());
				}
			}
		}
		return instance;
	}
}
