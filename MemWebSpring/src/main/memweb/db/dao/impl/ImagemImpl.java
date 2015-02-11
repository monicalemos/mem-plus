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
import db.dao.ImagemDAO;
import db.models.Imagem;

/**
 * Home object for domain model class Imagem.
 * @see .Imagem
 * @author Hibernate Tools
 */
public class ImagemImpl extends AbstractDAO implements ImagemDAO {

//    private static final Log log = LogFactory.getLog(ImagemImpl.class);
//
//    private final SessionFactory sessionFactory = getSessionFactory();
//    
    SessionFactory sessionFactory = getSessionFactory();
    Log log;
    
    public ImagemImpl() {
    	setLog(LogFactory.getLog(ImagemImpl.class));
    	log = getLog();
	}

    
    @Override	
    public void delete(int id) {
        log.debug("deleting Imagem instance");
        try {
            sessionFactory.getCurrentSession().delete(findById(id));
            log.debug("delete successful");
        }
        catch (RuntimeException re) {
            log.error("delete failed", re);
            throw re;
        }
    }
    
   @Override    
    public Imagem findById( int id) {
        log.debug("getting Imagem instance with id: " + id);
        try {
            Imagem instance = (Imagem) sessionFactory.getCurrentSession()
                    .get("Imagem", id);
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
    public List findByExample(Imagem instance) {
        log.debug("finding Imagem instance by example");
        try {
            List results = sessionFactory.getCurrentSession()
                    .createCriteria("Imagem")
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
public void save(Imagem img) {
	// TODO Auto-generated method stub
	
}


@Override
public void update(Imagem img) {
	// TODO Auto-generated method stub
	
}

@Override
public Iterable<Imagem> getAll() {
	// TODO Auto-generated method stub
	return null;
} 
}

