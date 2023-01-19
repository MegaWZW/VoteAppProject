package com.course.app.dao.api;

import com.course.app.core.Genre;

import java.util.List;

public interface IGenresDAO {
	List<Genre> getAll();
	Genre getOne(String name_genre);
	void addPosition(Genre genre);
	void deletePosition(Genre genre);
	void updatePosition(Genre toDelete, Genre toAdd);
	boolean isExist(String name_genre);
}
