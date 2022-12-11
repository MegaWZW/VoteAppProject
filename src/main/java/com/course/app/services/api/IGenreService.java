package com.course.app.services.api;

import com.course.app.dao.api.IGenresDAO;
import com.course.app.dto.GenresDTO;


public interface IGenreService {
	/**
	 * Метод для получения объета GenresDTO
	 * @param dao ссылка на GenresDAO
	 * @return возвращает DTO
	 */
	GenresDTO getTransferObj (IGenresDAO dao);
}
