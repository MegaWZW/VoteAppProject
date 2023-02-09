package com.course.app.web.controllers;

import com.course.app.core.Message;
import com.course.app.core.Result;
import com.course.app.services.api.IStatisticService;
import com.course.app.services.factories.StatisticServiceSingleton;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Сервлет для вывода результатов голосования
 */
@WebServlet(name = "ResultPrinterServlet", urlPatterns = "/result")
public class ResultPrinterServlet extends HttpServlet {

	private final IStatisticService stat;

	public ResultPrinterServlet(){
		this.stat = StatisticServiceSingleton.getInstance();
	}
	protected void doGet(HttpServletRequest request,
	                     HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html; charset=utf-8");
		request.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();

		//подсчёт результатов голосования и создание объекта Result
		Result res = stat.calculate();
		Map<String, Integer> artistsMap = res.getArtistsMap();
		Map<String, Integer> genresMap = res.getGenresMap();
		List<Message> messagesList = res.getMessages();

		/*
		print result
		 */
		Set<String> keySet = artistsMap.keySet();
		for(String key : keySet) {
			out.write("<p>" + key + " ---------- " + artistsMap.get(key) + " points</p>");
		}
		out.write("<p>*****************************</p>");

		keySet = genresMap.keySet();
		for(String key : keySet) {
			out.write("<p>" + key + " ---------- " + genresMap.get(key) + " points</p>");
		}

		out.write("<p>*****************************</p>");
		out.write("<br><br>");
		out.write("<h2>Messages:</h2>");out.write("<br>");


		for(Message message : messagesList) {
			out.write("<p>" + message.getTime() + ": " + message.getText());
		}
	}
}
