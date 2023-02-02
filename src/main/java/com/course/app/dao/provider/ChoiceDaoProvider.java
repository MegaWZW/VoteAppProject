package com.course.app.dao.provider;

import com.course.app.dao.api.IArtistsDAO;
import com.course.app.dao.api.IGenresDAO;
import com.course.app.dao.api.IVotesDAO;
import com.course.app.dao.provider.api.IDaoProvider;

public class ChoiceDaoProvider implements IDaoProvider {

	private volatile static ChoiceDaoProvider instance;
	private boolean useDB = true; //enum is better
	private IDaoProvider daoProvider;

	private ChoiceDaoProvider() {
		if(useDB) {
			this.daoProvider = new DaoDataBaseProvider();
		} else {
			this.daoProvider = new DaoMemoryProvider();
		}
	}

	@Override
	public IGenresDAO genresDao() {
		return daoProvider.genresDao();
	}

	@Override
	public IArtistsDAO artistsDao() {
		return daoProvider.artistsDao();
	}

	@Override
	public IVotesDAO votesDao() {
		return daoProvider.votesDao();
	}

	public static IDaoProvider getInstance() {
		if(instance == null) {
			synchronized (ChoiceDaoProvider.class) {
				if(instance == null){
					instance = new ChoiceDaoProvider();
				}
			}
		}
		return instance;
	}
}
