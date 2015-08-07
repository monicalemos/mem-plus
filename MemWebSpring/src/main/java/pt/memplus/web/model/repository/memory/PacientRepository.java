package pt.memplus.web.model.repository.memory;

import java.util.LinkedList;

import pt.memplus.web.model.repository.IRepository;
import pt.memplus.web.models.Patient;
import pt.memplus.web.util.GenericUtils;

public class PacientRepository implements IRepository<Patient> {
	private LinkedList<Patient> repository;
	
	private Patient getNewPatient()
	{
		Patient model = new Patient();
		model.setId(1234);
		model.setDateOfBirth(GenericUtils.getDate("1977-05-27"));
		model.setFirstName("Nuno");
		model.setGender("Masculino");
		model.setLastName("Cancelo");
		model.setMaritalStatus("Casado");
		model.setProfession("Informático");
		model.setMiddleName("Alexandre");
		model.getAddress().setCity("Lisboa");
		model.getAddress().setCountry("Portugal");
		model.getAddress().setRegion("Lisboa");
		return model;
	}

	public PacientRepository() {
		repository = new LinkedList<Patient>();
		repository.add(getNewPatient()); 
	}
	@Override
	public Iterable<Patient> getAll() {
		// TODO Auto-generated method stub
		return repository;
	}

	@Override
	public Iterable<Patient> getAllPaged(int currentpage, int nextNbrItems) {
		// TODO Auto-generated method stub
		return repository;
	}

	@Override
	public Patient select(int id) {
		// TODO Auto-generated method stub
		return repository.getFirst();
	}

	@Override
	public boolean update(Patient obj) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean create(Patient obj) {
		// TODO Auto-generated method stub
		return repository.add(obj);
	}

	@Override
	public boolean delete(int id) {
		// TODO Auto-generated method stub
		return false;
	}

}
