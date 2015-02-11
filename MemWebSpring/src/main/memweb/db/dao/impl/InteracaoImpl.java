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
import db.dao.InteracaoDAO;
import db.models.Interacao;

/**
 * Home object for domain model class Interacao.
 * @see .Interacao
 * @author Hibernate Tools
 */
public class InteracaoImpl extends AbstractDAO implements InteracaoDAO{

//    private static final Log log = LogFactory.getLog(InteracaoImpl.class);
//
//    private final SessionFactory sessionFactory = getSessionFactory();
    
	   SessionFactory sessionFactory = getSessionFactory();
	    Log log;
	    
	    public InteracaoImpl() {
	    	setLog(LogFactory.getLog(InteracaoImpl.class));
	    	log = getLog();
		}

	    
    @Override    
    public void delete(Interacao persistentInstance) {
        log.debug("deleting Interacao instance");
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
    public Interacao findById( int id) {
        log.debug("getting Interacao instance with id: " + id);
        try {
            Interacao instance = (Interacao) sessionFactory.getCurrentSession()
                    .get("Interacao", id);
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
    public List findByExample(Interacao instance) {
        log.debug("finding Interacao instance by example");
        try {
            List results = sessionFactory.getCurrentSession()
                    .createCriteria("Interacao")
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
public void save(Interacao at) {
	// TODO Auto-generated method stub
	
}

@Override
public void deleteById(int id) {
	// TODO Auto-generated method stub
	
}

@Override
public void update(Interacao at) {
	// TODO Auto-generated method stub
	
}

@Override
public Iterable<Interacao> getAll() {
	// TODO Auto-generated method stub
	return null;
} 
}

