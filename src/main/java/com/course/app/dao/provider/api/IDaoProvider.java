package com.course.app.dao.provider.api;

import com.course.app.dao.api.IArtistsDAO;
import com.course.app.dao.api.IGenresDAO;
import com.course.app.dao.api.IVotesDAO;

public interface IDaoProvider {
	IGenresDAO genresDao();
	IArtistsDAO artistsDao();
	IVotesDAO votesDao();
}
