package com.course.app.dao.db;

import com.course.app.dao.api.IGenresDAO;
import com.course.app.dao.db.orm.entity.Genre;
import com.course.app.dto.GenreDTO;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

public class GenreDataBaseDAO implements IGenresDAO {

	private final EntityManagerFactory factory;

	public GenreDataBaseDAO(EntityManagerFactory factory){
		this.factory = factory;
	}

	@Override
	public List<GenreDTO> getAll() {
		List<GenreDTO> list = new ArrayList<>();
		EntityManager em = factory.createEntityManager();
		try{
			CriteriaBuilder cb = em.getCriteriaBuilder();
			CriteriaQuery<Genre> cq = cb.createQuery(Genre.class);
			Root<Genre> genreRoot = cq.from(Genre.class);
			CriteriaQuery<Genre> allGenres = cq.select(genreRoot);
			TypedQuery<Genre> allQuery = em.createQuery(allGenres);
			List<Genre> genreEntities = allQuery.getResultList();

			for(Genre entity : genreEntities) {
				list.add(new GenreDTO(entity));
			}
		}catch(Exception e) {
			throw new RuntimeException(e);
		}finally {
			em.close();
		}
		return list;
	}

	@Override
	public GenreDTO getOne(String name_genre) {
		if(!isExist(name_genre)){
			throw new NoSuchElementException("Такой артист не принимает участие в голосовании");
		}

		List<GenreDTO> listGenres = getAll();
		for(GenreDTO item : listGenres) {
			if(item.getName().equals(name_genre)){
				return item;
			}
		}
		return null;
	}

	@Override
	public void addPosition(GenreDTO genre) {
		if(isExist(genre.getName())){
			throw new IllegalArgumentException("Жанр с таким названием уже участвует в голосовании");
		}
		Genre entity = new Genre(genre);
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
	public void deletePosition(GenreDTO genre) {
		if(!isExist(genre.getName())){
			throw new NoSuchElementException("Жанра, который Вы хотите удалить, не в БД");
		}
		EntityManager em = factory.createEntityManager();
		Genre entity = em.find(Genre.class, genre.getId());
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
			throw new NoSuchElementException("Изменяемого жанра не существует");
		}
		EntityManager em = factory.createEntityManager();
		List<Genre> list = null;
		Genre toUpdate = null;
		try {
			CriteriaBuilder cb = em.getCriteriaBuilder();
			CriteriaQuery<Genre> cq = cb.createQuery(Genre.class);
			Root<Genre> genreRoot = cq.from(Genre.class);
			CriteriaQuery<Genre> allGenres = cq.select(genreRoot);
			TypedQuery<Genre> allQuery = em.createQuery(allGenres);
			list = allQuery.getResultList();

			for (Genre entity : list) {
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
		List<GenreDTO> all = getAll();
		for (GenreDTO genre : all) {
			if(genre.getName().equals(name)){
				return true;
			}
		}
		return false;
	}
}
