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
import db.dao.FamiliarDAO;
import db.models.Familiar;

/**
 * Home object for domain model class Familiar.
 * @see .Familiar
 * @author Hibernate Tools
 */
public class FamiliarImpl extends AbstractDAO implements FamiliarDAO {

//    private static final Log log = LogFactory.getLog(FamiliarImpl.class);

//    private final SessionFactory sessionFactory = getSessionFactory();
	
    SessionFactory sessionFactory = getSessionFactory();
    Log log;
    
    public FamiliarImpl() {
    	setLog(LogFactory.getLog(FamiliarImpl.class));
    	log = getLog();
	}
    
    
    @Override    
    public void delete(Familiar persistentInstance) {
        log.debug("deleting Familiar instance");
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
    public Familiar findById( int id) {
        log.debug("getting Familiar instance with id: " + id);
        try {
            Familiar instance = (Familiar) sessionFactory.getCurrentSession()
                    .get("Familiar", id);
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
    public List findByExample(Familiar instance) {
        log.debug("finding Familiar instance by example");
        try {
            List results = sessionFactory.getCurrentSession()
                    .createCriteria("Familiar")
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
	public void save(Familiar familiar) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteById(int id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(Familiar familiar) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Iterable<Familiar> getAll() {
		// TODO Auto-generated method stub
		return null;
	} 
}

