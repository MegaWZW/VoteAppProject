package com.course.app.services;

import com.course.app.core.*;
import com.course.app.dao.api.IArtistsDAO;
import com.course.app.dao.api.IGenresDAO;
import com.course.app.dao.memory.factroies.ArtistsMemoryDAOSingleton;
import com.course.app.dao.memory.factroies.GenresMemoryDAOSingleton;
import com.course.app.services.api.IStatisticService;
import com.course.app.services.api.IVoteService;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


public class StatisticService implements IStatisticService {

	private IVoteService voteService;
	private IArtistsDAO artistDao;
	private IGenresDAO genreDao;

	public StatisticService(IVoteService voteService, IArtistsDAO artistDao, IGenresDAO genreDao) {
		this.voteService = voteService;
		this.artistDao = artistDao;
		this.genreDao = genreDao;
	}

	@Override
	public Result calculate(){
		for(Artist art : ArtistsMemoryDAOSingleton.getInstance().getAll()){
			art.setPoints(0);
		}

		for(Genre gen : GenresMemoryDAOSingleton.getInstance().getAll()){
			gen.setPoints(0);
		}

		List<Vote> votes = voteService.getDao().getAll();
		List<Artist> artists = artistDao.getAll();
		List<Genre> genres = genreDao.getAll();
		List<Message> messages = new ArrayList<>();

		for(Vote vote : votes) {
			for (Artist artist : artists) {
				if (vote.getArtist().equals(artist.getName())) {
					artist.incrementPoints();
				}
			}

			String[] gens = vote.getGenres();
			for(String gen : gens) {
				for(Genre genre : genres) {
					if (gen.equals(genre.getName())){
						genre.incrementPoints();
					}
				}
			}

			messages.add(vote.getMessage());
		}
		return new Result(artists, genres, messages);
	}

	private static void incrementValue (Map<String, Integer> map, String key) {
		Integer count = map.get(key);
		if(count == null){
			map.put(key, 1);
		}else {
			map.put(key, count + 1);
		}
	}
}
