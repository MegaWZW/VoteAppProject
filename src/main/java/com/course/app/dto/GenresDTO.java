package com.course.app.dto;

import java.util.ArrayList;
import java.util.List;

public class GenresDTO {
	private List<GenreDTO> genres;

	public GenresDTO(List<GenreDTO> genres) {
		this.genres = genres;
	}

	public GenresDTO() {
		genres = new ArrayList<>();
	}

	public List<GenreDTO> getAll() {
		return genres;
	}

	public void add (GenreDTO genre) {
		genres.add(genre);
	}
}
