package com.course.app.services;

import com.course.app.services.api.IStatisticService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class StatisticService implements IStatisticService {

	private VoteService voteService;

	public StatisticService(VoteService voteService){
		this.voteService = voteService;
	}

	@Override
	public Result calculate(){
		Map<String, Integer> artistsMap = new HashMap<>();
		Map<String, Integer> genresMap = new HashMap<>();
		Map<String, String> messageMap = new HashMap<>();

		List<Vote> votesList = voteService.getDao().getData();

		for(Vote vote : votesList) {
			if (!artistsMap.containsKey(vote.getArtist())){
				artistsMap.put(vote.getArtist(), 1);
			}else{
				incrementValue(artistsMap, vote.getArtist());
			}

			for(String genre : vote.getGenres()){
				if (!genresMap.containsKey(genre)){
					genresMap.put(genre, 1);
				}else{
					incrementValue(genresMap, genre);
				}
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
