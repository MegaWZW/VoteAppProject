package com.course.app.web.controllers;

import com.course.app.dto.ArtistDTO;
import com.course.app.dto.ArtistsDTO;
import com.course.app.services.api.IArtistService;
import com.course.app.services.factories.ArtistServiceSingleton;

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
        this.artistService = ArtistServiceSingleton.getInstance();
    }

    protected void doGet (HttpServletRequest request,
                          HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html; charset=utf-8");
        request.setCharacterEncoding("UTF-8");
        PrintWriter writer = response.getWriter();

        PrintWriter out = response.getWriter();
        ArtistsDTO dto = artistService.getTransferObj();

        writer.write("<p><span style='color: green;'>List of artists(choose one):</span></p>");

        for (ArtistDTO artist : dto.getAll()){
            writer.write("<p><span style='color: green;'>" + artist.getName() + "</span></p>");
        }

        writer.write("<p></p>");

        writer.write("Add short text about you:");
    }
}
