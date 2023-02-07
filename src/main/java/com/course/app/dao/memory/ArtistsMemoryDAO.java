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
	public ArtistDTO getOne(String name_artist) {
		for(ArtistDTO artist : artists) {
			if(artist.getName().equals(name_artist)) {
				return artist;
			}
		}
		throw new NoSuchElementException("Артист с таким именем не принимает участие в голосовании");
	}

	@Override
	public void addPosition(ArtistDTO artist) {
		if(artist == null) {
			throw new IllegalArgumentException("Передана пустая ссылка на объект ArtistDTO");
		}
		artists.add(artist);
	}

	@Override
	public void deletePosition(ArtistDTO artist) {
		if(artist == null) {
			throw new IllegalArgumentException("Передана пустая ссылка на объект ArtistDTO");
		}
		for(ArtistDTO item : artists) {
			if(item.getName().equals(artist.getName())) {
				artists.remove(item);
				break;
			}
		}
	}

	@Override
	public void updatePosition(String toDelete, String toAdd) {
		if(toDelete == null || toAdd == null) {
			throw new IllegalArgumentException("Передана пустая ссылка на объект(ы)");
		}
		if(isExist(toDelete)) {
			throw new NoSuchElementException("Артиста, имя которого Вы хотите изменить, нет в списке");
		}
		for(ArtistDTO artist : artists) {
			if(artist.getName().equals(toDelete)) {
				artist.setName(toAdd);
				break;
			}
		}
	}

	@Override
	public boolean isExist(String name) {
		for(ArtistDTO artist : artists) {
			if(artist.getName().equals(name)) {
				return true;
			}
		}
		return false;
	}
}