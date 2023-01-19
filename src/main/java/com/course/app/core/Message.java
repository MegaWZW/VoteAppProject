package com.course.app.core;

import java.time.LocalDateTime;

public class Message implements Comparable<Message>{
	private String text;
	private LocalDateTime time;

	public Message(String text, LocalDateTime time) {
		this.text = text;
		this.time = time;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public LocalDateTime getTime() {
		return time;
	}

	public void setTime(LocalDateTime time) {
		this.time = time;
	}

	@Override
	public int compareTo(Message o) {
		if (this.time.compareTo(o.getTime()) > 0){
			return 1;
		} else if (this.time.compareTo(o.getTime()) < 0) {
			return -1;
		}

		return 0;
	}
}
