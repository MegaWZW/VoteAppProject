package com.course.app.dao;

import com.course.app.core.Artist;
import com.course.app.dao.api.IArtistsDAO;
import com.mchange.v2.c3p0.ComboPooledDataSource;

import java.beans.PropertyVetoException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

public class ArtistsDataBaseDAO implements IArtistsDAO {

	private ComboPooledDataSource dataSource;

	private final String getAll = "SELECT artist_id, name_artist  " +
			                      "FROM app.artist;" ;

	private final String getOne = "SELECT artist_id, name_artist " +
			                      "FROM app.artist " +
			                      "WHERE name_artist LIKE ?;" ;

	private final String insertPos = "INSERT INTO app.artist (name_artist) " +
			                         "VALUES (?);" ;

	private final String deletePos = "DELETE FROM app.artist " +
			                         "WHERE name_artist LIKE ?;" ;

	private final String updatePos = "UPDATE app.artist " +
			                         "SET name_artist = ? " +
			                         "WHERE name_artist LIKE ?;" ;

	public ArtistsDataBaseDAO(){
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
	public List<Artist> getAll() {
		List<Artist> list = new ArrayList<>();
		try(Connection connection = dataSource.getConnection();
		    PreparedStatement stmt = connection.prepareStatement(getAll);
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
		    PreparedStatement stmt = connection.prepareStatement(getOne);){

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
		    PreparedStatement stmt = connection.prepareStatement(insertPos)) {

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
		    PreparedStatement stmt = connection.prepareStatement(deletePos)) {

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
		    PreparedStatement stmt = connection.prepareStatement(updatePos)) {

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
