package com.course.app.dao.db.orm.hibernate.factories;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class EntityManagerFactorySingleton {
	private volatile static EntityManagerFactory instance;

	private EntityManagerFactorySingleton(){

	}

	public static EntityManagerFactory getInstance() {
		if(instance == null) {
			synchronized (EntityManagerFactorySingleton.class) {
				if (instance == null) {
					instance = Persistence.createEntityManagerFactory("voteapp");
				}
			}
		}
		return instance;
	}
}
