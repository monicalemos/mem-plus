package pt.memplus.web.services;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pt.memplus.web.daos.AddressDAO;
import pt.memplus.web.models.Address;

@Service("addressService")
@Transactional
public class AddressServiceImpl implements AddressService {
	@Autowired
    private AddressDAO addressDAO;
	
	@Override
	public void save(Address address) {
		addressDAO.save(address);
		
	}

	@Override
	public void update(Address address) {
		addressDAO.update(address);
		
	}

	@Override
	public void delete(int id) {
		addressDAO.delete(id);
		
	}

	@Override
	public Iterable<Address> getAll() {
		return addressDAO.getAll();
	}

	@Override
	public Address getById(int id) {
		return addressDAO.getById(id);
	}


}
