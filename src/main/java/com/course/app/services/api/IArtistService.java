package com.course.app.services.api;

import com.course.app.dao.api.IArtistsDAO;
import com.course.app.dto.ArtistsDTO;

public interface IArtistService {
	/**
	 * Метод для получения объета ArtistsDTO
	 * @param dao ссылка на ArtistsDAO
	 * @return возвращает DTO
	 */
	ArtistsDTO getTransferObj ();

}
