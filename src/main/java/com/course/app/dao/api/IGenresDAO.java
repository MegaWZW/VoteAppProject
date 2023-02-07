package com.course.app.dao.api;

import com.course.app.dto.GenreDTO;

import java.util.List;

public interface IGenresDAO {
	List<GenreDTO> getAll();
	GenreDTO getOne(String name_genre);
	void addPosition(GenreDTO genre);
	void deletePosition(GenreDTO genre);
	void updatePosition(String toDelete, String toAdd);
	boolean isExist(String name_genre);
}
