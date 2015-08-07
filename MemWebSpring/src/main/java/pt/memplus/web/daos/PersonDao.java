package pt.memplus.web.daos;

import pt.memplus.web.models.Person;

public interface PersonDao {
	void save(Person per);
	void delete(int id);
	void update(Person per);
	Iterable<Person> getAll();
	Person getById(int id);
}
