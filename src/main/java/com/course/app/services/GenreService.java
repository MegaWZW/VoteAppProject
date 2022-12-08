package com.course.app.services;

import com.course.app.dao.api.IGenresDAO;
import com.course.app.dto.GenresDTO;
import com.course.app.services.api.IGenreService;

public class GenreService implements IGenreService {
	private final IGenresDAO dao;

	public GenreService(IGenresDAO dao) {
		this.dao = dao;
	}

	@Override
	public GenresDTO getTransferObj(IGenresDAO dao) {
		return new GenresDTO(dao.getData());
	}

}
