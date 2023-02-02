package com.course.app.services;

import com.course.app.core.*;
import com.course.app.dao.api.IArtistsDAO;
import com.course.app.dao.api.IGenresDAO;
import com.course.app.dao.api.IVotesDAO;
import com.course.app.dao.memory.factroies.ArtistsMemoryDAOSingleton;
import com.course.app.dao.memory.factroies.GenresMemoryDAOSingleton;
import com.course.app.dto.VoteDTO;
import com.course.app.services.api.INotificationService;
import com.course.app.services.api.IVoteService;

import java.util.*;

public class VoteService implements IVoteService {

	private final IVotesDAO dao;
	private final INotificationService notificationService = null;

	public VoteService (IVotesDAO dao) {
		this.dao = dao;
	}

	@Override
	public void save(VoteDTO dto) {
		Vote vote = this.validate(dto);
		dao.save(vote);
		notificationService.send("message info");
	}

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

	@Override
	public void sort(Result result) {
		Collections.sort(result.getArtists());
		Collections.sort(result.getGenres());
		Collections.sort(result.getMessages());
	}

	public IVotesDAO getDao() {
		return dao;
	}

	private static boolean hasRepeatedElements (String[] toCheck) {
		Arrays.sort(toCheck);
		for (int i = 0; i < toCheck.length - 1; i++) {
			if (toCheck[i].equals(toCheck[i + 1])) {
				return true;
			}
		}
		return false;
	}

	private static boolean hasProperArtist (String[] artists) {
		IArtistsDAO dao = ArtistsMemoryDAOSingleton.getInstance();

		List<String> namesList = new ArrayList<>();
		for(Artist art : dao.getAll()) {
			namesList.add(art.getName());
		}

		for (int i = 0; i < artists.length; i++) {
			if (!namesList.contains(artists[i])){
				return false;
			}
		}
		return true;
	}

	private static boolean hasProperGenres (String[] genres) {
		IGenresDAO dao = GenresMemoryDAOSingleton.getInstance();
		List<String> namesList = new ArrayList<>();
		for(Genre gen : dao.getAll()) {
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
