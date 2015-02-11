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
import db.dao.RelacaoFamiliarFamiliarDAO;
import db.models.RelacaoFamiliarFamiliar;

/**
 * Home object for domain model class RelacaoFamiliarFamiliar.
 * @see .RelacaoFamiliarFamiliar
 * @author Hibernate Tools
 */
public class RelacaoFamiliarFamiliarImpl extends AbstractDAO implements RelacaoFamiliarFamiliarDAO{

//    private static final Log log = LogFactory.getLog(RelacaoFamiliarFamiliarImpl.class);
//
//    private final SessionFactory sessionFactory = getSessionFactory();
	
    SessionFactory sessionFactory = getSessionFactory();
    Log log;
    
    public RelacaoFamiliarFamiliarImpl() {
    	setLog(LogFactory.getLog(RelacaoFamiliarFamiliarImpl.class));
    	log = getLog();
	}
    
    @Override
    public void delete(RelacaoFamiliarFamiliar persistentInstance) {
        log.debug("deleting RelacaoFamiliarFamiliar instance");
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
    public RelacaoFamiliarFamiliar findById(int id) {
        log.debug("getting RelacaoFamiliarFamiliar instance with id: " + id);
        try {
            RelacaoFamiliarFamiliar instance = (RelacaoFamiliarFamiliar) sessionFactory.getCurrentSession()
                    .get("RelacaoFamiliarFamiliar", id);
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
    public List findByExample(RelacaoFamiliarFamiliar instance) {
        log.debug("finding RelacaoFamiliarFamiliar instance by example");
        try {
            List results = sessionFactory.getCurrentSession()
                    .createCriteria("RelacaoFamiliarFamiliar")
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
	public void save(RelacaoFamiliarFamiliar relacao) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteById(int id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(RelacaoFamiliarFamiliar relacao) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Iterable<RelacaoFamiliarFamiliar> getAll() {
		// TODO Auto-generated method stub
		return null;
	} 
}

