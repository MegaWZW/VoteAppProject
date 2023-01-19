package com.course.app.dao;

import com.course.app.core.Artist;
import com.course.app.dao.api.IArtistsDAO;

import java.util.List;

public class ArtistsMemoryDAO implements IArtistsDAO {
	private List<Artist> artists;

	public ArtistsMemoryDAO(List<Artist> artists) {
		this.artists = artists;
	}

	@Override
	public List<Artist> getAll() {
		return artists;
	}

	@Override
	public Artist getOne(String name_artist) {
		return null;
	}

		@Override
	public void addPosition(Artist artist) {
		artists.add(artist);
	}

	@Override
	public void deletePosition(Artist artist) {
		artists.remove(artist);
	}

	@Override
	public void updatePosition(Artist toDelete, Artist toAdd) {

	}

	@Override
	public boolean isExist(String name) {
		return false;
	}
}