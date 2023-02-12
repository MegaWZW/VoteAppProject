package com.course.app.web.util;

import javax.servlet.http.HttpServletRequest;

public class RequestParameterHandler {
	public static final String ARTIST_PARAM = "artist";
	public static final String GENRE_PARAM = "genre";
	public static final String ID_PARAM = "id";
	public static final String TEXT_PARAM = "text";
	public static final String ARTIST_ID_PARAM = "artistId";
	public static final String GENRE_ID_PARAM = "genreId";

	public static String getParameterFromRequest(HttpServletRequest request, String name) {
		String[] params = request.getParameterValues(name);
		if(params == null || params.length == 0) {
			throw new IllegalArgumentException("Параметры не переданы");
		}
		if(params.length > 1) {
			throw new IllegalArgumentException("Должно быть передано не более одного параметра");
		}
		if(params[0] == null || params[0].isBlank()) {
			throw new IllegalArgumentException("Передан пустой параметр");
		}
		return params[0];
	}

	public static Long convertIdToLong(String id) {
		try {
			return Long.parseLong(id);
		}catch (NumberFormatException e) {
			throw new IllegalArgumentException("Передан некорректный параметр id");
		}
	}
}
