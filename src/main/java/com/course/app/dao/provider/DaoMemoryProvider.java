package com.course.app.dao.provider;

import com.course.app.dao.api.IArtistsDAO;
import com.course.app.dao.api.IGenresDAO;
import com.course.app.dao.api.IVotesDAO;
import com.course.app.dao.memory.factroies.ArtistsMemoryDAOSingleton;
import com.course.app.dao.memory.factroies.GenresMemoryDAOSingleton;
import com.course.app.dao.memory.factroies.VotesMemoryDAOSingleton;
import com.course.app.dao.provider.api.IDaoProvider;

public class DaoMemoryProvider implements IDaoProvider {
	@Override
	public IGenresDAO genresDao() {
		return GenresMemoryDAOSingleton.getInstance();
	}

	@Override
	public IArtistsDAO artistsDao() {
		return ArtistsMemoryDAOSingleton.getInstance();
	}

	@Override
	public IVotesDAO votesDao() {
		return VotesMemoryDAOSingleton.getInstance();
	}
}
