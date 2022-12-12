package com.course.app.controllers;

import com.course.app.dao.ArtistsDAO;
import com.course.app.dao.api.IArtistsDAO;
import com.course.app.dao.factories.ArtistsDAOMemorySingleton;
import com.course.app.dto.ArtistsDTO;
import com.course.app.services.api.IArtistService;
import com.course.app.services.factories.ArtistServiceMemorySingleton;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Сервлет для вывода информации об исполнителях
 */

@WebServlet(name = "ArtistInfoServlet", urlPatterns = "/artists")
public class ArtistInfoServlet extends HttpServlet {

    private final IArtistService artistService;

    public ArtistInfoServlet() {
        this.artistService = ArtistServiceMemorySingleton.getInstance();
    }

    protected void doGet (HttpServletRequest request,
                          HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html; charset=utf-8");
        request.setCharacterEncoding("UTF-8");
        PrintWriter writer = response.getWriter();

        PrintWriter out = response.getWriter();
        ArtistsDTO dto = artistService.getTransferObj();

        writer.write("<p><span style='color: green;'>List of artists(choose one):</span></p>");

        for (String artist : dto.getArtists()){
            writer.write("<p><span style='color: green;'>" + artist + "</span></p>");
        }

        writer.write("<p></p>");

        writer.write("Add short text about you:");
    }
}
