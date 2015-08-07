package pt.memplus.web.model.repository.memory;

import java.util.LinkedList;

import pt.memplus.web.model.repository.IRepository;
import pt.memplus.web.models.Doctor;
import pt.memplus.web.util.GenericUtils;

public class DoctorRepository implements IRepository<Doctor> {

	private LinkedList<Doctor> repository;
	
	private Doctor getNewDoctor()
	{
		Doctor model = new Doctor();
		model.setId(1234);
		model.setDateOfBirth(GenericUtils.getDate("1977-05-27"));
		model.setFirstName("Nuno");
		model.setGender("Masculino");
		model.setLastName("Cancelo");
		model.setMaritalStatus("Casado");
		model.setProfession("Informático");
		model.setMiddleName("Alexandre");
		return model;
	}
	
	public DoctorRepository() {
		repository = new LinkedList<Doctor>();
		repository.add(getNewDoctor()); 	
	}
	
	@Override
	public Iterable<Doctor> getAll() {
		// TODO Auto-generated method stub
		return repository;
	}

	@Override
	public Iterable<Doctor> getAllPaged(int currentpage, int nextNbrItems) {
		// TODO Auto-generated method stub
		return repository;
	}

	@Override
	public Doctor select(int id) {
		// TODO Auto-generated method stub
		return repository.getFirst();
	}

	@Override
	public boolean update(Doctor obj) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean create(Doctor obj) {
		// TODO Auto-generated method stub
		repository.addLast(obj);
		return repository.contains(obj);
	}

	@Override
	public boolean delete(int id) {
		// TODO Auto-generated method stub
		return false;
	}

}
