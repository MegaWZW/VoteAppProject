package com.course.app.services;

import com.course.app.core.*;
import com.course.app.dao.api.IArtistsDAO;
import com.course.app.dao.api.IGenresDAO;
import com.course.app.dao.api.IVotesDAO;
import com.course.app.dao.factories.ArtistsDAOMemorySingleton;
import com.course.app.dao.factories.GenresDAOMemorySingleton;
import com.course.app.dto.VoteDTO;
import com.course.app.services.api.IVoteService;

import java.util.*;

public class VoteService implements IVoteService {

	private final IVotesDAO dao;

	public VoteService (IVotesDAO dao) {
		this.dao = dao;
	}

	/**
	 * Попытка сохранения DTO
	 * @param dto объект DTO
	 */
	@Override
	public void save(VoteDTO dto) {
		Vote vote = this.validate(dto);
		dao.save(vote);
	}

	/**
	 * Валидация полученного голоса
	 * @param dto объект DTO
	 * @return объект типа Голос
	 * @throws IllegalArgumentException в случае неверно переданых параметров
	 */
	@Override
	public Vote validate(VoteDTO dto) throws IllegalArgumentException {

		int artistsLen = dto.getArtists().length;
		int genresLen = dto.getGenres().length;
		int textLen = dto.getText().length();

		if(dto.getArtists() == null || artistsLen != 1) {
			throw new IllegalArgumentException("Указано неверное количество исполнителей");
		}

		if(dto.getGenres() == null || genresLen < 3 || genresLen > 5) {
			throw new IllegalArgumentException("Указано неверное количетво жанров");
		}

		if(dto.getText() == null || textLen == 0){
			throw new IllegalArgumentException("Укажите информацию о себе");
		}

		if (hasRepeatedElements(dto.getArtists()) | hasRepeatedElements(dto.getGenres())) {
			throw new IllegalArgumentException("Ваш голос сожержит повторяющиеся элементы");
		}

		if (!hasProperGenres(dto.getGenres()) || !hasProperArtist(dto.getArtists())) {
			throw new IllegalArgumentException("Таких жанров/исполнителей нет в списке для голосования");
		}

		return new Vote(dto);
	}

	/**
	 * Сортировка результатов голосования
	 * @param result объект, хранящий результат подсчёта голосов
	 * @return объект, хранящий результат подсчёта голосов в отсортированном виде
	 */
	@Override
	public void sort(Result result) {
		Collections.sort(result.getArtists());
		Collections.sort(result.getGenres());
		Collections.sort(result.getMessages());
	}

	public IVotesDAO getDao() {
		return dao;
	}

	/**
	 * Проверка, содержит ли массив строк поаторяющиеся элементы
	 * @param toCheck массив строк, переданный дял проверки
	 * @return
	 */
	private static boolean hasRepeatedElements (String[] toCheck) {
		Arrays.sort(toCheck);
		for (int i = 0; i < toCheck.length - 1; i++) {
			if (toCheck[i].equals(toCheck[i + 1])) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Метод, осуществляющий проверку соответствия переданных исполнителей в качестве параметров
	 * списку исполнителей, доступных для выбора на голосовании
	 * @param artists массив переданных имён артистов
	 * @return true - если исполнители соответствуют списку, false - не соответствуют
	 */
	private static boolean hasProperArtist (String[] artists) {
		IArtistsDAO dao = ArtistsDAOMemorySingleton.getInstance();

		List<String> namesList = new ArrayList<>();
		for(Artist art : dao.getData()) {
			namesList.add(art.getName());
		}

		for (int i = 0; i < artists.length; i++) {
			if (!namesList.contains(artists[i])){
				return false;
			}
		}
		return true;
	}

	/**
	 * Метод, осуществляющий проверку соответствия переданных муз.жанров в качестве параметров
	 * списку жанров, доступных для выбора на голосовании
	 * @param genres массив переданных муз.жанров
	 * @return true - если муз.жанры соответствуют списку, false - не соответствуют
	 */
	private static boolean hasProperGenres (String[] genres) {
		IGenresDAO dao = GenresDAOMemorySingleton.getInstance();
		List<String> namesList = new ArrayList<>();
		for(Genre gen : dao.getData()) {
			namesList.add(gen.getName());
		}

		for (int i = 0; i < genres.length; i++) {
			if (!namesList.contains(genres[i])){
				return false;
			}
		}
		return true;
	}
}
