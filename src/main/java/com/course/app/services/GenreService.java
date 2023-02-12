package com.course.app.services;

import com.course.app.dao.api.IGenresDAO;
import com.course.app.dto.GenreDTO;
import com.course.app.services.api.IGenreService;

import java.util.List;

public class GenreService implements IGenreService {
	private final IGenresDAO dao;

	public GenreService(IGenresDAO dao) {
		this.dao = dao;
	}

	@Override
	public List<GenreDTO> getAll() {
		return dao.getAll();
	}

	@Override
	public GenreDTO getOne(Long id) {
		return dao.getOne(id);
	}

	@Override
	public void addPosition(GenreDTO genre) {
		dao.addPosition(genre);
	}

	@Override
	public void deletePosition(Long id) {
		dao.deletePosition(id);
	}

	@Override
	public void updatePosition(GenreDTO genre) {
		dao.updatePosition(genre);
	}

	@Override
	public boolean isExist(Long id) {
		return dao.isExist(id);
	}
}
