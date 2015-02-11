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
import db.dao.RelacaoPacienteFamiliarDAO;
import db.models.RelacaoPacienteFamiliar;
import db.models.RelacaoPacienteFamiliarId;

/**
 * Home object for domain model class RelacaoPacienteFamiliar.
 * @see .RelacaoPacienteFamiliar
 * @author Hibernate Tools
 */
public class RelacaoPacienteFamiliarImpl extends AbstractDAO implements RelacaoPacienteFamiliarDAO{

//    private static final Log log = LogFactory.getLog(RelacaoPacienteFamiliarImpl.class);
//
//    private final SessionFactory sessionFactory = getSessionFactory();
    
    SessionFactory sessionFactory = getSessionFactory();
    Log log;
    
    public RelacaoPacienteFamiliarImpl() {
    	setLog(LogFactory.getLog(RelacaoPacienteFamiliarImpl.class));
    	log = getLog();
	}
    
    @Override
    public void delete(RelacaoPacienteFamiliar persistentInstance) {
        log.debug("deleting RelacaoPacienteFamiliar instance");
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
    public RelacaoPacienteFamiliar findById(int id) {
        log.debug("getting RelacaoPacienteFamiliar instance with id: " + id);
        try {
            RelacaoPacienteFamiliar instance = (RelacaoPacienteFamiliar) sessionFactory.getCurrentSession()
                    .get("RelacaoPacienteFamiliar", id);
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
    public List findByExample(RelacaoPacienteFamiliar instance) {
        log.debug("finding RelacaoPacienteFamiliar instance by example");
        try {
            List results = sessionFactory.getCurrentSession()
                    .createCriteria("RelacaoPacienteFamiliar")
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
	public void save(RelacaoPacienteFamiliar relacao) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteById(int id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(RelacaoPacienteFamiliar relacao) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Iterable<RelacaoPacienteFamiliar> getAll() {
		// TODO Auto-generated method stub
		return null;
	} 
}

