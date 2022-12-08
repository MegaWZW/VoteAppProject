package com.course.app.dao;

import com.course.app.dao.api.IArtistsDAO;
import com.course.app.dao.factories.ArtistsDAOMemorySingleton;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ArtistsDAO implements IArtistsDAO {
	private List<String> artists;

	public ArtistsDAO(List<String> artists) {
		this.artists = artists;
	}

	@Override
	public List<String> getData() {
		List<String> dataCopy = new ArrayList<>();
		Collections.copy(dataCopy, artists);
		return dataCopy;
	}

	@Override
	public void addPosition(String artistName) {
		ArtistsDAOMemorySingleton.getInstance().artists.add(artistName);
	}

	@Override
	public void deletePosition(String artistName) {
		ArtistsDAOMemorySingleton.getInstance().artists.remove(artistName);
	}
}