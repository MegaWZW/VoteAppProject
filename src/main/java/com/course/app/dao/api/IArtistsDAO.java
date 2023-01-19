package com.course.app.dao.api;

import com.course.app.core.Artist;

import java.util.List;


public interface IArtistsDAO {
	List<Artist> getAll();
	Artist getOne(String name_artist);
	void addPosition(Artist artist);
	void deletePosition(Artist artist);
	void updatePosition(Artist toDelete, Artist toAdd);
	boolean isExist(String name);
}
