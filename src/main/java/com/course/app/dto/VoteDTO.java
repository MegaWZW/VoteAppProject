package com.course.app.dto;

import java.time.LocalDateTime;
import java.util.Set;

public class VoteDTO {
	private Long id;
	private ArtistDTO artist;
	private Set<GenreDTO> genres;
	private LocalDateTime dtCreate;
	private String about;

	public VoteDTO(Long id, ArtistDTO artist, Set<GenreDTO> genres, LocalDateTime dtCreate, String about) {
		this.id = id;
		this.artist = artist;
		this.genres = genres;
		this.dtCreate = dtCreate;
		this.about = about;
	}

	public VoteDTO(Long id, ArtistDTO artist, Set<GenreDTO> genres,String about) {
		this.id = id;
		this.artist = artist;
		this.genres = genres;
		this.dtCreate = LocalDateTime.now();
		this.about = about;
	}

	public VoteDTO(ArtistDTO artist, Set<GenreDTO> genres, String about) {
		this.id = 1L;
		this.artist = artist;
		this.genres = genres;
		this.dtCreate = LocalDateTime.now();
		this.about = about;
	}

	public ArtistDTO getArtist() {
		return artist;
	}

	public void setArtist(ArtistDTO artist) {
		this.artist = artist;
	}

	public Set<GenreDTO> getGenres() {
		return genres;
	}

	public void setGenres(Set<GenreDTO> genres) {
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

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getArtistId() {
		return artist.getId();
	}

	public Long[] getGenresId() {
		int i = 0;
		Long[] ids = new Long[genres.size()];
		for(GenreDTO genre : genres) {
			ids[i++] = genre.getId();
		}
		return ids;
	}
}
