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
			throw new IllegalArgumentException("Передана пустая ссылка на объект ArtistDTO");
		}
		genres.add(genre);
	}

	@Override
	public void updatePosition(String toDelete, String toAdd) {
		if(toDelete == null || toAdd == null) {
			throw new IllegalArgumentException("Передана пустая ссылка на объект(ы)");
		}
		if(isExist(toDelete)) {
			throw new NoSuchElementException("Артиста, имя которого Вы хотите изменить, нет в списке");
		}
		for(GenreDTO genre : genres) {
			if(genre.getName().equals(toDelete)) {
				genre.setName(toAdd);
				break;
			}
		}
	}

	@Override
	public void deletePosition(GenreDTO genre) {
		if(genre == null) {
			throw new IllegalArgumentException("Передана пустая ссылка на объект GenreDTO");
		}
		for(GenreDTO item : genres) {
			if(item.getName().equals(genre.getName())) {
				genres.remove(item);
				break;
			}
		}
	}

	@Override
	public GenreDTO getOne(String name_genre) {
		for(GenreDTO genre : genres) {
			if(genre.getName().equals(name_genre)) {
				return genre;
			}
		}
		throw new NoSuchElementException("Такого жанра нет в голосовании");
	}


	@Override
	public boolean isExist(String name_genre) {
		for(GenreDTO genre : genres) {
			if(genre.getName().equals(name_genre)) {
				return true;
			}
		}
		return false;
	}
}
