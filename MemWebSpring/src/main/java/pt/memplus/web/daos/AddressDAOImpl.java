package pt.memplus.web.daos;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import pt.memplus.web.models.Address;
@Repository
public class AddressDAOImpl extends AbstractDao implements AddressDAO  {
	private static final Logger logger = LoggerFactory.getLogger(AddressDAOImpl.class);

	@Override
	public void save(Address address) {
		getSession().persist(address);
	}

	@Override
	public void update(Address address) {
		getSession().update(address);
	}

	@Override
	public Address getById(int id) {
        Criteria criteria = getSession().createCriteria(Address.class);
        criteria.add(Restrictions.eq("id",id));
        return (Address) criteria.uniqueResult();
	}
	@Override
	public void delete(int id) {
		// TODO Auto-generated method stub
		
	}
	@SuppressWarnings("unchecked")
	@Override
	public Iterable<Address> getAll() {
		Criteria criteria = getSession().createCriteria(Address.class);
        return (List<Address>) criteria.list();
	}

}
