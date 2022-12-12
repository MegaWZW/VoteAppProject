package com.course.app.dto;

import com.course.app.core.Artist;

import java.util.List;

public class ArtistsDTO {
	private List<String> artistNames;

	public ArtistsDTO (List<String> artists) {
		this.artistNames = artists;
	}

	public List<String> getArtists() {
		return artistNames;
	}
}
