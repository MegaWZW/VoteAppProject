package com.course.app.dao.factories;

import com.course.app.dao.VotesDAO;
import com.course.app.dao.api.IVotesDAO;
import com.course.app.services.Vote;

import java.util.ArrayList;
import java.util.List;

/**
 * Класс для получения уникального объекта VotesDAO
 * Реализован с помощью паттерна синглтон
 */
public class VotesDAOMemorySingleton {
	private volatile static IVotesDAO instance;
	private static List<Vote> votes = new ArrayList<>();

	private VotesDAOMemorySingleton(){}

	public static IVotesDAO getInstance(){
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
