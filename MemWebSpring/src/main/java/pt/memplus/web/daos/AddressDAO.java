package pt.memplus.web.daos;

import pt.memplus.web.models.Address;

public interface AddressDAO {
	void save(Address address);
	void update(Address address);
	void delete(int id);
	Iterable<Address> getAll();
	Address getById(int id);
}
