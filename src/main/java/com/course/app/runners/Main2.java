package com.course.app.runners;

import java.sql.*;

public class Main2 {
	public static void main(String[] args) {
		try {
			Class.forName("org.postgresql.Driver");
		} catch (ClassNotFoundException e) {
			throw new RuntimeException(e);
		}

		try(Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/VoteApp",
				"postgres", "arsenal1577980");
		    PreparedStatement stmt = connection.prepareStatement("SELECT artist_id, name_artist " +
				    "FROM app.artist " +
				    "ORDER BY name_artist;");
		    ResultSet resSet = stmt.executeQuery();){
			while (resSet.next()){
				long id = resSet.getLong("artist_id");
				String artist = resSet.getString("name_artist");
				System.out.println(id + ": " + artist);
			}
		}catch (SQLException e){
			throw new RuntimeException(e);
		}
	}
}
