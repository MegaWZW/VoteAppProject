package com.course.app.dto;

import java.util.List;

public class GenresDTO {
	private List<String> genreNames;

	public GenresDTO (List<String> genres) {
		this.genreNames = genres;
	}

	public List<String> getGenres() {
		return genreNames;
	}
}
