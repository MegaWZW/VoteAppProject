package com.course.app.dto;

public class VoteDTO {
	private String[] artists;
	private String[] genres;
	private String text;

	public VoteDTO(String[] artists, String[] genres, String text){
		this.artists = artists;
		this.genres = genres;
		this.text = text;
	}

	public String[] getArtists() {
		return artists;
	}

	public String[] getGenres() {
		return genres;
	}

	public String getText() {
		return text;
	}
}
