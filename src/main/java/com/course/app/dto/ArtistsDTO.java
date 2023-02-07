package com.course.app.dto;

import java.util.ArrayList;
import java.util.List;

public class ArtistsDTO {
	private List<ArtistDTO> artists;

	public ArtistsDTO (List<ArtistDTO> artists) {
		this.artists = artists;
	}

	public ArtistsDTO () {
		artists = new ArrayList<>();
	}

	public List<ArtistDTO> getAll() {
		return artists;
	}

	public void add(ArtistDTO artist) {
		artists.add(artist);
	}

}
