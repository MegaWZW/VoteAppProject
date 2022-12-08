package com.course.app.dao.api;

import java.util.List;

public interface IGenresDAO {
	List<String> getData();
	void addPosition(String genreName);
	void deletePosition(String genreName);
}
