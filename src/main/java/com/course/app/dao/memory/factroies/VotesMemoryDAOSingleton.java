package com.course.app.dao.memory.factroies;

import com.course.app.dao.api.IVotesDAO;
import com.course.app.dao.memory.VotesMemoryDAO;

public class VotesMemoryDAOSingleton {
	private volatile static IVotesDAO instance;

	private VotesMemoryDAOSingleton(){}

	public static IVotesDAO getInstance(){
		if (instance == null) {
			synchronized (VotesMemoryDAOSingleton.class) {
				if (instance == null) {
					instance = new VotesMemoryDAO();
				}
			}
		}
		return instance;
	}
}
