package com.course.app.controllers;

import com.course.app.Genres;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Сервляет для вывода информации о жанрах
 */
@WebServlet(name="GenreInfoServlet", urlPatterns = "/genres")
public class GenreInfoServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response) throws ServletException, IOException {

        response.setContentType("text/html; charset=utf-8");
        request.setCharacterEncoding("UTF-8");
        PrintWriter writer = response.getWriter();

        Genres gen = Genres.getInstance();

        writer.write("<p><span style='color: blue;'>List of genres(choose from 3 to 5):</span></p>");

        for (String genre : gen.getGenres()) {
            writer.write("<p><span style='color: blue;'>" + genre + "</span></p>");
        }
        writer.write("<p></p>");

        writer.write("Add short text about you:");
    }
}
