package com.course.app;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class VotesStorage {
	private static VotesStorage instance;
	private Map<String, Integer> artistsMap = new HashMap<>();
	private Map<String, Integer> genresMap = new HashMap<>();
	private Map<String, String> textMap = new HashMap<>();

	private VotesStorage(){
		artistsMap.put("Adele",0);
		artistsMap.put("Billie Eilish",0);
		artistsMap.put("Taylor Swift",0);
		artistsMap.put("Ed Sheeran",0);

		genresMap.put("Pop",0);
		genresMap.put("Hip hop",0);
		genresMap.put("Rock",0);
		genresMap.put("Rhythm and blues",0);
		genresMap.put("Soul",0);
		genresMap.put("Reggae",0);
		genresMap.put("Country",0);
		genresMap.put("Funk",0);
		genresMap.put("Folk",0);
		genresMap.put("Jazz",0);
	};

	public static VotesStorage getInstance() {
		if (instance == null) {
			instance = new VotesStorage();
		}
		return instance;
	}

	public void acceptVote (Artists.UserChoice choice){
		Set<String> artists = choice.getArtists();
		Set<String> genres = choice.getGenres();
		String currentTime = choice.getCurrentTime();

		for (String artist : artists) {
			if (!artistsMap.containsKey(artist)){
				artistsMap.put(artist, 0);
			}else{
				incrementValue(artistsMap, artist);
			}
		}

		for (String genre : genres) {
			if (!genresMap.containsKey(genre)){
				genresMap.put(genre, 0);
			}else{
				incrementValue(genresMap, genre);
			}
		}

		textMap.put(currentTime, choice.getText());
	}

	public Map<String, Integer> getArtistsMap() {
		return artistsMap;
	}

	public Map<String, Integer> getGenresMap() {
		return genresMap;
	}

	public Map<String, String> getTextMap() {
		return textMap;
	}

	private static void incrementValue (Map<String, Integer> map, String key) {
		Integer count = map.get(key);
		if(count == null){
			map.put(key, 1);
		}else {
			map.put(key, count + 1);
		}
	}
}
