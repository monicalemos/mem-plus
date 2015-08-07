package pt.memplus.web.daos;

import org.springframework.stereotype.Repository;

import pt.memplus.web.models.Person;

@Repository("Person")
public class PersonDaoImpl extends AbstractDao implements PersonDao {

	@Override
	public void save(Person per) {
		// TODO Auto-generated method stub

	}

	@Override
	public void delete(int id) {
		// TODO Auto-generated method stub

	}

	@Override
	public void update(Person per) {
		// TODO Auto-generated method stub

	}

	@Override
	public Iterable<Person> getAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Person getById(int id) {
		// TODO Auto-generated method stub
		return null;
	}

}
