package com.course.app.dto;

import java.util.List;

public class GenresDTO {
	private List<String> genres;

	public GenresDTO (List<String> genres) {
		this.genres = genres;
	}

	public List<String> getGenres() {
		return genres;
	}
}
