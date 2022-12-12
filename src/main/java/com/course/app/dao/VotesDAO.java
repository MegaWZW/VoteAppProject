package com.course.app.dao;

import com.course.app.dao.api.IVotesDAO;
import com.course.app.core.Vote;

import java.util.List;

public class VotesDAO implements IVotesDAO {
	private List<Vote> votes;

	public VotesDAO(List<Vote> votes) {
		this.votes = votes;
	}

	/**
	 * Метод для получения списка голосов
	 * @return список, содержащий объекты Голос
	 */
	@Override
	public List<Vote> getData() {
		return votes;
	}

	/**
	 * Метод для сохранения голоса в список
	 * @param vote объект типа Голос, содержащий информацию о выбранных
	 *             пользователем музыкальных жанрах, исполнителе, времени принятия голоса,
	 *             а также текст сообщениия, оставленного пользователем
	 *
	 */
	@Override
	public void save(Vote vote) {
		votes.add(vote);
	}
}
