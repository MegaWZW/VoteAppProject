package com.course.app.dao.api;

import com.course.app.services.Vote;

import java.util.List;

public interface IVotesDAO {
	/**
	 * Получает список всех, объектов Голос, хранищихся в DAO
	 * @return список объектов
	 */
	List<Vote> getData();

	/**
	 * метод для сохранения объекта Голос в DAO
	 * @param vote
	 */
	void save(Vote vote);
}
