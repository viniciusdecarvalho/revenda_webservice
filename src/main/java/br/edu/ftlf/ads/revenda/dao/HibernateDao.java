package br.edu.ftlf.ads.revenda.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Projections;

public class HibernateDao<T> {

	private Session session;
	private Class<?> type;

	private HibernateDao(Session session, Class<?> type) {
		this.session = session;
		this.type = type;
	}
	
	public Session getSession() {
		return session;
	}

	public Class<?> getType() {
		return type;
	}

	public void insert(T model) {
		session.persist(model);
	}
	
	public void update(T model) {
		session.merge(model);
	}
	
	public void save(T model) {
		session.saveOrUpdate(model);
	}

	public void delete(Integer id) {
		session.delete(get(id));
	}
	
	public void delete(T model) {
		session.delete(model);
	}

	@SuppressWarnings("unchecked")
	public List<T> listAll() {
		return getCachedCriteria().list();
	}
	
	@SuppressWarnings("unchecked")
	public List<T> listAll(int start, int limite) {
		return getCachedCriteria()
				.setFirstResult(start)
				.setMaxResults(limite)
				.list();
	}
	
	public Criteria getCriteria() {
		return session.createCriteria(type);
	}

	public Criteria getCachedCriteria() {
		return getCriteria().setCacheable(true);
	}
	
	public DetachedCriteria getDetachedCriteria() {
		return DetachedCriteria.forClass(type);
	}
	
	@SuppressWarnings("unchecked")
	public T load(Integer id) {
		return (T) session.load(type, id);
	}

	@SuppressWarnings("unchecked")
	public T get(Integer id) {
		return (T) session.get(type, id);
	}
	
	public void refresh(T entity) {
		session.refresh(entity);
	}

	public Long count() {
		return count(null, null);
	}
	
	public Long count(Criterion filter, String groupBy) {
		Criteria criteria = getCachedCriteria();
		if (filter != null) {
			criteria.add(filter);
		}
		if (groupBy != null && !groupBy.isEmpty()) {
			criteria.setProjection(Projections.groupProperty(groupBy));
		}
		return (Long)criteria.setProjection(Projections.rowCount()).uniqueResult();
	}

	public Query query(String hql, Object... parameters) {
		Query query = session.createQuery(hql).setCacheable(true);
		if (parameters != null) {
			for (int i = 0; i < parameters.length; i++) {
				query.setParameter(i, parameters[i]);
			}
		}
		return query;
	}
	
	public static <T> HibernateDao<T> create(Session session, Class<T> type) {
		return new HibernateDao<T>(session, type);
	}
	
}