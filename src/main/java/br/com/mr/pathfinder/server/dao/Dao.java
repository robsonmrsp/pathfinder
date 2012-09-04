package br.com.mr.pathfinder.server.dao;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

@Named
public class Dao<Entity> {

	@Inject
	SessionFactory sessionFactory;

	protected Class<Entity> clazz;

	protected Session getSession() {
		return sessionFactory.getCurrentSession();
	}

	public Entity update(Entity entity) {
		getSession().update(entity);
		return entity;
	}

	public Entity save(Entity entity) {
		getSession().save(entity);
		return entity;
	}

	@SuppressWarnings("unchecked")
	public List<Entity> loadAll() {
		return criteria().list();
	}

	protected Criteria criteria() {
		return getSession().createCriteria(clazz);
	}
}
