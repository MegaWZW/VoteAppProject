package com.course.app.dao;

import com.course.app.core.Artist;
import com.course.app.dao.api.IArtistsDAO;

import java.util.List;

public class ArtistsMemoryDAO implements IArtistsDAO {
	private List<Artist> artists;

	@Override
	public void update(Artist artist) {

	}

	@Override
	public Artist getArtist(String name_artist) {
		return null;
	}

	public ArtistsMemoryDAO(List<Artist> artists) {
		this.artists = artists;
	}

	/**
	 * Метод для получения списка артистов, хранящихся в DAO
	 * @return список имён артистов
	 */
	@Override
	public List<Artist> getData() {
		return artists;
	}

	/**
	 * Добавляет имя исполнителя с список для голосования
	 * @param artist исполнитель, которого нужно добавить
	 */
	@Override
	public void addPosition(Artist artist) {
		artists.add(artist);
	}

	/**
	 * Метод для удаления артиста из списка для голосования
	 * @param artist исполнитель, которого необходимо удалить
	 */
	@Override
	public void deletePosition(Artist artist) {
		artists.remove(artist);
	}
}