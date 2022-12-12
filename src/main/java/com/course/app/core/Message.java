package com.course.app.core;

public class Message implements Comparable<Message>{
	private String text;
	private String time;

	public Message(String text, String time) {
		this.text = text;
		this.time = time;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
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
