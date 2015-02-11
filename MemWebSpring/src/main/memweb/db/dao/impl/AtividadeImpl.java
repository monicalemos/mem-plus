package db.dao.impl;

import java.util.List;

import javax.naming.InitialContext;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Example;

import db.dao.AbstractDAO;
import db.dao.AtividadeDAO;
import db.models.Atividade;

/**
 * Home object for domain model class Atividade.
 * @see .Atividade
 * @author Hibernate Tools
 */
public class AtividadeImpl extends AbstractDAO implements AtividadeDAO {
   
    SessionFactory sessionFactory = getSessionFactory();
    Log log;
    
    public AtividadeImpl() {
    	setLog(LogFactory.getLog(AtividadeImpl.class));
    	log = getLog();
	}

   @Override    
    public void delete(Atividade persistentInstance) {
        log.debug("deleting Atividade instance");
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
    public Atividade findById( int id) {
        log.debug("getting Atividade instance with id: " + id);
        try {
            Atividade instance = (Atividade) sessionFactory.getCurrentSession()
                    .get("Atividade", id);
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
    public List findByExample(Atividade instance) {
        log.debug("finding Atividade instance by example");
        try {
            List results = sessionFactory.getCurrentSession()
                    .createCriteria("Atividade")
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
	public void save(Atividade at) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteById(int id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(Atividade at) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Iterable<Atividade> getAll() {
		// TODO Auto-generated method stub
		return null;
	} 
}

