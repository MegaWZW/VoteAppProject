package com.course.app.services;

import com.course.app.dto.VoteDTO;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Objects;

public class Vote {
	private String artist;
	private String[] genres;
	private String message;
	private String acceptanceTime;
	private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

	public Vote (VoteDTO dto) {
		this.artist = dto.getArtists()[0];
		this.genres = dto.getGenres();
		this.message = dto.getText();
		this.acceptanceTime = LocalDateTime.now().format(formatter);
	}

	public String getArtist() {
		return artist;
	}

	public String[] getGenres() {
		return genres;
	}

	public String getMessage() {
		return message;
	}

	public String getAcceptanceTime() {
		return acceptanceTime;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Vote vote = (Vote) o;
		return Objects.equals(artist, vote.artist) && Arrays.equals(genres, vote.genres)
				&& Objects.equals(message, vote.message) && Objects.equals(acceptanceTime, vote.acceptanceTime);
	}

	@Override
	public int hashCode() {
		int result = Objects.hash(artist, message, acceptanceTime);
		result = 31 * result + Arrays.hashCode(genres);
		return result;
	}
}
