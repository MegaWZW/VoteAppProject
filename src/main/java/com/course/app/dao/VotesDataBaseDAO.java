package com.course.app.dao;

import com.course.app.core.Vote;
import com.course.app.dao.api.IVotesDAO;
import com.mchange.v2.c3p0.ComboPooledDataSource;

import java.beans.PropertyVetoException;
import java.util.List;

public class VotesDataBaseDAO implements IVotesDAO {

	private ComboPooledDataSource dataSource;

	public VotesDataBaseDAO(){
		dataSource = new ComboPooledDataSource();
		try {
			dataSource.setDriverClass( "org.postgresql.Driver" );
		} catch (PropertyVetoException e) {
			throw new RuntimeException(e);
		}
		dataSource.setJdbcUrl( "jdbc:postgresql://localhost:5432/VoteApp" );
		dataSource.setUser("postgres");
		dataSource.setPassword("arsenal1577980");
	}

	@Override
	public List<Vote> getAll() {
		return null;
	}

	@Override
	public void save(Vote vote) {

	}
}
