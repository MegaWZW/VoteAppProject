package com.course.app.dao.db;

import com.course.app.dao.api.IArtistsDAO;
import com.course.app.dao.db.orm.entity.Artist;
import com.course.app.dto.ArtistDTO;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
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
			cq.select(artistRoot);
			List<Artist> artistEntities = em.createQuery(cq).getResultList();

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
	public ArtistDTO getOne(Long id) {
		if (!isExist(id)) {
			throw new NoSuchElementException("Артиста с таким id не существует");
		}
		EntityManager entityManager = factory.createEntityManager();
		try{
			entityManager.getTransaction().begin();
			Artist entity = entityManager.find(Artist.class, id);
			if(entity == null) {
				return null;
			}else {
				return new ArtistDTO(entity);
			}
		}catch (Exception e) {
			throw new RuntimeException(e);
		}finally {
			entityManager.close();
		}

	}

	@Override
	public void addPosition(ArtistDTO artist) {
		if(isNameExist(artist.getName())){
			throw new IllegalArgumentException("Такой артист уже участвует в голосовании");
		}
		Artist entity = new Artist(artist);
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
			throw new NoSuchElementException("Артиста, которого Вы хотите удалить, нет в БД");
		}
		EntityManager em = factory.createEntityManager();
		Artist entity = em.find(Artist.class, id);
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
	public void updatePosition(ArtistDTO dto) {
		if(!isExist(dto.getId())){
			throw new NoSuchElementException("Изменяемого артиста не существует");
		}
		EntityManager em = factory.createEntityManager();
		Artist entity = new Artist(dto);
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
			CriteriaQuery<Artist> cq = cb.createQuery(Artist.class);
			Root<Artist> artistRoot = cq.from(Artist.class);
			cq.select(artistRoot).where(cb.equal(artistRoot.get("id"), id));
			List<Artist> artistEntities = em.createQuery(cq).getResultList();
			int size = artistEntities.size();
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
			CriteriaQuery<Artist> cq = cb.createQuery(Artist.class);
			Root<Artist> artistRoot = cq.from(Artist.class);
			cq.select(artistRoot).where(cb.like(artistRoot.get("name"), name));
			List<Artist> artistEntities = em.createQuery(cq).getResultList();
			int size = artistEntities.size();
			return size != 0;

		}catch(Exception e) {
			throw new RuntimeException(e);
		}finally {
			em.close();
		}
	}
}
