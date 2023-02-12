package com.course.app.dao.api;

import com.course.app.dto.ArtistDTO;

import java.util.List;


public interface IArtistsDAO {
	List<ArtistDTO> getAll();
	ArtistDTO getOne(Long id);
	void addPosition(ArtistDTO artist);
	void deletePosition(Long id);
	void updatePosition(ArtistDTO artist);
	boolean isExist(Long id);
}
