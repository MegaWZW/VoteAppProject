package com.course.app.web.controllers;

import com.course.app.dto.ArtistDTO;
import com.course.app.services.api.IArtistService;
import com.course.app.services.factories.ArtistServiceSingleton;
import com.course.app.web.util.RequestParameterHandler;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

/**
 * Сервлет для вывода информации об исполнителях
 */

@WebServlet(name = "ArtistInfoServlet", urlPatterns = "/artists")
public class ArtistServlet extends HttpServlet {

    private final IArtistService artistService;

    public ArtistServlet() {
        this.artistService = ArtistServiceSingleton.getInstance();
    }

    protected void doGet (HttpServletRequest request,
                          HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html; charset=utf-8");
        request.setCharacterEncoding("UTF-8");
        PrintWriter writer = response.getWriter();
        List<ArtistDTO> listArtist = artistService.getAll();
        writer.write("<h1><span style='color: green;'>List of artists(choose one ID):</span></h1>");

        for (ArtistDTO artist : listArtist){
            writer.write("<p><span style='color: green;'>" + "id: " + artist.getId() + " - " + artist.getName() + "</span></p>");
        }

        writer.write("<p></p>");
        writer.write("Добавьте небольшой текст о себе при отправке голоса");
    }

    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response) throws IOException, ServletException {
        response.setContentType("text/html; charset=utf-8");
        request.setCharacterEncoding("UTF-8");
        PrintWriter writer = response.getWriter();

        String artist = RequestParameterHandler.getParameterFromRequest(request, RequestParameterHandler.ARTIST_PARAM);
        ArtistDTO dto = new ArtistDTO(artist);
        artistService.addPosition(dto);
        writer.write("<p><span style='color: green;'>" + "Артист " + dto.getName() + " успешно добавлен в голосование" + "</span></p>");
    }

    protected void doPut(HttpServletRequest request,
                         HttpServletResponse response) throws IOException, ServletException {
        response.setContentType("text/html; charset=utf-8");
        request.setCharacterEncoding("UTF-8");
        PrintWriter writer = response.getWriter();

        String newName = RequestParameterHandler.getParameterFromRequest(request, RequestParameterHandler.ARTIST_PARAM);
        String id = RequestParameterHandler.getParameterFromRequest(request, RequestParameterHandler.ID_PARAM);

        ArtistDTO dto = new ArtistDTO(RequestParameterHandler.convertIdToLong(id), newName);
        artistService.updatePosition(dto);
        writer.write("<p><span style='color: green;'>" + "Артист успешно изменён" + "</span></p>");
    }

    protected void doDelete(HttpServletRequest request,
                            HttpServletResponse response) throws IOException, ServletException {
        response.setContentType("text/html; charset=utf-8");
        request.setCharacterEncoding("UTF-8");
        PrintWriter writer = response.getWriter();

        String id = RequestParameterHandler.getParameterFromRequest(request, RequestParameterHandler.ID_PARAM);
        artistService.deletePosition(RequestParameterHandler.convertIdToLong(id));
        writer.write("<p><span style='color: green;'>" + "Артист успешно удалён" + "</span></p>");
    }
}
