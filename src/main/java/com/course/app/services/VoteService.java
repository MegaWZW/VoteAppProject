package com.course.app.services;

import com.course.app.dao.ArtistsDAO;
import com.course.app.dao.GenresDAO;
import com.course.app.dao.api.IVotesDAO;
import com.course.app.dao.factories.ArtistsDAOMemorySingleton;
import com.course.app.dao.factories.GenresDAOMemorySingleton;
import com.course.app.dto.VoteDTO;
import com.course.app.services.api.IVoteService;

import java.util.Arrays;

public class VoteService implements IVoteService {

	private final IVotesDAO dao;

	public VoteService (IVotesDAO dao) {
		this.dao = dao;
	}

	@Override
	public void save(Vote vote) {
		dao.save(vote);
	}

	@Override
	public Vote validate(VoteDTO dto) {

		int artistsLen = dto.getArtists().length;
		int genresLen = dto.getGenres().length;
		int textLen = dto.getText().length();

		if(dto.getArtists() == null || artistsLen != 1) {
			return null;
		}

		if(dto.getGenres() == null || genresLen < 3 || genresLen > 5) {
			return null;
		}

		if(dto.getText() == null || textLen == 0){
			return null;
		}

		if (hasRepeatedElements(dto.getArtists()) | hasRepeatedElements(dto.getGenres())) {
			return null;
		}

	}

	@Override
	public void sort(Result result) {

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
		ArtistsDAO dao = ArtistsDAOMemorySingleton.getInstance();
		for(String artist : artists) {
			for(String comp : dao.getData()) {
				if(!artist.equals(comp)) {
					return false;
				}
			}
		}
		return true;
	}

	private static boolean hasProperGenres (String[] genres) {
		GenresDAO dao = GenresDAOMemorySingleton.getInstance();
		for(String genre : genres) {
			for(String comp : dao.getData()) {
				if(!genre.equals(comp)) {
					return false;
				}
			}
		}
		return true;
	}
}
