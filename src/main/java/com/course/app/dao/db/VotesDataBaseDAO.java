package com.course.app.dao.db;

import com.course.app.dao.api.IVotesDAO;
import com.course.app.dao.db.ds.api.IDataSourceWrapper;
import com.course.app.dao.db.orm.entity.Genre;
import com.course.app.dao.db.orm.entity.Vote;
import com.course.app.dto.ArtistDTO;
import com.course.app.dto.GenreDTO;
import com.course.app.dto.VoteDTO;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.sql.*;
import java.sql.Date;
import java.time.LocalDateTime;
import java.util.*;

public class VotesDataBaseDAO implements IVotesDAO {

	private EntityManagerFactory factory;

	public VotesDataBaseDAO(EntityManagerFactory factory){
		this.factory = factory;
	}

	@Override
	public List<VoteDTO> getAll() {
		List<VoteDTO> list = new ArrayList<>();
		EntityManager em = factory.createEntityManager();
		try{
			CriteriaBuilder cb = em.getCriteriaBuilder();
			CriteriaQuery<Vote> cq = cb.createQuery(Vote.class);
			Root<Vote> votesRoot = cq.from(Vote.class);
			CriteriaQuery<Vote> allVotes = cq.select(votesRoot);
			TypedQuery<Vote> allQuery = em.createQuery(allVotes);
			List<Vote> voteEntities = allQuery.getResultList();

			for(Vote entity : voteEntities) {
				list.add(new VoteDTO(entity));
			}
		}catch(Exception e) {
			throw new RuntimeException(e);
		}finally {
			em.close();
		}
		return list;
	}

	@Override
	public void save(VoteDTO voteDTO) {
		Vote entity = new Vote(voteDTO);
		EntityManager em = factory.createEntityManager();
		try{
			em.getTransaction().begin();
			em.persist(entity);
			em.getTransaction().commit();
		} catch (Exception e) {
			em.getTransaction().rollback();
			System.out.println(e.getMessage());
			throw  new RuntimeException(e);
		} finally {
			em.close();
		}
	}
}
