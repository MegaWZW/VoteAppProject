package com.course.app.dto;

import com.course.app.core.Genre;

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
