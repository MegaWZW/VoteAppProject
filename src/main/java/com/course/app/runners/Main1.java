package com.course.app.runners;

import java.sql.*;

public class Main1 {
	public static void main(String[] args) {
		try {
			Class.forName("org.postgresql.Driver");
		} catch (ClassNotFoundException e) {
			throw new RuntimeException(e);
		}
		try(Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/VoteApp",
				"postgres", "arsenal1577980");
		    Statement stmt = connection.createStatement();
		    ResultSet resSet = stmt.executeQuery("SELECT genre_id, name_genre " +
													    "FROM app.genre " +
													    "ORDER BY genre_id;");) {
			while(resSet.next()){
				long id = resSet.getLong("genre_id");
				String genre = resSet.getString("name_genre");
				System.out.println(id + ": " + genre);
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}

	}
}
