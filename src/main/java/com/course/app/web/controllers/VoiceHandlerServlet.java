package com.course.app.web.controllers;

import com.course.app.dto.ArtistDTO;
import com.course.app.dto.GenreDTO;
import com.course.app.dto.VoteDTO;
import com.course.app.services.api.IStatisticService;
import com.course.app.services.api.IVoteService;
import com.course.app.services.factories.StatisticServiceSingleton;
import com.course.app.services.factories.VoteServiceSingleton;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashSet;
import java.util.Set;

/**
 * Сервлет для обработки голосов
 */
@WebServlet(name = "VoiceHandlerServlet", urlPatterns = "/vote")
public class VoiceHandlerServlet extends HttpServlet {

	private static final String PARAMETER_ARTIST = "artist";
	private static final String PARAMETER_GENRE = "genre";
	private static final String TEXT = "text";

	private final IVoteService voteService;

	public VoiceHandlerServlet(){
		this.voteService = VoteServiceSingleton.getInstance();
	}

	protected void doPost (HttpServletRequest request,
	                       HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=utf-8");

		String artist = request.getParameter(PARAMETER_ARTIST);
		String[] genres = request.getParameterValues(PARAMETER_GENRE);
		String text = request.getParameter(TEXT);

		ArtistDTO artistDTO = voteService.getArtistsDAO().getOne(artist);
		Set<GenreDTO> setGenresDTO = new HashSet<>();
		for(String genre : genres) {
			GenreDTO genreDTO = voteService.getGenresDAO().getOne(genre);
			setGenresDTO.add(genreDTO);
		}

		PrintWriter out = response.getWriter();

		VoteDTO dto = new VoteDTO(artistDTO, setGenresDTO, text);


		try {
			voteService.save(dto);
			out.write("<p><span style='color: green;'>Ваш голос засчитан!</span></p>");
		} catch (IllegalArgumentException e) {
			out.write(e.getMessage());
		}

		IStatisticService stat = StatisticServiceSingleton.getInstance();
		stat.calculate();
	}
}
