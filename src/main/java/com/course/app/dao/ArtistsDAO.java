package com.course.app.dao;

import com.course.app.dao.api.IArtistsDAO;

import java.util.List;

public class ArtistsDAO implements IArtistsDAO {
	private List<String> artists;

	public ArtistsDAO(List<String> artists) {
		this.artists = artists;
	}

	/**
	 * Метод для получения списка артистов, хранящихся в DAO
	 * @return список имён артистов
	 */
	@Override
	public List<String> getData() {
		return artists;
	}

	/**
	 * Добавляет имя исполнителя с список для голосования
	 * @param artistName имя исполнителя, которого нужно добавить
	 */
	@Override
	public void addPosition(String artistName) {
		artists.add(artistName);
	}

	/**
	 * Метод для удаления артиста из списка для голосования
	 * @param artistName имя исполнителя, которого необходимо удалить
	 */
	@Override
	public void deletePosition(String artistName) {
		artists.remove(artistName);
	}
}