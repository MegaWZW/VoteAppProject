package com.course.app.dao.db.entity;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

public class Vote implements Serializable {
	private Long id;
	private Artist artist;
	private List<Genre> genres;
	private LocalDateTime dtCreate;
	private String about;

	public Vote(Long id, Artist artist, List<Genre> genres, LocalDateTime dtCreate, String about) {
		this.id = id;
		this.artist = artist;
		this.genres = genres;
		this.dtCreate = dtCreate;
		this.about = about;
	}

	public Vote() {

	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Artist getArtist() {
		return artist;
	}

	public void setArtist(Artist artist) {
		this.artist = artist;
	}

	public List<Genre> getGenres() {
		return genres;
	}

	public void setGenres(List<Genre> genres) {
		this.genres = genres;
	}

	public LocalDateTime getDtCreate() {
		return dtCreate;
	}

	public void setDtCreate(LocalDateTime dtCreate) {
		this.dtCreate = dtCreate;
	}

	public String getAbout() {
		return about;
	}

	public void setAbout(String about) {
		this.about = about;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Vote vote = (Vote) o;
		return Objects.equals(id, vote.id) && Objects.equals(artist, vote.artist) &&
				Objects.equals(genres, vote.genres) &&
				Objects.equals(dtCreate, vote.dtCreate) && Objects.equals(about, vote.about);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, artist, genres, dtCreate, about);
	}

	@Override
	public String toString() {
		return "Vote{" +
				"id=" + id +
				", artist=" + artist +
				", genres=" + genres +
				", dtCreate=" + dtCreate +
				", about='" + about + '\'' +
				'}';
	}
}
