package com.course.app.core;

import java.util.List;
import java.util.Map;

public class Result {
	private final Map<String, Integer> artistsMap;
	private final Map<String, Integer> genresMap;
	private final List<Message> messages;

	public Result(Map<String, Integer> artistsMap, Map<String, Integer> genresMap, List<Message> messages) {
		this.artistsMap = artistsMap;
		this.genresMap = genresMap;
		this.messages = messages;
	}

	public Map<String, Integer> getArtistsMap() {
		return artistsMap;
	}

	public Map<String, Integer> getGenresMap() {
		return genresMap;
	}

	public List<Message> getMessages() {
		return messages;
	}
}
