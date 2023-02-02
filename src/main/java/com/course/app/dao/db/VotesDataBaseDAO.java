package com.course.app.dao.db;

import com.course.app.core.Artist;
import com.course.app.core.Vote;
import com.course.app.dao.api.IVotesDAO;
import com.course.app.dao.db.ds.api.IDataSourceWrapper;
import com.mchange.v2.c3p0.ComboPooledDataSource;

import java.beans.PropertyVetoException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class VotesDataBaseDAO implements IVotesDAO {

	private IDataSourceWrapper dataSource;

	private final String getAll = "";

	public VotesDataBaseDAO(IDataSourceWrapper wrapper){
		this.dataSource = wrapper;
	}

	@Override
	public List<Vote> getAll() {
		String name_artist;
		String[] names_genres;
		try(Connection connection = dataSource.getConnection();
		    PreparedStatement stmt = connection.prepareStatement(getAll);
		    ResultSet resSet = stmt.executeQuery();){
			while (resSet.next()){

			}
		}catch (SQLException e){
			throw new RuntimeException(e);
		}
		return null;
	}

	@Override
	public void save(Vote vote) {

	}
}
