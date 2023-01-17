package com.course.app.services;

import com.course.app.core.*;
import com.course.app.dao.api.IArtistsDAO;
import com.course.app.dao.api.IGenresDAO;
import com.course.app.dao.factories.ArtistsDAOMemorySingleton;
import com.course.app.dao.factories.GenresDAOMemorySingleton;
import com.course.app.services.api.IStatisticService;
import com.course.app.services.api.IVoteService;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Класс, предназначенны для подсчёта статистики
 */
public class StatisticService implements IStatisticService {

	private IVoteService voteService;
	private IArtistsDAO artistDao;
	private IGenresDAO genreDao;

	public StatisticService(IVoteService voteService, IArtistsDAO artistDao, IGenresDAO genreDao) {
		this.voteService = voteService;
		this.artistDao = artistDao;
		this.genreDao = genreDao;
	}

	/**
	 * Метод, подсчитывающий резултаты гоолосования
	 * @return объект, содержащий результаты голосования
	 */
	@Override
	public Result calculate(){
		for(Artist art : ArtistsDAOMemorySingleton.getInstance().getData()){
			art.setPoints(0);
		}

		for(Genre gen : GenresDAOMemorySingleton.getInstance().getData()){
			gen.setPoints(0);
		}

		List<Vote> votes = voteService.getDao().getData();
		List<Artist> artists = artistDao.getData();
		List<Genre> genres = genreDao.getData();
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
