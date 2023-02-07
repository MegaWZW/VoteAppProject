package com.course.app.dao.memory.factroies;

import com.course.app.dao.api.IGenresDAO;
import com.course.app.dao.memory.GenresMemoryDAO;
import com.course.app.dto.GenreDTO;

import java.util.Arrays;
import java.util.List;

public class GenresMemoryDAOSingleton {
	private volatile static IGenresDAO instance;

	private static List<GenreDTO> genres = Arrays.asList(
			new GenreDTO(1L, "Pop"),
			new GenreDTO(2L, "Hip hop"),
			new GenreDTO(3L, "Rock"),
			new GenreDTO(4L, "Rhythm and blues"),
			new GenreDTO(5L, "Soul"),
			new GenreDTO(6L, "Reggae"),
			new GenreDTO(7L, "Country"),
			new GenreDTO(8L, "Funk"),
			new GenreDTO(9L, "Folk"),
			new GenreDTO(10L, "Jazz")
			);

	private GenresMemoryDAOSingleton(){};

	public static IGenresDAO getInstance() {
		if(instance == null) {
			synchronized (GenresMemoryDAOSingleton.class) {
				if(instance == null){
					instance = new GenresMemoryDAO(genres);
				}
			}
		}
		return instance;
	}
}
