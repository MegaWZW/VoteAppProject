package com.course.app.dto;

import com.course.app.dao.db.orm.entity.Artist;

public class ArtistDTO {
	private Long id;
	private String name;

	public ArtistDTO(Long id, String name) {
		this.id = id;
		this.name = name;
	}

	public ArtistDTO(String name) {
		this.id = 0L;
		this.name = name;
	}

	public ArtistDTO(Artist artistEntity) {
		this.id = artistEntity.getId();
		this.name = artistEntity.getName();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "ArtistDTO{" +
				"id=" + id +
				", name='" + name + '\'' +
				'}';
	}
}
