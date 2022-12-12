package com.course.app.controllers;

import com.course.app.core.Result;
import com.course.app.dto.VoteDTO;
import com.course.app.services.api.IStatisticService;
import com.course.app.services.api.IVoteService;
import com.course.app.services.factories.StatisticServiceMemorySingleton;
import com.course.app.services.factories.VoteServiceMemorySingleton;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Сервлет для обработки голосов
 */
@WebServlet(name = "VoiceHandlerServlet", urlPatterns = "/vote")
public class VoiceHandlerServlet extends HttpServlet {

	private static final String PARAMETER_ARTIST = "artist";
	private static final String PARAMETER_GENRE = "genre";
	private static final String TEXT = "text";

	private final IVoteService service;

	public VoiceHandlerServlet(){
		this.service = VoteServiceMemorySingleton.getInstance();
	}

	protected void doPost (HttpServletRequest request,
	                       HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=utf-8");

		String[] artists = request.getParameterValues(PARAMETER_ARTIST);
		String[] genres = request.getParameterValues(PARAMETER_GENRE);
		String text = request.getParameter(TEXT);

		PrintWriter out = response.getWriter();

		VoteDTO dto = new VoteDTO(artists, genres, text);


		try {
			service.save(dto);
			out.write("<p><span style='color: green;'>Ваш голос засчитан!</span></p>");
		} catch (IllegalArgumentException e) {
			out.write(e.getMessage());
		}

		IStatisticService stat = StatisticServiceMemorySingleton.getInstance();
		stat.calculate();
	}
}
