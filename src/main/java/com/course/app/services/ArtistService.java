package com.course.app.services;

import com.course.app.dao.api.IArtistsDAO;
import com.course.app.dto.ArtistsDTO;
import com.course.app.services.api.IArtistService;

public class ArtistService implements IArtistService {

	private final IArtistsDAO dao;

	public ArtistService(IArtistsDAO dao) {
		this.dao = dao;
	}

	@Override
	public ArtistsDTO getTransferObj() {
		return new ArtistsDTO(dao.getAll());
	}
}
