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
import db.dao.QuestionarioDAO;
import db.models.Questionario;

/**
 * Home object for domain model class Questionario.
 * @see .Questionario
 * @author Hibernate Tools
 */
public class QuestionarioImpl extends AbstractDAO implements QuestionarioDAO{

//    private static final Log log = LogFactory.getLog(QuestionarioImpl.class);
//
//    private final SessionFactory sessionFactory = getSessionFactory();
	
    SessionFactory sessionFactory = getSessionFactory();
    Log log;
    
    public QuestionarioImpl() {
    	setLog(LogFactory.getLog(QuestionarioImpl.class));
    	log = getLog();
	}
    
    @Override
    public void delete(Questionario persistentInstance) {
        log.debug("deleting Questionario instance");
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
    public Questionario findById( int id) {
        log.debug("getting Questionario instance with id: " + id);
        try {
            Questionario instance = (Questionario) sessionFactory.getCurrentSession()
                    .get("Questionario", id);
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
    public List findByExample(Questionario instance) {
        log.debug("finding Questionario instance by example");
        try {
            List results = sessionFactory.getCurrentSession()
                    .createCriteria("Questionario")
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
	public void save(Questionario quest) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteById(int id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(Questionario quest) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Iterable<Questionario> getAll() {
		// TODO Auto-generated method stub
		return null;
	} 
}

