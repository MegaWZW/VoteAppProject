package com.course.app.dao.factories;

import com.course.app.dao.ArtistsDAO;

import java.util.Arrays;
import java.util.List;

public class ArtistsDAOMemorySingleton {
	private volatile static ArtistsDAO instance;
	private static List<String> artists = Arrays.asList(
			"Adele",
			"Billie Eilish",
			"Taylor Swift",
			"Ed Sheeran");


	private ArtistsDAOMemorySingleton(){};

	public static ArtistsDAO getInstance() {
		if(instance == null) {
			synchronized (ArtistsDAOMemorySingleton.class) {
				if (instance == null) {
					instance = new ArtistsDAO(artists);
				}
			}
		}
		return instance;
	}
}
