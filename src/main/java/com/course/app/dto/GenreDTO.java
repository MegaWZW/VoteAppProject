package com.course.app.dto;

import com.course.app.dao.db.orm.entity.Genre;

import java.util.Objects;

public class GenreDTO {
	private Long id;
	private String name;

	public GenreDTO(Long id, String name) {
		this.id = id;
		this.name = name;
	}

	public GenreDTO(String name){
		this.id = 0L;
		this.name = name;
	}

	public GenreDTO(Genre genreEntity) {
		this.id = genreEntity.getId();
		this.name = genreEntity.getName();
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
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		GenreDTO genreDTO = (GenreDTO) o;
		return Objects.equals(id, genreDTO.id) && Objects.equals(name, genreDTO.name);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, name);
	}

	@Override
	public String toString() {
		return "GenreDTO{" +
				"id=" + id +
				", name='" + name + '\'' +
				'}';
	}
}
