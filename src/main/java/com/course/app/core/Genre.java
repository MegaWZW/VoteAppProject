package com.course.app.core;

import java.util.Objects;

public class Genre implements Comparable<Genre>{
	private String name;
	private long points;
	private long id;

	public Genre(String name, long points, long id) {
		this.name = name;
		this.points = points;
		this.id = id;
	}

	public Genre(String name, long id) {
		this.name = name;
		this.points = 0;
		this.id = id;
	}

	public Genre(String name) {
		this.name = name;
		this.points = 0;
		this.id = 0;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public long getPoints() {
		return points;
	}

	public void setPoints(int points) {
		this.points = points;
	}

	public long getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@Override
	public int compareTo(Genre o) {
		return 0;
	}

	public void incrementPoints(){
		this.points++;
	}
	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Genre genre = (Genre) o;
		return points == genre.points && id == genre.id && Objects.equals(name, genre.name);
	}

	@Override
	public int hashCode() {
		return Objects.hash(name, points, id);
	}
}
