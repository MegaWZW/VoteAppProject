package com.course.app.dao.api;

import com.course.app.dto.ArtistDTO;

import java.util.List;


public interface IArtistsDAO {
	List<ArtistDTO> getAll();
	ArtistDTO getOne(String name_artist);
	void addPosition(ArtistDTO artist);
	void deletePosition(ArtistDTO artist);
	void updatePosition(String toDelete, String toAdd);
	boolean isExist(String name);
}
