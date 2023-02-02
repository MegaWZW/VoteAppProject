package com.course.app.dao.memory.factroies;

import com.course.app.dao.memory.VotesMemoryDAO;
import com.course.app.dao.api.IVotesDAO;
import com.course.app.core.Vote;

import java.util.ArrayList;
import java.util.List;

public class VotesMemoryDAOSingleton {
	private volatile static IVotesDAO instance;

	private static List<Vote> votes = new ArrayList<>();

	private VotesMemoryDAOSingleton(){}

	public static IVotesDAO getInstance(){
		if (instance == null) {
			synchronized (VotesMemoryDAOSingleton.class) {
				if (instance == null) {
					instance = new VotesMemoryDAO(votes);
				}
			}
		}
		return instance;
	}
}
