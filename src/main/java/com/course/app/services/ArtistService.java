package com.course.app.services;

import com.course.app.core.Artist;
import com.course.app.dao.api.IArtistsDAO;
import com.course.app.dto.ArtistsDTO;
import com.course.app.services.api.IArtistService;

import java.util.ArrayList;
import java.util.List;

public class ArtistService implements IArtistService {

	private final IArtistsDAO dao;

	public ArtistService(IArtistsDAO dao) {
		this.dao = dao;
	}

	/**
	 * метод для получения Artists DTO
	 * @param dao ссылка на ArtistsDAO
	 * @return DTO
	 */
	@Override
	public ArtistsDTO getTransferObj() {
		List<String> artistNames = new ArrayList<>();
		List<Artist> artists = dao.getData();

		for(Artist art : artists) {
			artistNames.add(art.getName());
		}
		return new ArtistsDTO(artistNames);
	}

}
