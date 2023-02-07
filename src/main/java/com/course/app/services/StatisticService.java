package com.course.app.services;

import com.course.app.core.Message;
import com.course.app.core.Result;
import com.course.app.dto.GenreDTO;
import com.course.app.dto.VoteDTO;
import com.course.app.services.api.IStatisticService;
import com.course.app.services.api.IVoteService;

import java.util.*;


public class StatisticService implements IStatisticService {

	private IVoteService voteService;

	public StatisticService(IVoteService voteService) {
		this.voteService = voteService;
	}

	@Override
	public Result calculate(){
		Map<String, Integer> artistsMap = new HashMap<>();
		Map<String, Integer> genresMap = new HashMap<>();
		List<Message> messagesList= new ArrayList<>();

		List<VoteDTO> votes = voteService.getAllVotes();

		for(VoteDTO vote : votes) {
			if(!artistsMap.containsKey(vote.getArtist().getName())) {
				artistsMap.put(vote.getArtist().getName(), 1);
			} else {
				incrementValue(artistsMap, vote.getArtist().getName());
			}
			Set<GenreDTO> genres = vote.getGenres();
			for(GenreDTO genre : genres) {
				if(!genresMap.containsKey(genre.getName())) {
					genresMap.put(genre.getName(), 1);
				} else {
					incrementValue(genresMap, genre.getName());
				}
			}
			messagesList.add(new Message(vote.getAbout(), vote.getDtCreate()));
		}
		Map<String, Integer> sortedArtistsMap = sortMapByValueDesc(artistsMap);
		Map<String, Integer> sortedGenresMap = sortMapByValueDesc(genresMap);
		Collections.sort(messagesList);

		return new Result(sortedArtistsMap, sortedGenresMap, messagesList);
	}

	private void incrementValue (Map<String, Integer> map, String key) {
		Integer count = map.get(key);
		if(count == null){
			map.put(key, 1);
		}else {
			map.put(key, count + 1);
		}
	}

//	private void valuesToNull(Map<String, Integer> map) {
//		Set<String> keySet = map.keySet();
//		for(String key : keySet) {
//			map.put(key, null);
//		}
//	}

	private Map<String, Integer> sortMapByValueDesc(Map<String, Integer> map) {
		List<Map.Entry<String, Integer>> list = new ArrayList<>(map.entrySet());
		list.sort(Collections.reverseOrder(Map.Entry.comparingByValue()));

		Map<String, Integer> result = new LinkedHashMap<>();
		for (Map.Entry<String, Integer> entry : list) {
			result.put(entry.getKey(), entry.getValue());
		}
		return result;
	}
}
