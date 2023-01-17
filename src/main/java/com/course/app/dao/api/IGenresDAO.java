package com.course.app.dao.api;

import com.course.app.core.Genre;

import java.util.List;

public interface IGenresDAO {
	/**
	 * метод для получения списка, хранящегося внутри DAO
	 * @return список строк
	 */
	List<Genre> getData();

	/**
	 * Добавляет музыкальный жанр в DAO
	 * @param genre наименование жанра, который нужно добавить
	 */
	void addPosition(Genre genre);

	/**
	 * Удаляет музыкальый жанр в DAO
	 * @param genre наименование жанра, который нужно удалить
	 */
	void deletePosition(Genre genre);

	void update();

}
