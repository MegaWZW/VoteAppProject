package com.course.app.dao.db;

import com.course.app.dao.api.IGenresDAO;
import com.course.app.dao.db.ds.api.IDataSourceWrapper;
import com.course.app.dto.GenreDTO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

public class GenreDataBaseDAO implements IGenresDAO {

	private final IDataSourceWrapper dataSource;

	private final static String GET_ALL_ROWS = "SELECT id, name  " +
			                                   "FROM app.genre;" ;

	private final static String GET_ONE_ROW = "SELECT id, name " +
			                                  "FROM app.genre " +
			                                  "WHERE name LIKE ?;" ;

	private final static String  INSERT_ROW_INTO_TABLE = "INSERT INTO app.genre (name) " +
			                                             "VALUES (?);" ;

	private final static String DELETE_ROW_FROM_TABLE = "DELETE FROM app.genre " +
			                                            "WHERE name LIKE ?;" ;

	private final static String UPDATE_ROW = "UPDATE app.genre " +
			                                 "SET name = ? " +
			                                 "WHERE name LIKE ?;" ;

	public GenreDataBaseDAO(IDataSourceWrapper wrapper){
		this.dataSource = wrapper;
	}

	@Override
	public List<GenreDTO> getAll() {
		List<GenreDTO> list = new ArrayList<>();
		try(Connection connection = this.dataSource.getConnection();
		    PreparedStatement stmt = connection.prepareStatement(GET_ALL_ROWS);
		    ResultSet resSet = stmt.executeQuery();){
			while (resSet.next()){
				String name = resSet.getString("name");
				long id = resSet.getLong("id");
				list.add(new GenreDTO(id, name));
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
	public GenreDTO getOne(String name_genre) {
		if(!isExist(name_genre)){
			throw new NoSuchElementException("Такого жанра нет в голосовании");
		}
		GenreDTO genre = null;

		try(Connection connection = this.dataSource.getConnection();
		    PreparedStatement stmt = connection.prepareStatement(GET_ONE_ROW);){

			stmt.setString(1, name_genre);
			try(ResultSet resSet = stmt.executeQuery()){
				while (resSet.next()){
					genre = new GenreDTO(resSet.getLong("id"), resSet.getString("name"));
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
			try {
				dataSource.close();
			} catch (Exception ex) {
				throw new RuntimeException("Проблема с закрытием соединения");
			}
			throw new RuntimeException(e);
		}
		return genre;
	}

	@Override
	public void addPosition(GenreDTO genre) {
		if(isExist(genre.getName())){
			throw new IllegalArgumentException("Такой жанр уже участвует в голосовании");
		}

		try(Connection connection = this.dataSource.getConnection();
		    PreparedStatement stmt = connection.prepareStatement(INSERT_ROW_INTO_TABLE)) {

			connection.setAutoCommit(false);
			stmt.setString(1, genre.getName());
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
	public void deletePosition(GenreDTO genre) {
		try(Connection connection = this.dataSource.getConnection();
		    PreparedStatement stmt = connection.prepareStatement(DELETE_ROW_FROM_TABLE)) {

			connection.setAutoCommit(false);
			stmt.setString(1, genre.getName());
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
			throw new NoSuchElementException("Изменяемого жанра не существует");
		}

		try(Connection connection = this.dataSource.getConnection();
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
		List<GenreDTO> all = getAll();
		for (GenreDTO genre : all) {
			if(genre.getName().equals(name)){
				return true;
			}
		}
		return false;
	}
}
