package com.course.app.core;

import java.util.List;
import java.util.Map;

/**
 * Класс для объектов, содержащих информацию о результатах голосования
 * Результаты храняться в HashMap'ах
 */
public class Result {
	private List<Artist> artists;
	private List<Genre> genres;
	private List<Message> messages;

	public Result(List<Artist> artists, List<Genre> genres, List<Message> messages) {
		this.artists = artists;
		this.genres = genres;
		this.messages = messages;
	}

	public List<Artist> getArtists() {
		return artists;
	}

	public List<Genre> getGenres() {
		return genres;
	}

	public List<Message> getMessages() {
		return messages;
	}
}
