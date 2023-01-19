package com.course.app.dao;

import com.course.app.core.Genre;
import com.course.app.dao.api.IGenresDAO;

import java.util.List;

public class GenresMemoryDAO implements IGenresDAO {
	private List<Genre> genres;

	public GenresMemoryDAO(List<Genre> genres) {
		this.genres = genres;
	}

	@Override
	public List<Genre> getAll() {
		return genres;
	}

	@Override
	public void addPosition(Genre genre) {
		genres.add(genre);
	}

	@Override
	public void updatePosition(Genre toDelete, Genre toAdd) {

	}

	@Override
	public void deletePosition(Genre genre) {
		genres.remove(genre);
	}

	@Override
	public Genre getOne(String name_genre) {
		return null;
	}


	@Override
	public boolean isExist(String name_genre) {
		return false;
	}
}
