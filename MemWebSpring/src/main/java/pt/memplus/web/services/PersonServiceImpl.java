package pt.memplus.web.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pt.memplus.web.models.Person;
@Service("PersonService")
@Transactional
public class PersonServiceImpl implements PersonService {
	@Autowired
//    private PersonDao personDao;
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
