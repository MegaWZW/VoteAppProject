package com.course.app.services;

import com.course.app.dao.api.IVotesDAO;
import com.course.app.dto.GenreDTO;
import com.course.app.dto.VoteDTO;
import com.course.app.services.api.IArtistService;
import com.course.app.services.api.IGenreService;
import com.course.app.services.api.IVoteService;

import java.util.List;
import java.util.Set;

public class VoteService implements IVoteService {

	private final IVotesDAO votesDAO;
	private final IArtistService artistService;
	private final IGenreService genreService;
	//private final INotificationService notificationService = null;

	public VoteService(IVotesDAO votesDAO, IArtistService artistService, IGenreService genreService) {
		this.votesDAO = votesDAO;
		this.artistService = artistService;
		this.genreService = genreService;
	}

	@Override
	public void save(VoteDTO dto) {
		validate(dto);
		votesDAO.save(dto);
		//notificationService.send("message info");
	}

	@Override
	public void validate(VoteDTO dto) {
		if(!checkIfInstanceVariablesNotNull(dto)){
			throw new NullPointerException("Все поля в голосе должны быть заполнены");
		}
		if(!checkIfIsBlank(dto.getAbout())){
			throw new IllegalStateException("Поле в голосе \"о себе\" должно быть заполнено");
		}
		if(!checkForProperAmountOfChoices(dto)) {
			throw new IllegalStateException("В голосе количество выбранных жанров должно быть от 3 до 5");
		}
		if(!checkForProperArtist(dto)) {
			throw new IllegalStateException("Голос содержит имя артиста, который не участвует в голосовании");
		}
		if(!checkForProperGenres(dto)){
			throw new IllegalStateException("В голосе содержится нзвание жанра(ов), которых нет в списке для голосования");
		}
	}

	@Override
	public List<VoteDTO> getAllVotes() {
		return votesDAO.getAll();
	}

	//	private boolean hasRepeatedElements (String[] toCheck) {
//		Arrays.sort(toCheck);
//		for (int i = 0; i < toCheck.length - 1; i++) {
//			if (toCheck[i].equals(toCheck[i + 1])) {
//				return true;
//			}
//		}
//		return false;
//	}

	private boolean checkForProperArtist(VoteDTO dto) {
		return artistService.isExist(dto.getArtist().getId());
	}

	private boolean checkForProperGenres (VoteDTO dto) {
		Set<GenreDTO> setGenres = dto.getGenres();
		for(GenreDTO genre : setGenres) {
			if(!genreService.isExist(genre.getId())){
				return false;
			}
		}
		return true;
	}

	private boolean checkForProperAmountOfChoices(VoteDTO dto) {
		Set<GenreDTO> setGenres = dto.getGenres();
		int size = setGenres.size();
		return size <= 5 && size >= 3;
	}

	private boolean checkIfInstanceVariablesNotNull(VoteDTO dto) {
		return dto.getId() != null && dto.getArtist() != null
				&& dto.getGenres() != null && dto.getAbout() != null && dto.getDtCreate() != null;
	}

	private boolean checkIfIsBlank (String toCheck){
		return !toCheck.isBlank();
	}
}
