package com.course.app.controllers;

import com.course.app.services.Result;
import com.course.app.services.SortedResult;
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

		//подсчёт результатов голосования и создание объекта Result
		Result res = stat.calculate();
		//сортировка результатов голосования
		SortedResult sortRes = service.sort(res);


		/*
		print result
		 */
		for(Map.Entry<String, Integer> artist : sortRes.getArtistsMap().entrySet()) {
			out.write("<p>" + artist.getKey() + " ---------- " + artist.getValue() + " points</p>");
		}
		out.write("<p>*****************************</p>");

		for(Map.Entry<String, Integer> genre : sortRes.getGenresMap().entrySet()) {
			out.write("<p>" + genre.getKey() + " ---------- " + genre.getValue() + " points</p>");
		}
		out.write("<p>*****************************</p>");


		for(Map.Entry<String, String> genre : sortRes.getMessageMap().entrySet()) {
			out.write("<p>" + genre.getKey() + ": " + genre.getValue());
		}
	}
}
