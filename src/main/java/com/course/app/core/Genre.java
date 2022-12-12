package com.course.app.core;

import java.util.Objects;

public class Genre implements Comparable<Genre>{
	private String name;
	private int points;
	private int id;

	public Genre(String name, int points, int id) {
		this.name = name;
		this.points = points;
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getPoints() {
		return points;
	}

	public void setPoints(int points) {
		this.points = points;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@Override
	public int compareTo(Genre o) {
		return o.getPoints() - this.points;
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
