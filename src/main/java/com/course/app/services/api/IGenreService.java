package com.course.app.services.api;

import com.course.app.dto.GenreDTO;

import java.util.List;

public interface IGenreService {
	List<GenreDTO> getAll();
	GenreDTO getOne(Long id);
	void addPosition(GenreDTO genre);
	void deletePosition(Long id);
	void updatePosition(GenreDTO genre);
	boolean isExist(Long id);
}
