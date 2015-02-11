package db.dao.impl;

import java.util.List;

import javax.naming.InitialContext;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Example;

import db.dao.AbstractDAO;
import db.dao.PacienteDAO;
import db.models.Paciente;

/**
 * Home object for domain model class Paciente.
 * @see .Paciente
 * @author Hibernate Tools
 */
public class PacienteImpl extends AbstractDAO implements PacienteDAO{

//    private static final Log log = LogFactory.getLog(PacienteImpl.class);
//
//    private final SessionFactory sessionFactory = getSessionFactory();
	
    SessionFactory sessionFactory = getSessionFactory();
    Log log;
    
    public PacienteImpl() {
    	setLog(LogFactory.getLog(PacienteImpl.class));
    	log = getLog();
	}
    
    @Override
    public void delete(Paciente pac) {
        log.debug("deleting Paciente instance");
        try {
            sessionFactory.getCurrentSession().delete(pac);
            log.debug("delete successful");
        }
        catch (RuntimeException re) {
            log.error("delete failed", re);
            throw re;
        }
    }
    
    @Override
    public Paciente getById( int id) {
        log.debug("getting Paciente instance with id: " + id);
        try {
            Paciente instance = (Paciente) sessionFactory.getCurrentSession()
                    .get("Paciente", id);
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
    public List findByExample(Paciente instance) {
        log.debug("finding Paciente instance by example");
        try {
            List results = sessionFactory.getCurrentSession()
                    .createCriteria("Paciente")
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
	public void save(Paciente pac) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteById(int id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(Paciente pac) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Iterable<Paciente> getAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Paciente getByPaciente(Paciente pac) {
		// TODO Auto-generated method stub
		return null;
	}
}

