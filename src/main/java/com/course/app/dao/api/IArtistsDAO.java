package com.course.app.dao.api;

import java.util.List;


public interface IArtistsDAO {
	/**
	 * метод для получения списка, хранящегося внутри DAO
	 * @return список строк
	 */
	List<String> getData();

	/**
	 * Добавляет исполнителя в DAO
	 * @param artistName имя исполнителя, которого нужно добавить
	 */
	void addPosition(String artistName);

	/**
	 * Удаляет исполнителя из DAO
	 * @param artistName имя исполнителя, которого необходимо удалить
	 */
	void deletePosition(String artistName);
}
