package com.course.app.services;

import com.course.app.dao.api.IGenresDAO;
import com.course.app.dto.GenresDTO;
import com.course.app.services.api.IGenreService;

public class GenreService implements IGenreService {
	private final IGenresDAO dao;

	public GenreService(IGenresDAO dao) {
		this.dao = dao;
	}

	/**
	 * метод для получения Genres DTO
	 * @param dao ссылка на GenresDAO
	 * @return DTO
	 */
	@Override
	public GenresDTO getTransferObj(IGenresDAO dao) {
		return new GenresDTO(dao.getData());
	}

}
