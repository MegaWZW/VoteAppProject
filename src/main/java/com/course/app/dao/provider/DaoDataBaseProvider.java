package com.course.app.dao.provider;

import com.course.app.dao.api.IArtistsDAO;
import com.course.app.dao.api.IGenresDAO;
import com.course.app.dao.api.IVotesDAO;
import com.course.app.dao.db.factories.GenreDataBaseDAOSingleton;
import com.course.app.dao.provider.api.IDaoProvider;

import java.beans.PropertyVetoException;

public class DaoDataBaseProvider implements IDaoProvider {
	@Override
	public IGenresDAO genresDao() {
		try{
			return GenreDataBaseDAOSingleton.getInstance();
		}catch(PropertyVetoException e) {
			throw new IllegalStateException(e);
		}
	}

	@Override
	public IArtistsDAO artistsDao() {
		return null;
	}

	@Override
	public IVotesDAO votesDao() {
		return null;
	}
}
