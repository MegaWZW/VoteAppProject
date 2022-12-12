package com.course.app.core;

import com.course.app.dto.VoteDTO;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Objects;

/**
 * Класс объектов типа Голос
 * При создании данного объекта в конструкторе присваивается системное время создания объекта
 * Данный объект создаётся после валидации в VoteService
 */
public class Vote {
	private String artist;
	private String[] genres;
	private Message message;
	private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

	public Vote (VoteDTO dto) {
		this.artist = dto.getArtists()[0];
		this.genres = dto.getGenres();
		this.message = new Message(dto.getText(), LocalDateTime.now().format(formatter));

	}

	public String getArtist() {
		return artist;
	}

	public String[] getGenres() {
		return genres;
	}

	public Message getMessage() {
		return message;
	}

}
