package db.dao.impl;

import java.util.List;

import javax.naming.InitialContext;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Example;

import db.dao.AbstractDAO;
import db.dao.EventoDAO;
import db.models.Evento;

/**
 * Home object for domain model class Evento.
 * @see .Evento
 * @author Hibernate Tools
 */
public class EventoImpl extends AbstractDAO implements EventoDAO{

//    private static final Log log = LogFactory.getLog(EventoImpl.class);
//
//    private final SessionFactory sessionFactory = getSessionFactory();
	
    SessionFactory sessionFactory = getSessionFactory();
    Log log;
    
    public EventoImpl() {
    	setLog(LogFactory.getLog(EventoImpl.class));
    	log = getLog();
	}
    
   
    @Override
    public void delete(Evento persistentInstance) {
        log.debug("deleting Evento instance");
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
    public Evento findById( int id) {
        log.debug("getting Evento instance with id: " + id);
        try {
            Evento instance = (Evento) sessionFactory.getCurrentSession()
                    .get("Evento", id);
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
    public List findByExample(Evento instance) {
        log.debug("finding Evento instance by example");
        try {
            List results = sessionFactory.getCurrentSession()
                    .createCriteria("Evento")
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
	public void save(Evento evento) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteById(int id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(Evento evento) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Iterable<Evento> getAll() {
		// TODO Auto-generated method stub
		return null;
	} 
}

