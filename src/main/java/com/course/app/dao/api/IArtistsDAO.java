package com.course.app.dao.api;

import com.course.app.core.Artist;

import java.util.List;


public interface IArtistsDAO {
	/**
	 * метод для получения списка, хранящегося внутри DAO
	 * @return список строк
	 */
	List<Artist> getData();

	/**
	 * Добавляет исполнителя в DAO
	 * @param artist исполнитель, которого нужно добавить
	 */
	void addPosition(Artist artist);

	/**
	 * Удаляет исполнителя из DAO
	 * @param artist исполнитель, которого необходимо удалить
	 */
	void deletePosition(Artist artist);

	void update(Artist artist);

	Artist getArtist(String name_artist);
}
