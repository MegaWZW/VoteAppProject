package com.course.app.web.controllers;

import com.course.app.dto.GenreDTO;
import com.course.app.services.api.IGenreService;
import com.course.app.services.factories.GenreServiceSingleton;
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
 * Сервлет для вывода информации о жанрах
 */
@WebServlet(name="GenreInfoServlet", urlPatterns = "/genres")
public class GenreServlet extends HttpServlet {
    private final IGenreService genreService;

    public GenreServlet() {
        this.genreService = GenreServiceSingleton.getInstance();
    }

    protected void doGet (HttpServletRequest request,
                          HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html; charset=utf-8");
        request.setCharacterEncoding("UTF-8");
        PrintWriter writer = response.getWriter();
        List<GenreDTO> listGenres = genreService.getAll();
        writer.write("<h1><span style='color: green;'>List of genres(choose 3 to 5 ID):</span></h1>");

        for (GenreDTO genre : listGenres){
            writer.write("<p><span style='color: green;'>" + "id: " + genre.getId() + " - " + genre.getName() + "</span></p>");
        }

        writer.write("<p></p>");
        writer.write("Добавьте небольшой текст о себе при отправке голоса");
    }

    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response) throws IOException, ServletException {
        response.setContentType("text/html; charset=utf-8");
        request.setCharacterEncoding("UTF-8");
        PrintWriter writer = response.getWriter();

        String genre = RequestParameterHandler.getParameterFromRequest(request, RequestParameterHandler.GENRE_PARAM);
        GenreDTO dto = new GenreDTO(genre);
        genreService.addPosition(dto);
        writer.write("<p><span style='color: green;'>" + "Жанр " + dto.getName() + " успешно добавлен в голосование" + "</span></p>");
    }

    protected void doPut(HttpServletRequest request,
                         HttpServletResponse response) throws IOException, ServletException {
        response.setContentType("text/html; charset=utf-8");
        request.setCharacterEncoding("UTF-8");
        PrintWriter writer = response.getWriter();

        String newName = RequestParameterHandler.getParameterFromRequest(request, RequestParameterHandler.GENRE_PARAM);
        String id = RequestParameterHandler.getParameterFromRequest(request, RequestParameterHandler.ID_PARAM);

        GenreDTO dto = new GenreDTO(RequestParameterHandler.convertIdToLong(id), newName);
        genreService.updatePosition(dto);
        writer.write("<p><span style='color: green;'>" + "Жанр успешно изменён" + "</span></p>");
    }

    protected void doDelete(HttpServletRequest request,
                            HttpServletResponse response) throws IOException, ServletException {
        response.setContentType("text/html; charset=utf-8");
        request.setCharacterEncoding("UTF-8");
        PrintWriter writer = response.getWriter();

        String id = RequestParameterHandler.getParameterFromRequest(request, RequestParameterHandler.ID_PARAM);
        genreService.deletePosition(RequestParameterHandler.convertIdToLong(id));
        writer.write("<p><span style='color: green;'>" + "Жанр успешно удалён" + "</span></p>");
    }
}
