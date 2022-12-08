package com.course.app.dto;

import java.util.List;

public class ArtistsDTO {
	private List<String> artists;

	public ArtistsDTO (List<String> artists) {
		this.artists = artists;
	}

	public List<String> getArtists() {
		return artists;
	}
}
