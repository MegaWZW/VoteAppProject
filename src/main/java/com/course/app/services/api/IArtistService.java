package com.course.app.services.api;

import com.course.app.dao.api.IArtistsDAO;
import com.course.app.dto.ArtistsDTO;

public interface IArtistService {
	ArtistsDTO getTransferObj (IArtistsDAO dao);

}
