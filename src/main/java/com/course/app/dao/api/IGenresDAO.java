package com.course.app.dao.api;

import java.util.List;

public interface IGenresDAO {
	/**
	 * метод для получения списка, хранящегося внутри DAO
	 * @return список строк
	 */
	List<String> getData();

	/**
	 * Добавляет музыкальный жанр в DAO
	 * @param genreName наименование жанра, который нужно добавить
	 */
	void addPosition(String genreName);

	/**
	 * Удаляет музыкальый жанр в DAO
	 * @param genreName наименование жанра, который нужно удалить
	 */
	void deletePosition(String genreName);
}
