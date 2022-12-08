package com.course.app.controllers;

import com.course.app.VotesStorage;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

@WebServlet(name = "ResultPrinterServlet", urlPatterns = "/result")
public class ResultPrinterServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request,
	                     HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html; charset=utf-8");
		request.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();

		VotesStorage votesStorage = VotesStorage.getInstance();

		Map<String, Integer> artistMap = votesStorage.getArtistsMap();
		List<Map.Entry<String, Integer>> artistList = new ArrayList<>(artistMap.entrySet());
		Collections.sort(artistList, new Comparator<Map.Entry<String, Integer>>() {
			@Override
			public int compare(Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2) {
				return o2.getValue() - o1.getValue();
			}
		});

		Map<String, Integer> genreMap = votesStorage.getGenresMap();
		List<Map.Entry<String, Integer>> genreList = new ArrayList<>(genreMap.entrySet());
		Collections.sort(genreList, new Comparator<Map.Entry<String, Integer>>() {
			@Override
			public int compare(Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2) {
				return o2.getValue() - o1.getValue();
			}
		});

		Map<String, String> textMap = votesStorage.getTextMap();
		List<String> listDate = new ArrayList<>();
		for (String dt : textMap.keySet()) {
			listDate.add(dt);
		}
		Collections.sort(listDate);


		/*
		print result
		 */
		for(Map.Entry<String, Integer> artist : artistList) {
			out.write("<p>" + artist.getKey() + " ---------- " + artist.getValue() + " points</p>");
		}
		out.write("<p>*****************************</p>");

		for(Map.Entry<String, Integer> genre : genreList) {
			out.write("<p>" + genre.getKey() + " ---------- " + genre.getValue() + " points</p>");
		}
		out.write("<p>*****************************</p>");


		for(String date : listDate) {
			out.write("<p>" + date + ": " + textMap.get(date) + "</p>");
		}
	}
}
