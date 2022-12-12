package com.course.app.controllers;

import com.course.app.core.*;
import com.course.app.dao.factories.ArtistsDAOMemorySingleton;
import com.course.app.dao.factories.GenresDAOMemorySingleton;
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
import java.util.Map;

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

		for(Artist art : ArtistsDAOMemorySingleton.getInstance().getData()){
			art.setPoints(0);
		}

		for(Genre gen : GenresDAOMemorySingleton.getInstance().getData()){
			gen.setPoints(0);
		}
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
