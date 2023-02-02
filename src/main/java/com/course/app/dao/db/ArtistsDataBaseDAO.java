package com.course.app.dao.db;

import com.course.app.core.Artist;
import com.course.app.dao.api.IArtistsDAO;
import com.course.app.dao.db.ds.api.IDataSourceWrapper;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

public class ArtistsDataBaseDAO implements IArtistsDAO {

	private IDataSourceWrapper dataSource;

	private final static String GET_ALL_ROWS = "SELECT artist_id, name_artist  " +
			                                   "FROM app.artist;" ;

	private final static String GET_ONE_ROW = "SELECT artist_id, name_artist " +
			                                  "FROM app.artist " +
			                                  "WHERE name_artist LIKE ?;" ;

	private final static String  INSERT_ROW_INTO_TABLE = "INSERT INTO app.artist (name_artist) " +
			                                             "VALUES (?);" ;

	private final static String DELETE_ROW_FROM_TABLE = "DELETE FROM app.artist " +
			                                            "WHERE name_artist LIKE ?;" ;

	private final static String UPDATE_ROW = "UPDATE app.artist " +
			                                 "SET name_artist = ? " +
			                                 "WHERE name_artist LIKE ?;" ;

	public ArtistsDataBaseDAO(IDataSourceWrapper wrapper){
		 this.dataSource = wrapper;
	}

	@Override
	public List<Artist> getAll() {
		List<Artist> list = new ArrayList<>();
		try(Connection connection = dataSource.getConnection();
		    PreparedStatement stmt = connection.prepareStatement(GET_ALL_ROWS);
		    ResultSet resSet = stmt.executeQuery();){
			while (resSet.next()){
				String name = resSet.getString("name_artist");
				long id = resSet.getLong("artist_id");
				list.add(new Artist(name, id));
			}
		}catch (SQLException e){
			throw new RuntimeException(e);
		}
		return list;
	}

	@Override
	public Artist getOne(String name_artist) {
		if(!isExist(name_artist)){
			throw new NoSuchElementException("Такой артист не принимает участие в голосовании");
		}
		Artist artist = null;

		try(Connection connection = dataSource.getConnection();
		    PreparedStatement stmt = connection.prepareStatement(GET_ONE_ROW);){

			stmt.setString(1, name_artist);
			try(ResultSet resSet = stmt.executeQuery()){
				while (resSet.next()){
					artist = new Artist(resSet.getString("name_artist"), resSet.getLong("artist_id"));
				}
			}catch (SQLException e){
				throw new RuntimeException(e);
			}

		}catch (SQLException e){
			throw new RuntimeException(e);
		}
		return artist;
	}

	@Override
	public void addPosition(Artist artist) {
		if(isExist(artist.getName())){
			throw new IllegalArgumentException("Артист с таким именем уже участвует в голосовании");
		}

		try(Connection connection = dataSource.getConnection();
		    PreparedStatement stmt = connection.prepareStatement(INSERT_ROW_INTO_TABLE)) {

			connection.setAutoCommit(false);
			stmt.setString(1, artist.getName());
			stmt.executeUpdate();
			connection.commit();

		}catch (SQLException e){
			throw new RuntimeException(e);
		}
	}

	@Override
	public void deletePosition(Artist artist) {
		try(Connection connection = dataSource.getConnection();
		    PreparedStatement stmt = connection.prepareStatement(DELETE_ROW_FROM_TABLE)) {

			connection.setAutoCommit(false);
			stmt.setString(1, artist.getName());
			stmt.executeUpdate();
			connection.commit();

		}catch (SQLException e){
			throw new RuntimeException(e);
		}
	}

	@Override
	public void updatePosition(Artist toDelete, Artist toAdd) {
		if(!isExist(toDelete.getName())){
			throw new NoSuchElementException("Изменяемого артиста не существует");
		}

		try(Connection connection = dataSource.getConnection();
		    PreparedStatement stmt = connection.prepareStatement(UPDATE_ROW)) {

			connection.setAutoCommit(false);
			stmt.setString(1, toAdd.getName());
			stmt.setString(2, toDelete.getName());
			stmt.executeUpdate();
			connection.commit();

		}catch (SQLException e){
			throw new RuntimeException(e);
		}
	}

	@Override
	public boolean isExist(String name) {
		List<Artist> all = getAll();
		for (Artist artist : all) {
			if(artist.getName().equals(name)){
				return true;
			}
		}
		return false;
	}
}
