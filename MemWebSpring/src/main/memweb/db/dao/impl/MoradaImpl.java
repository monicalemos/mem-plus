package db.dao.impl;
// default package
// Generated 10/fev/2015 20:01:08 by Hibernate Tools 4.3.1


import java.util.List;

import javax.naming.InitialContext;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Example;

import db.dao.AbstractDAO;
import db.dao.MoradaDAO;
import db.models.Morada;

/**
 * Home object for domain model class Morada.
 * @see .Morada
 * @author Hibernate Tools
 */
public class MoradaImpl extends AbstractDAO implements MoradaDAO{

//    private static final Log log = LogFactory.getLog(MoradaImpl.class);
//
//    private final SessionFactory sessionFactory = getSessionFactory();
	
    SessionFactory sessionFactory = getSessionFactory();
    Log log;
    
    public MoradaImpl() {
    	setLog(LogFactory.getLog(MoradaImpl.class));
    	log = getLog();
	}
    
    @Override
    public void delete(Morada persistentInstance) {
        log.debug("deleting Morada instance");
        try {
            sessionFactory.getCurrentSession().delete(persistentInstance);
            log.debug("delete successful");
        }
        catch (RuntimeException re) {
            log.error("delete failed", re);
            throw re;
        }
    }
    
    @Override
    public Morada findById( int id) {
        log.debug("getting Morada instance with id: " + id);
        try {
            Morada instance = (Morada) sessionFactory.getCurrentSession()
                    .get("Morada", id);
            if (instance==null) {
                log.debug("get successful, no instance found");
            }
            else {
                log.debug("get successful, instance found");
            }
            return instance;
        }
        catch (RuntimeException re) {
            log.error("get failed", re);
            throw re;
        }
    }
    
    @Override
    public List findByExample(Morada instance) {
        log.debug("finding Morada instance by example");
        try {
            List results = sessionFactory.getCurrentSession()
                    .createCriteria("Morada")
                    .add(Example.create(instance))
            .list();
            log.debug("find by example successful, result size: " + results.size());
            return results;
        }
        catch (RuntimeException re) {
            log.error("find by example failed", re);
            throw re;
        }
    }

	@Override
	public void save(Morada morada) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteById(int id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(Morada morada) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Iterable<Morada> getAll() {
		// TODO Auto-generated method stub
		return null;
	} 
}

