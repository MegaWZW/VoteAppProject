package com.course.app.dao;

import com.course.app.core.Artist;
import com.course.app.dao.api.IArtistsDAO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ArtistDBDao implements IArtistsDAO {
	@Override
	public List<Artist> getData() {
		List<Artist> list = new ArrayList<>();
		try(Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/base",
				"postgres", "103406709");
		    PreparedStatement stmt = connection.prepareStatement(
					"SELECT artist_id, name_artist " +
				    "FROM app.artist;");
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
	public void addPosition(Artist artist) {
		
	}

	@Override
	public void deletePosition(Artist artist) {

	}

	@Override
	public void update(Artist artist) {

	}

	@Override
	public Artist getArtist(String name_artist) {
		return null;
	}
}
