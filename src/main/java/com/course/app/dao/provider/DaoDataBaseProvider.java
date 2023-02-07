package com.course.app.dao.provider;

import com.course.app.dao.api.IArtistsDAO;
import com.course.app.dao.api.IGenresDAO;
import com.course.app.dao.api.IVotesDAO;
import com.course.app.dao.db.factories.ArtistsDataBaseDAOSingleton;
import com.course.app.dao.db.factories.GenresDataBaseDAOSingleton;
import com.course.app.dao.db.factories.VotesDataBaseDAOSingleton;
import com.course.app.dao.provider.api.IDaoProvider;

import java.beans.PropertyVetoException;

public class DaoDataBaseProvider implements IDaoProvider {
	@Override
	public IGenresDAO genresDao() {
		try{
			return GenresDataBaseDAOSingleton.getInstance();
		}catch(PropertyVetoException e) {
			throw new IllegalStateException(e);
		}
	}

	@Override
	public IArtistsDAO artistsDao() {
		try{
			return ArtistsDataBaseDAOSingleton.getInstance();
		}catch(PropertyVetoException e) {
			throw new IllegalStateException(e);
		}
	}

	@Override
	public IVotesDAO votesDao() {
		try{
			return VotesDataBaseDAOSingleton.getInstance();
		}catch(PropertyVetoException e) {
			throw new IllegalStateException(e);
		}
	}
}
