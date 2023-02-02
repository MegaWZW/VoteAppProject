package com.course.app.dto;

import java.util.List;

public class VoteDTO {
	private String artist;
	private List<String> genres;
	private String text;

	public VoteDTO(String artist, List<String> genres, String text){
		this.artist = artist;
		this.genres = genres;
		this.text = text;
	}

	public String getArtist() {
		return artist;
	}

	public List<String> getGenres() {
		return genres;
	}

	public String getText() {
		return text;
	}
}
