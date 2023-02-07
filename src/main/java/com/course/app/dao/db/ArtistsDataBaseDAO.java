package com.course.app.dao.db;

import com.course.app.dao.db.entity.Artist;
import com.course.app.dao.api.IArtistsDAO;
import com.course.app.dao.db.ds.api.IDataSourceWrapper;
import com.course.app.dto.ArtistDTO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

public class ArtistsDataBaseDAO implements IArtistsDAO {

	private IDataSourceWrapper dataSource;

	private final static String GET_ALL_ROWS = "SELECT id, name " +
			                                   "FROM app.artist;" ;

	private final static String GET_ONE_ROW = "SELECT id, name " +
			                                  "FROM app.artist " +
			                                  "WHERE name LIKE ?;" ;

	private final static String  INSERT_ROW_INTO_TABLE = "INSERT INTO app.artist (name) " +
			                                             "VALUES (?);" ;

	private final static String DELETE_ROW_FROM_TABLE = "DELETE FROM app.artist " +
			                                            "WHERE name LIKE ?;" ;

	private final static String UPDATE_ROW = "UPDATE app.artist " +
			                                 "SET name = ? " +
			                                 "WHERE name LIKE ?;" ;

	public ArtistsDataBaseDAO(IDataSourceWrapper wrapper){
		 this.dataSource = wrapper;
	}

	@Override
	public List<ArtistDTO> getAll() {
		List<ArtistDTO> list = new ArrayList<>();
		try(Connection connection = dataSource.getConnection();
		    PreparedStatement stmt = connection.prepareStatement(GET_ALL_ROWS);
		    ResultSet resSet = stmt.executeQuery();){
			while (resSet.next()){
				String name = resSet.getString("name");
				long id = resSet.getLong("id");
				list.add(new ArtistDTO(id, name));
			}
		}catch (SQLException e){
			try {
				dataSource.close();
			} catch (Exception ex) {
				throw new RuntimeException("Проблема с закрытием соединения");
			}
			throw new RuntimeException(e);
		}
		return list;
	}

	@Override
	public ArtistDTO getOne(String name_artist) {
		if(!isExist(name_artist)){
			throw new NoSuchElementException("Такой артист не принимает участие в голосовании");
		}
		ArtistDTO artist = null;

		try(Connection connection = dataSource.getConnection();
		    PreparedStatement stmt = connection.prepareStatement(GET_ONE_ROW);){

			stmt.setString(1, name_artist);
			try(ResultSet resSet = stmt.executeQuery()){
				while (resSet.next()){
					artist = new ArtistDTO(resSet.getLong("id"), resSet.getString("name"));
				}
			}catch (SQLException e){
				try {
					dataSource.close();
				} catch (Exception ex) {
					throw new RuntimeException("Проблема с закрытием соединения");
				}
				throw new RuntimeException(e);
			}

		}catch (SQLException e){
			throw new RuntimeException(e);
		}
		return artist;
	}

	@Override
	public void addPosition(ArtistDTO artist) {
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
			try {
				dataSource.close();
			} catch (Exception ex) {
				throw new RuntimeException("Проблема с закрытием соединения");
			}
			throw new RuntimeException(e);
		}
	}

	@Override
	public void deletePosition(ArtistDTO artist) {
		try(Connection connection = dataSource.getConnection();
		    PreparedStatement stmt = connection.prepareStatement(DELETE_ROW_FROM_TABLE)) {

			connection.setAutoCommit(false);
			stmt.setString(1, artist.getName());
			stmt.executeUpdate();
			connection.commit();

		}catch (SQLException e){
			try {
				dataSource.close();
			} catch (Exception ex) {
				throw new RuntimeException("Проблема с закрытием соединения");
			}
			throw new RuntimeException(e);
		}
	}

	@Override
	public void updatePosition(String toDelete, String toAdd) {
		if(!isExist(toDelete)){
			throw new NoSuchElementException("Изменяемого артиста не существует");
		}

		try(Connection connection = dataSource.getConnection();
		    PreparedStatement stmt = connection.prepareStatement(UPDATE_ROW)) {

			connection.setAutoCommit(false);
			stmt.setString(1, toAdd);
			stmt.setString(2, toDelete);
			stmt.executeUpdate();
			connection.commit();

		}catch (SQLException e){
			try {
				dataSource.close();
			} catch (Exception ex) {
				throw new RuntimeException("Проблема с закрытием соединения");
			}
			throw new RuntimeException(e);
		}
	}

	@Override
	public boolean isExist(String name) {
		List<ArtistDTO> all = getAll();
		for (ArtistDTO artist : all) {
			if(artist.getName().equals(name)){
				return true;
			}
		}
		return false;
	}
}
