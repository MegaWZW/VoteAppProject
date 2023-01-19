package com.course.app.services;

import com.course.app.core.Genre;
import com.course.app.dao.api.IGenresDAO;
import com.course.app.dto.GenresDTO;
import com.course.app.services.api.IGenreService;

import java.util.ArrayList;
import java.util.List;

public class GenreService implements IGenreService {
	private final IGenresDAO dao;

	public GenreService(IGenresDAO dao) {
		this.dao = dao;
	}

	@Override
	public GenresDTO getTransferObj() {
		List<String> genreNames = new ArrayList<>();
		List<Genre> genres = dao.getAll();

		for(Genre gen : genres){
			genreNames.add(gen.getName());
		}
		return new GenresDTO(genreNames);
	}

}
