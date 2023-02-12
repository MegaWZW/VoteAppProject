package com.course.app.dao.db;

import com.course.app.dao.api.IGenresDAO;
import com.course.app.dao.db.orm.entity.Genre;
import com.course.app.dto.GenreDTO;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
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
			cq.select(genreRoot);
			List<Genre> artistEntities = em.createQuery(cq).getResultList();

			for(Genre entity : artistEntities) {
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
	public GenreDTO getOne(Long id) {
		if (!isExist(id)) {
			throw new NoSuchElementException("Жанра с таким id не существует");
		}
		EntityManager entityManager = factory.createEntityManager();
		try{
			entityManager.getTransaction().begin();
			Genre entity = entityManager.find(Genre.class, id);
			if(entity == null) {
				return null;
			}else {
				return new GenreDTO(entity);
			}
		}catch (Exception e) {
			throw new RuntimeException(e);
		}finally {
			entityManager.close();
		}

	}

	@Override
	public void addPosition(GenreDTO genre) {
		if(isNameExist(genre.getName())){
			throw new IllegalArgumentException("Такой жанр уже участвует в голосовании");
		}
		Genre entity = new Genre(genre);
		EntityManager em = factory.createEntityManager();
		try {
			em.getTransaction().begin();
			em.merge(entity);
			em.getTransaction().commit();
		}catch (Exception e) {
			em.getTransaction().rollback();
			throw new RuntimeException(e);
		}finally {
			em.close();
		}
	}

	@Override
	public void deletePosition(Long id) {
		if(!isExist(id)) {
			throw new NoSuchElementException("Жанра, который Вы хотите удалить, нет в БД");
		}
		EntityManager em = factory.createEntityManager();
		Genre entity = em.find(Genre.class, id);
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
	public void updatePosition(GenreDTO dto) {
		if(!isExist(dto.getId())){
			throw new NoSuchElementException("Изменяемого жанра не существует");
		}
		EntityManager em = factory.createEntityManager();
		Genre entity = new Genre(dto);
		try {
			em.getTransaction().begin();
			em.merge(entity);
			em.getTransaction().commit();

		}catch (Exception e) {
			em.getTransaction().rollback();
			throw new RuntimeException(e);
		}finally {
			em.close();
		}
	}

	@Override
	public boolean isExist(Long id) {
		EntityManager em = factory.createEntityManager();
		try{
			CriteriaBuilder cb = em.getCriteriaBuilder();
			CriteriaQuery<Genre> cq = cb.createQuery(Genre.class);
			Root<Genre> genreRoot = cq.from(Genre.class);
			cq.select(genreRoot).where(cb.equal(genreRoot.get("id"), id));
			List<Genre> genreEntities = em.createQuery(cq).getResultList();
			int size = genreEntities.size();
			return size != 0;

		}catch(Exception e) {
			throw new RuntimeException(e);
		}finally {
			em.close();
		}
	}

	private boolean isNameExist(String name) {
		EntityManager em = factory.createEntityManager();
		try{
			CriteriaBuilder cb = em.getCriteriaBuilder();
			CriteriaQuery<Genre> cq = cb.createQuery(Genre.class);
			Root<Genre> genreRoot = cq.from(Genre.class);
			cq.select(genreRoot).where(cb.like(genreRoot.get("name"), name));
			List<Genre> genreEntities = em.createQuery(cq).getResultList();
			int size = genreEntities.size();
			return size != 0;

		}catch(Exception e) {
			throw new RuntimeException(e);
		}finally {
			em.close();
		}
	}
}
