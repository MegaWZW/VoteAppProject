package com.course.app.dao;

import com.course.app.core.Genre;
import com.course.app.dao.api.IGenresDAO;
import com.mchange.v2.c3p0.ComboPooledDataSource;

import java.beans.PropertyVetoException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

public class GenreDataBaseDAO implements IGenresDAO {

	private ComboPooledDataSource dataSource;

	private final String getAll = "SELECT genre_id, name_genre  " +
			                      "FROM app.genre;" ;

	private final String getOne = "SELECT genre_id, name_genre " +
			                      "FROM app.genre " +
			                      "WHERE name_genre LIKE ?;" ;

	private final String insertPos = "INSERT INTO app.genre (name_genre) " +
			                         "VALUES (?);" ;

	private final String deletePos = "DELETE FROM app.genre " +
			                         "WHERE name_genre LIKE ?;" ;

	private final String updatePos = "UPDATE app.genre " +
			                         "SET name_genre = ? " +
			                         "WHERE name_genre LIKE ?;" ;

	public GenreDataBaseDAO(){
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
	public List<Genre> getAll() {
		List<Genre> list = new ArrayList<>();
		try(Connection connection = dataSource.getConnection();
		    PreparedStatement stmt = connection.prepareStatement(getAll);
		    ResultSet resSet = stmt.executeQuery();){
			while (resSet.next()){
				String name = resSet.getString("name_genre");
				long id = resSet.getLong("genre_id");
				list.add(new Genre(name, id));
			}
		}catch (SQLException e){
			throw new RuntimeException(e);
		}
		return list;
	}

	@Override
	public Genre getOne(String name_genre) {
		if(!isExist(name_genre)){
			throw new NoSuchElementException("Такого жанра нет в голосовании");
		}
		Genre genre = null;

		try(Connection connection = dataSource.getConnection();
		    PreparedStatement stmt = connection.prepareStatement(getOne);){

			stmt.setString(1, name_genre);
			try(ResultSet resSet = stmt.executeQuery()){
				while (resSet.next()){
					genre = new Genre(resSet.getString("name_genre"), resSet.getLong("genre_id"));
				}
			}catch (SQLException e){
				throw new RuntimeException(e);
			}

		}catch (SQLException e){
			throw new RuntimeException(e);
		}
		return genre;
	}

	@Override
	public void addPosition(Genre genre) {
		if(isExist(genre.getName())){
			throw new IllegalArgumentException("Такой жанр уже участвует в голосовании");
		}

		try(Connection connection = dataSource.getConnection();
		    PreparedStatement stmt = connection.prepareStatement(insertPos)) {

			connection.setAutoCommit(false);
			stmt.setString(1, genre.getName());
			stmt.executeUpdate();
			connection.commit();

		}catch (SQLException e){
			throw new RuntimeException(e);
		}
	}

	@Override
	public void deletePosition(Genre genre) {
		try(Connection connection = dataSource.getConnection();
		    PreparedStatement stmt = connection.prepareStatement(deletePos)) {

			connection.setAutoCommit(false);
			stmt.setString(1, genre.getName());
			stmt.executeUpdate();
			connection.commit();

		}catch (SQLException e){
			throw new RuntimeException(e);
		}
	}

	@Override
	public void updatePosition(Genre toDelete, Genre toAdd) {
		if(!isExist(toDelete.getName())){
			throw new NoSuchElementException("Изменяемого жанра не существует");
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
		List<Genre> all = getAll();
		for (Genre genre : all) {
			if(genre.getName().equals(name)){
				return true;
			}
		}
		return false;
	}
}
