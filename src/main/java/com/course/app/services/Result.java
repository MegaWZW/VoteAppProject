package com.course.app.services;

import java.util.Map;

/**
 * Класс для объектов, содержащих информацию о результатах голосования
 * Результаты храняться в HashMap'ах
 */
public class Result {
	private Map<String, Integer> artistsMap;
	private Map<String, Integer> genresMap;
	private Map<String, String> messageMap;

	public Result(Map<String, Integer> artistsMap, Map<String, Integer> genresMap, Map<String, String> messageMap) {
		this.artistsMap = artistsMap;
		this.genresMap = genresMap;
		this.messageMap = messageMap;
	}

	public Map<String, Integer> getArtistsMap() {
		return artistsMap;
	}

	public Map<String, Integer> getGenresMap() {
		return genresMap;
	}

	public Map<String, String> getMessageMap() {
		return messageMap;
	}
}
