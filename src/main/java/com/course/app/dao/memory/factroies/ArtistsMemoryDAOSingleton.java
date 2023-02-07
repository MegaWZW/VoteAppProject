package com.course.app.dao.memory.factroies;

import com.course.app.dao.api.IArtistsDAO;
import com.course.app.dao.db.entity.Artist;
import com.course.app.dao.memory.ArtistsMemoryDAO;
import com.course.app.dto.ArtistDTO;

import java.util.Arrays;
import java.util.List;

public class ArtistsMemoryDAOSingleton {
	private volatile static IArtistsDAO instance;

	private static List<ArtistDTO> artists = Arrays.asList(
			new ArtistDTO(1L, "Adele"),
			 new ArtistDTO(2L, "Billie Eilish"),
			new ArtistDTO(3L,"Taylor Swift"),
			new ArtistDTO(4L, "Ed Sheeran")
			);


	private ArtistsMemoryDAOSingleton(){};

	public static IArtistsDAO getInstance() {
		if(instance == null) {
			synchronized (ArtistsMemoryDAOSingleton.class) {
				if (instance == null) {
					instance = new ArtistsMemoryDAO(artists);
				}
			}
		}
		return instance;
	}
}
