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
import db.dao.JogoDAO;
import db.models.Jogo;

/**
 * Home object for domain model class Jogo.
 * @see .Jogo
 * @author Hibernate Tools
 */
public class JogoImpl extends AbstractDAO implements JogoDAO{

//    private static final Log log = LogFactory.getLog(JogoImpl.class);
//
//    private final SessionFactory sessionFactory = getSessionFactory();
    
    SessionFactory sessionFactory = getSessionFactory();
    Log log;
    
    public JogoImpl() {
    	setLog(LogFactory.getLog(JogoImpl.class));
    	log = getLog();
	}

    
    @Override
    public void delete(Jogo persistentInstance) {
        log.debug("deleting Jogo instance");
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
    public Jogo findById( int id) {
        log.debug("getting Jogo instance with id: " + id);
        try {
            Jogo instance = (Jogo) sessionFactory.getCurrentSession()
                    .get("Jogo", id);
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
    public List findByExample(Jogo instance) {
        log.debug("finding Jogo instance by example");
        try {
            List results = sessionFactory.getCurrentSession()
                    .createCriteria("Jogo")
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
	public void save(Jogo jogo) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteById(int id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(Jogo jogo) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Iterable<Jogo> getAll() {
		// TODO Auto-generated method stub
		return null;
	} 
}

