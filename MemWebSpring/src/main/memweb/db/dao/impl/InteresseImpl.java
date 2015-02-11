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
import db.dao.InteresseDAO;
import db.models.Interesse;

/**
 * Home object for domain model class Interesse.
 * @see .Interesse
 * @author Hibernate Tools
 */
public class InteresseImpl extends AbstractDAO implements InteresseDAO{

//    private static final Log log = LogFactory.getLog(InteresseImpl.class);
//
//    private final SessionFactory sessionFactory = getSessionFactory();
	
	   SessionFactory sessionFactory = getSessionFactory();
	    Log log;
	    
	    public InteresseImpl() {
	    	setLog(LogFactory.getLog(InteresseImpl.class));
	    	log = getLog();
		}

    
    @Override
    public void delete(Interesse persistentInstance) {
        log.debug("deleting Interesse instance");
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
    public Interesse findById( int id) {
        log.debug("getting Interesse instance with id: " + id);
        try {
            Interesse instance = (Interesse) sessionFactory.getCurrentSession()
                    .get("Interesse", id);
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
    public List findByExample(Interesse instance) {
        log.debug("finding Interesse instance by example");
        try {
            List results = sessionFactory.getCurrentSession()
                    .createCriteria("Interesse")
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
	public void deleteById(int id) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void save(Interesse at) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void update(Interesse at) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public Iterable<Interesse> getAll() {
		// TODO Auto-generated method stub
		return null;
	} 
}

