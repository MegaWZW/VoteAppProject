package com.course.app.dao.db;

import com.course.app.dao.api.IArtistsDAO;
import com.course.app.dao.db.orm.entity.Artist;
import com.course.app.dto.ArtistDTO;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

public class ArtistsDataBaseDAO implements IArtistsDAO {

	private final EntityManagerFactory factory;

	public ArtistsDataBaseDAO(EntityManagerFactory factory){
		 this.factory = factory;
	}

	@Override
	public List<ArtistDTO> getAll() {
		List<ArtistDTO> list = new ArrayList<>();
		EntityManager em = factory.createEntityManager();
		try{
			CriteriaBuilder cb = em.getCriteriaBuilder();
			CriteriaQuery<Artist> cq = cb.createQuery(Artist.class);
			Root<Artist> artistRoot = cq.from(Artist.class);
			CriteriaQuery<Artist> allArtists = cq.select(artistRoot);
			TypedQuery<Artist> allQuery = em.createQuery(allArtists);
			List<Artist> artistEntities = allQuery.getResultList();

			for(Artist entity : artistEntities) {
				list.add(new ArtistDTO(entity));
			}
		}catch(Exception e) {
			throw new RuntimeException(e);
		}finally {
			em.close();
		}
		return list;
	}

	@Override
	public ArtistDTO getOne(String name_artist) {
		if(!isExist(name_artist)){
			throw new NoSuchElementException("Такой артист не принимает участие в голосовании");
		}

		List<ArtistDTO> listArtist = getAll();
		for(ArtistDTO item : listArtist) {
			if(item.getName().equals(name_artist)){
				return item;
			}
		}
		return null;
	}

	@Override
	public void addPosition(ArtistDTO artist) {
		if(isExist(artist.getName())){
			throw new IllegalArgumentException("Артист с таким именем уже участвует в голосовании");
		}
		Artist entity = new Artist(artist);
		EntityManager em = factory.createEntityManager();
		try {
			em.getTransaction().begin();
			em.persist(entity);
			em.getTransaction().commit();
		}catch (Exception e) {
			em.getTransaction().rollback();
			throw new RuntimeException(e);
		}finally {
			em.close();
		}
	}

	@Override
	public void deletePosition(ArtistDTO artist) {
		if(!isExist(artist.getName())){
			throw new NoSuchElementException("Артиста, которого Вы хотите удалить, не в БД");
		}
		EntityManager em = factory.createEntityManager();
		Artist entity = em.find(Artist.class, artist.getId());
		try{
			em.getTransaction().begin();
			em.remove(entity);
			em.getTransaction().commit();
		}catch (Exception e) {
			em.getTransaction().rollback();
			throw new RuntimeException(e);
		}finally {
			em.close();
		}
	}

	@Override
	public void updatePosition(String toDelete, String toAdd) {
		if(!isExist(toDelete)){
			throw new NoSuchElementException("Изменяемого артиста не существует");
		}
		EntityManager em = factory.createEntityManager();
		List<Artist> list = null;
		Artist toUpdate = null;
		try {
			CriteriaBuilder cb = em.getCriteriaBuilder();
			CriteriaQuery<Artist> cq = cb.createQuery(Artist.class);
			Root<Artist> artistRoot = cq.from(Artist.class);
			CriteriaQuery<Artist> allArtists = cq.select(artistRoot);
			TypedQuery<Artist> allQuery = em.createQuery(allArtists);
			list = allQuery.getResultList();

			for (Artist entity : list) {
				if (entity.getName().equals(toDelete)) {
					toUpdate = entity;
					break;
				}
			}
			if (toUpdate != null) {
				toUpdate.setName(toAdd);
			}
			em.getTransaction().begin();
			em.merge(toUpdate);
			em.getTransaction().commit();
		}catch (Exception e) {
			em.getTransaction().rollback();
			throw new RuntimeException(e);
		}finally {
			em.close();
		}
	}

	@Override
	public boolean isExist(String name) {
		List<ArtistDTO> all = getAll();
		for (ArtistDTO artist : all) {
			if(artist.getName().equals(name)){
				return true;
			}
		}
		return false;
	}
}
