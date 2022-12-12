package com.course.app.core;

import java.util.Objects;

public class Artist implements Comparable<Artist>{
	private String name;
	private int points;
	private int id;

	public Artist(String name, int points, int id) {
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

	public void incrementPoints(){
		this.points++;
	}


	@Override
	public int compareTo(Artist o) {
		return  o.getPoints() - this.points;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Artist artist = (Artist) o;
		return points == artist.points && id == artist.id && Objects.equals(name, artist.name);
	}

	@Override
	public int hashCode() {
		return Objects.hash(name, points, id);
	}
}
