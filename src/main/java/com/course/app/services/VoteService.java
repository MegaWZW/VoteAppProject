package com.course.app.services;

import com.course.app.dao.api.IArtistsDAO;
import com.course.app.dao.api.IGenresDAO;
import com.course.app.dao.api.IVotesDAO;
import com.course.app.dto.GenreDTO;
import com.course.app.dto.VoteDTO;
import com.course.app.services.api.IVoteService;

import java.util.List;
import java.util.Set;

public class VoteService implements IVoteService {

	private final IVotesDAO votesDAO;
	private final IArtistsDAO artistsDAO;
	private final IGenresDAO genresDAO;
	//private final INotificationService notificationService = null;

	public VoteService(IVotesDAO votesDAO, IArtistsDAO artistsDAO, IGenresDAO genresDAO) {
		this.votesDAO = votesDAO;
		this.artistsDAO = artistsDAO;
		this.genresDAO = genresDAO;
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

	@Override
	public IArtistsDAO getArtistsDAO() {
		return this.artistsDAO;
	}

	@Override
	public IGenresDAO getGenresDAO() {
		return this.genresDAO;
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
		return artistsDAO.isExist(dto.getArtist().getName());
	}

	private boolean checkForProperGenres (VoteDTO dto) {
		Set<GenreDTO> setGenres = dto.getGenres();
		for(GenreDTO genre : setGenres) {
			if(!genresDAO.isExist(genre.getName())){
				return false;
			}
		}
		return true;
	}

	private boolean checkForProperAmountOfChoices(VoteDTO dto) {
		Set<GenreDTO> setGenres = dto.getGenres();
		int size = setGenres.size();
		if(size > 5 || size < 3) {
			return false;
		}
		return true;
	}

	private boolean checkIfInstanceVariablesNotNull(VoteDTO dto) {
		if(dto.getId() == null || dto.getArtist() == null
		|| dto.getGenres() == null || dto.getAbout() == null || dto.getDtCreate() == null) {

			return false;
		}
		return true;
	}

	private boolean checkIfIsBlank (String toCheck){
		if(toCheck.isBlank()) {
			return false;
		}
		return true;
	}
}
