package com.course.app.controllers;

import com.course.app.Artists;
import com.course.app.VotesStorage;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;


@WebServlet(name = "VoiceHandlerServlet", urlPatterns = "/vote")
public class VoiceHandlerServlet extends HttpServlet {

	private static final String PARAMETER_ARTIST = "artist";
	private static final String PARAMETER_GENRE = "genre";
	private static final String TEXT = "text";

	protected void doPost (HttpServletRequest request,
	                       HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=utf-8");

		String[] artists = request.getParameterValues("artist");
		String[] genres = request.getParameterValues("genre");
		String text = request.getParameter("text");
		PrintWriter out = response.getWriter();


		if(artists == null || artists.length != 1) {
			out.write("<p><span style='color: red;'>Выбрано неверное количество исполнителей</span></p>");
			out.write("<p/>");
			out.write("<p><span style='color: red;'>Ваш голос не засчитан</span></p>");
			out.write("<p/>");
			return;
		}

		if(genres == null || genres.length < 3 || genres.length > 5) {
			out.write("<p><span style='color: red;'>Выбрано неверное количество музыкальных жанров</span></p>");
			out.write("<p/>");
			out.write("<p><span style='color: red;'>Ваш голос не засчитан</span></p>");
			out.write("<p/>");
			return;
		}

		if(text == null || text.length() == 0){
			out.write("<p><span style='color: red;'>Напишите что-нибудь</span></p>");
			out.write("<p/>");
			out.write("<p><span style='color: red;'>Ваш голос не засчитан</span></p>");
			out.write("<p/>");
			return;
		}

		if (hasRepeatedElements(artists) | hasRepeatedElements(genres)) {
			out.write("<p><span style='color: red;'>Ваш голос содержит повторяющиеся элементы!</span></p>");
			out.write("<p/>");
			out.write("<p><span style='color: red;'>Ваш голос не засчитан</span></p>");
			out.write("<p/>");
			return;
		}

		Artists.UserChoice choice = new Artists.UserChoice(artists, genres, text);
		VotesStorage.getInstance().acceptVote(choice);
		out.write("<p><span style='color: green;'>Поздравляем, Ваш голос засчитан!</span></p>");

	}

	private static boolean hasRepeatedElements (String[] toCheck) {
		Arrays.sort(toCheck);
		for (int i = 0; i < toCheck.length - 1; i++) {
			if (toCheck[i].equals(toCheck[i + 1])) {
				return true;
			}
		}
		return false;
	}
}
