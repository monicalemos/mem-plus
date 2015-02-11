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
import db.dao.TecnicoDAO;
import db.models.Tecnico;

/**
 * Home object for domain model class Tecnico.
 * @see .Tecnico
 * @author Hibernate Tools
 */
public class TecnicoImpl extends AbstractDAO implements TecnicoDAO{

//    private static final Log log = LogFactory.getLog(TecnicoImpl.class);
//
//    private final SessionFactory sessionFactory = getSessionFactory();
	
    SessionFactory sessionFactory = getSessionFactory();
    Log log;
    
    public TecnicoImpl() {
    	setLog(LogFactory.getLog(TecnicoImpl.class));
    	log = getLog();
	}
    
    @Override
    public void delete(Tecnico persistentInstance) {
        log.debug("deleting Tecnico instance");
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
    public Tecnico findById(int id) {
        log.debug("getting Tecnico instance with id: " + id);
        try {
            Tecnico instance = (Tecnico) sessionFactory.getCurrentSession()
                    .get("Tecnico", id);
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
    public List findByExample(Tecnico instance) {
        log.debug("finding Tecnico instance by example");
        try {
            List results = sessionFactory.getCurrentSession()
                    .createCriteria("Tecnico")
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
	public void save(Tecnico quest) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void update(Tecnico quest) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public Iterable<Tecnico> getAll() {
		// TODO Auto-generated method stub
		return null;
	} 
}

