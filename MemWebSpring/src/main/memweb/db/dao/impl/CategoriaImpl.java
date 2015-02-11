package db.dao.impl;

import java.util.List;

import javax.naming.InitialContext;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Example;

import db.dao.AbstractDAO;
import db.dao.CategoriaDAO;
import db.models.Categoria;

/**
 * Home object for domain model class Categoria.
 * @see .Categoria
 * @author Hibernate Tools
 */
public class CategoriaImpl extends AbstractDAO implements CategoriaDAO{

//    private static final Log log = LogFactory.getLog(CategoriaImpl.class);
//
//    private final SessionFactory sessionFactory = getSessionFactory();
//    
    SessionFactory sessionFactory = getSessionFactory();
    Log log;
    
    public CategoriaImpl() {
    	setLog(LogFactory.getLog(CategoriaImpl.class));
    	log = getLog();
	}
    
    public Categoria findById( int id) {
        log.debug("getting Categoria instance with id: " + id);
        try {
            Categoria instance = (Categoria) sessionFactory.getCurrentSession()
                    .get("Categoria", id);
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
    public List findByExample(Categoria instance) {
        log.debug("finding Categoria instance by example");
        try {
            List results = sessionFactory.getCurrentSession()
                    .createCriteria("Categoria")
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
	public void save(Categoria cat) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(Categoria cat) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteById(int id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(Categoria cat) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Iterable<Categoria> getAll() {
		// TODO Auto-generated method stub
		return null;
	} 
}

