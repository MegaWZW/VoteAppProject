package com.course.app.services.api;


import com.course.app.dao.api.IVotesDAO;
import com.course.app.dto.VoteDTO;
import com.course.app.services.Result;
import com.course.app.services.SortedResult;
import com.course.app.services.Vote;

public interface IVoteService {
	/**
	 * Метод для сохранения голоса в системе
	 * @param dto объект DTO
	 */
	void save(VoteDTO dto);

	/**
	 * Метод для проверки корректоности переданнх данных
	 * @param dto объект DTO
	 * @return объект Голос
	 */
	Vote validate(VoteDTO dto);

	/**
	 * Метод для сортирровки резултатов голосования
	 * @param result объект, хранящий результат подсчёта голосов
	 * @return объект, содержащий отсортированные данные
	 */
	SortedResult sort(Result result);
	IVotesDAO getDao();
}
