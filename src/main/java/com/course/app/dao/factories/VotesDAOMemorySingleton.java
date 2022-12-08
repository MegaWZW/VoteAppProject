package com.course.app.dao.factories;

import com.course.app.dao.VotesDAO;
import com.course.app.services.Vote;

import java.util.ArrayList;
import java.util.List;

public class VotesDAOMemorySingleton {
	private volatile static VotesDAO instance;
	private static List<Vote> votes = new ArrayList<>();

	private VotesDAOMemorySingleton(){}

	public static VotesDAO getInstance(){
		if (instance == null) {
			synchronized (VotesDAOMemorySingleton.class) {
				if (instance == null) {
					instance = new VotesDAO(votes);
				}
			}
		}
		return instance;
	}
}
