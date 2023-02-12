package com.course.app.dao.memory;

import com.course.app.dao.api.IGenresDAO;
import com.course.app.dto.ArtistDTO;
import com.course.app.dto.GenreDTO;

import java.util.List;
import java.util.NoSuchElementException;

public class GenresMemoryDAO implements IGenresDAO {
	private List<GenreDTO> genres;

	public GenresMemoryDAO(List<GenreDTO> genres) {
		this.genres = genres;
	}

	@Override
	public List<GenreDTO> getAll() {
		return genres;
	}

	@Override
	public void addPosition(GenreDTO genre) {
		if(genre == null) {
			throw new IllegalArgumentException("Передана пустая ссылка на объект GenreDTO");
		}
		genres.add(genre);
	}

	@Override
	public void updatePosition(GenreDTO genre) {
		if(genre == null) {
			throw new IllegalArgumentException("Передана пустая ссылка на объект(ы)");
		}
		if(!isExist(genre.getId())) {
			throw new NoSuchElementException("Жанра, название которого Вы хотите изменить, нет в списке для голосования");
		}
		for(GenreDTO item : genres) {
			if(item.getId().equals(genre.getId())) {
				genre.setName(genre.getName());
				break;
			}
		}
	}

	@Override
	public void deletePosition(Long id) {
		if(!isExist(id)) {
			throw new NoSuchElementException("Жанра, который Вы хотите удалить, нет в списке для голосования");
		}
		for(GenreDTO item : genres) {
			if(item.getId().equals(id)) {
				genres.remove(item);
				break;
			}
		}
	}

	@Override
	public GenreDTO getOne(Long id) {
		for(GenreDTO genre : genres) {
			if(genre.getId().equals(id)) {
				return genre;
			}
		}
		throw new NoSuchElementException("Такого жанра нет в голосовании");
	}


	@Override
	public boolean isExist(Long id) {
		for(GenreDTO genre : genres) {
			if(genre.getId().equals(id)) {
				return true;
			}
		}
		return false;
	}
}
