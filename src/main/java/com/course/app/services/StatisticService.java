package com.course.app.services;

import com.course.app.dao.api.IArtistsDAO;
import com.course.app.dao.api.IGenresDAO;
import com.course.app.dao.factories.ArtistsDAOMemorySingleton;
import com.course.app.dao.factories.GenresDAOMemorySingleton;
import com.course.app.dao.factories.VotesDAOMemorySingleton;
import com.course.app.services.api.IStatisticService;
import com.course.app.services.api.IVoteService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Класс, предназначенны для подсчёта статистики
 */
public class StatisticService implements IStatisticService {

	private IVoteService voteService;

	public StatisticService(IVoteService voteService){
		this.voteService = voteService;
	}

	/**
	 * Метод, подсчитывающий резултаты гоолосования
	 * @return объект, содержащий результаты голосования
	 */
	@Override
	public Result calculate(){
		/*
		Создание 3-ёх Мар для хранения результатов голосования
		 */
		Map<String, Integer> artistsMap = new HashMap<>();
		Map<String, Integer> genresMap = new HashMap<>();
		Map<String, String> messageMap = new HashMap<>();

		//получение объектов, хранящих имена исполнителей и музыкальные жанры
		IArtistsDAO artDao = ArtistsDAOMemorySingleton.getInstance();
		IGenresDAO genresDAO = GenresDAOMemorySingleton.getInstance();

		/*
		заполнение Мар исполнителей, ключ - имя исполнителя, значение 0 - по умолчанию
		 */
		for(String artist : artDao.getData()) {
			artistsMap.put(artist, 0);
		}

		/*
		заполнение Мар муз. жанров, ключ - название жанра, значение 0 - по умолчанию
		 */
		for(String genre : genresDAO.getData()) {
			genresMap.put(genre, 0);
		}

		//получение списка объектов типа Голос
		List<Vote> votesList = VotesDAOMemorySingleton.getInstance().getData();

		/**
		 * Итерация по списку Голосов, извлечение информации, заполенение результатов голосования
		 */
		for(Vote vote : votesList) {
			incrementValue(artistsMap, vote.getArtist());
			for(String genre : vote.getGenres()){
				incrementValue(genresMap, genre);
			}
			messageMap.put(vote.getAcceptanceTime(), vote.getMessage());
		}

		return new Result(artistsMap, genresMap, messageMap);
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
