package com.course.app.web.controllers;

import com.course.app.dao.db.orm.entity.Genre;
import com.course.app.dto.ArtistDTO;
import com.course.app.dto.GenreDTO;
import com.course.app.dto.VoteDTO;
import com.course.app.services.api.IArtistService;
import com.course.app.services.api.IGenreService;
import com.course.app.services.api.IStatisticService;
import com.course.app.services.api.IVoteService;
import com.course.app.services.factories.ArtistServiceSingleton;
import com.course.app.services.factories.GenreServiceSingleton;
import com.course.app.services.factories.StatisticServiceSingleton;
import com.course.app.services.factories.VoteServiceSingleton;
import com.course.app.web.util.RequestParameterHandler;

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

	private final IVoteService voteService;
	private final IArtistService artistService;
	private final IGenreService genreService;

	public VoiceHandlerServlet(){
		this.voteService = VoteServiceSingleton.getInstance();
		this.artistService = ArtistServiceSingleton.getInstance();
		this.genreService = GenreServiceSingleton.getInstance();
	}

	protected void doPost (HttpServletRequest request,
	                       HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=utf-8");

		String artistId = request.getParameter(RequestParameterHandler.ARTIST_ID_PARAM);
		String[] genresId = request.getParameterValues(RequestParameterHandler.GENRE_ID_PARAM);
		String text = request.getParameter(RequestParameterHandler.TEXT_PARAM);

		ArtistDTO artistDTO = new ArtistDTO(RequestParameterHandler.convertIdToLong(artistId),
				artistService.getOne(RequestParameterHandler.convertIdToLong(artistId)).getName());

		Set<GenreDTO> setGenresDTO = new HashSet<>();
		for(String id : genresId) {
			GenreDTO genreDTO = new GenreDTO(RequestParameterHandler.convertIdToLong(id),
					genreService.getOne(RequestParameterHandler.convertIdToLong(id)).getName());
			setGenresDTO.add(genreDTO);
		}

		PrintWriter out = response.getWriter();

		VoteDTO dto = new VoteDTO(artistDTO, setGenresDTO, text);

		voteService.save(dto);
		out.write("<p><span style='color: green;'>Ваш голос засчитан!</span></p>");
	}
}
