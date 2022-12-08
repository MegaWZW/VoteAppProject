package com.course.app.dao.api;

import java.util.List;

public interface IArtistsDAO {
	List<String> getData();
	void addPosition(String artistName);
	void deletePosition(String artistName);
}
