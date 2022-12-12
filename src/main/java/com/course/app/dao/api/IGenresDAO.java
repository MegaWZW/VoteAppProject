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
	public void addPosition(Genre genre);

	/**
	 * Удаляет музыкальый жанр в DAO
	 * @param genre наименование жанра, который нужно удалить
	 */
	public void deletePosition(Genre genre);
}
