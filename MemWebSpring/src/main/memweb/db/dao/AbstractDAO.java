package db.dao;

import javax.naming.InitialContext;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

import db.dao.impl.PacienteImpl;

public abstract class AbstractDAO {

	// @Autowired
	// private SessionFactory sessionFactory;
	private static Log log;

	@Autowired
	private final SessionFactory sessionFactory = getSessionFactory();

	protected SessionFactory getSessionFactory() {
		// return sessionFactory.getCurrentSession();

		try {
			return (SessionFactory) new InitialContext()
					.lookup("SessionFactory");
		} catch (Exception e) {
			log.error("Could not locate SessionFactory in JNDI", e);
			throw new IllegalStateException(
					"Could not locate SessionFactory in JNDI");
		}
	}

	public void persist(Object entity) {
		// getSession().persist(entity);

		log.debug("persisting entity instance");
		try {
			sessionFactory.getCurrentSession().persist(entity);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void delete(Object entity) {
		//getSession().delete(entity);

		log.debug("delete entity instance");
		try {
			sessionFactory.getCurrentSession().delete(entity);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}
	
	public Log getLog(){
		return log;
	}
	
	protected void setLog(Log log) {
		this.log = log; 
	}

}
