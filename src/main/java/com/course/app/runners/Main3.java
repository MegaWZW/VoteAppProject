package com.course.app.runners;

import com.course.app.core.Artist;
import com.course.app.dao.ArtistDBDao;

import java.util.List;

public class Main3 {
	public static void main(String[] args) {

		try {
			Class.forName("org.postgresql.Driver");
		} catch (ClassNotFoundException e) {
			throw new RuntimeException(e);
		}

		ArtistDBDao dbDao = new ArtistDBDao();

		List<Artist> list =  dbDao.getData();
		for(Artist artist : list) {
			System.out.println(artist.getName());
		}
	}
}
