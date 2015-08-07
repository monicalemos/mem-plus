package pt.memplus.web.services;

import pt.memplus.web.models.Person;

public interface PersonService {
	void save(Person per);
	void delete(int id);
	void update(Person per);
	Iterable<Person> getAll();
	Person getById(int id);
}
