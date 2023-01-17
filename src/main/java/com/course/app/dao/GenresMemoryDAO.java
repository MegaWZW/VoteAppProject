package com.course.app.dao;

import com.course.app.core.Genre;
import com.course.app.dao.api.IGenresDAO;

import java.util.List;

public class GenresMemoryDAO implements IGenresDAO {
	private List<Genre> genres;

	public GenresMemoryDAO(List<Genre> genres) {
		this.genres = genres;
	}

	/**
	 * Метод для получения списка музыкальных жанров, хранящихся в DAO
	 * @return
	 */
	@Override
	public List<Genre> getData() {
		return genres;
	}

	/**
	 * Добавляет музыкальный жанр в список для голосования
	 * @param genre наименование жанра, который нужно добавить
	 */
	@Override
	public void addPosition(Genre genre) {
		genres.add(genre);
	}

	@Override
	public void update() {

	}

	/**
	 * Удаляет музыкальный жанр из списка для голосования
	 * @param genre наименование жанра, который нужно удалить
	 */
	@Override
	public void deletePosition(Genre genre) {
		genres.remove(genre);
	}

}
