package com.course.app.dao;

import com.course.app.dao.api.IGenresDAO;
import com.course.app.dao.factories.ArtistsDAOMemorySingleton;
import com.course.app.dao.factories.GenresDAOMemorySingleton;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class GenresDAO implements IGenresDAO {
	private List<String> genres;

	public GenresDAO(List<String> genres) {
		this.genres = genres;
	}

	@Override
	public List<String> getData() {
		List<String> dataCopy = new ArrayList<>();
		Collections.copy(dataCopy,genres);
		return dataCopy;
	}

	@Override
	public void addPosition(String genreName) {
		GenresDAOMemorySingleton.getInstance().genres.add(genreName);
	}

	@Override
	public void deletePosition(String genreName) {
		GenresDAOMemorySingleton.getInstance().genres.remove(genreName);
	}

}
