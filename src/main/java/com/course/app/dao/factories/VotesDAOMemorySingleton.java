package com.course.app.dao.factories;

import com.course.app.dao.VotesMemoryDAO;
import com.course.app.dao.api.IVotesDAO;
import com.course.app.core.Vote;

import java.util.ArrayList;
import java.util.List;

public class VotesDAOMemorySingleton {
	private volatile static IVotesDAO instance;

	private static List<Vote> votes = new ArrayList<>();

	private VotesDAOMemorySingleton(){}

	public static IVotesDAO getInstance(){
		if (instance == null) {
			synchronized (VotesDAOMemorySingleton.class) {
				if (instance == null) {
					instance = new VotesMemoryDAO(votes);
				}
			}
		}
		return instance;
	}
}
