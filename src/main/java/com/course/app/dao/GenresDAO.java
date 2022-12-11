package com.course.app.dao;

import com.course.app.dao.api.IGenresDAO;

import java.util.List;

public class GenresDAO implements IGenresDAO {
	private List<String> genres;

	public GenresDAO(List<String> genres) {
		this.genres = genres;
	}

	/**
	 * Метод для получения списка музыкальных жанров, хранящихся в DAO
	 * @return
	 */
	@Override
	public List<String> getData() {
		return genres;
	}

	/**
	 * Добавляет музыкальный жанр в список для голосования
	 * @param genreName наименование жанра, который нужно добавить
	 */
	@Override
	public void addPosition(String genreName) {
		genres.add(genreName);
	}

	/**
	 * Удаляет музыкальный жанр из списка для голосования
	 * @param genreName наименование жанра, который нужно удалить
	 */
	@Override
	public void deletePosition(String genreName) {
		genres.remove(genreName);
	}

}
