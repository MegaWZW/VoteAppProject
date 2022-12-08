package com.course.app.services.api;

import com.course.app.dao.api.IGenresDAO;
import com.course.app.dto.GenresDTO;


public interface IGenreService {
	GenresDTO getTransferObj (IGenresDAO dao);
}
