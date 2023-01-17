package com.course.app.controllers;

import com.course.app.core.Artist;
import com.course.app.core.Genre;
import com.course.app.core.Message;
import com.course.app.core.Result;
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
 * Сервлет для вывода результатов голосования
 */
@WebServlet(name = "ResultPrinterServlet", urlPatterns = "/result")
public class ResultPrinterServlet extends HttpServlet {

	private final IVoteService service;
	private final IStatisticService stat;

	public ResultPrinterServlet(){
		this.service = VoteServiceMemorySingleton.getInstance();
		this.stat = StatisticServiceMemorySingleton.getInstance();
	}
	protected void doGet(HttpServletRequest request,
	                     HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html; charset=utf-8");
		request.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();


		//подсчёт результатов голосования и создание объекта Result
		Result res = stat.calculate();
		//сортировка результатов голосования
		service.sort(res);

		/*
		print result
		 */
		for(Artist artist : res.getArtists()) {
			out.write("<p>" + artist.getName() + " ---------- " + artist.getPoints() + " points</p>");

		}
		out.write("<p>*****************************</p>");

		for(Genre genre : res.getGenres()) {
			out.write("<p>" + genre.getName() + " ---------- " + genre.getPoints() + " points</p>");

		}
		out.write("<p>*****************************</p>");


		for(Message message : res.getMessages()) {
			out.write("<p>" + message.getTime() + ": " + message.getText());
		}
	}
}
