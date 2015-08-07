package pt.memplus.web.services;

import pt.memplus.web.models.Address;

public interface AddressService {
	void save(Address address);
	void update(Address address);
	void delete(int id);
	Iterable<Address> getAll();
	Address getById(int id);
}
