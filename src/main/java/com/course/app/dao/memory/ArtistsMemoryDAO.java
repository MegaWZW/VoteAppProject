package com.course.app.dao.memory;

import com.course.app.dao.api.IArtistsDAO;
import com.course.app.dto.ArtistDTO;

import java.util.List;
import java.util.NoSuchElementException;

public class ArtistsMemoryDAO implements IArtistsDAO {
	private List<ArtistDTO> artists;

	public ArtistsMemoryDAO(List<ArtistDTO> artists) {
		this.artists = artists;
	}


	@Override
	public List<ArtistDTO> getAll() {
		return artists;
	}

	@Override
	public ArtistDTO getOne(Long id) {
		for(ArtistDTO artist : artists) {
			if(artist.getId().equals(id)) {
				return artist;
			}
		}
		throw new NoSuchElementException("Такой артист не принимает участи в голосовании");
	}

	@Override
	public void addPosition(ArtistDTO artist) {
		if(artist == null) {
			throw new IllegalArgumentException("Передана пустая ссылка на объект ArtistDTO");
		}
		artists.add(artist);
	}

	@Override
	public void deletePosition(Long id) {
		if(!isExist(id)) {
			throw new NoSuchElementException("Артиста, которого Вы хотите удалить нет в голосовании");
		}
		for(ArtistDTO item : artists) {
			if(item.getId().equals(id)) {
				artists.remove(item);
				break;
			}
		}
	}

	@Override
	public void updatePosition(ArtistDTO artist) {
		if(artist == null) {
			throw new IllegalArgumentException("Передана пустая ссылка на объект(ы)");
		}
		if(!isExist(artist.getId())) {
			throw new NoSuchElementException("Артиста, имя которого Вы хотите изменить, нет в списке");
		}
		for(ArtistDTO item : artists) {
			if(item.getId().equals(artist.getId())) {
				item.setName(artist.getName());
				break;
			}
		}
	}

	@Override
	public boolean isExist(Long id) {
		for(ArtistDTO artist : artists) {
			if(artist.getId().equals(id)) {
				return true;
			}
		}
		return false;
	}
}