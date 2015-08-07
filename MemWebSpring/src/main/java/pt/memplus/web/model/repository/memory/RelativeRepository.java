package pt.memplus.web.model.repository.memory;

import java.util.LinkedList;

import pt.memplus.web.model.repository.IRepository;
import pt.memplus.web.models.Relative;
import pt.memplus.web.util.GenericUtils;

public class RelativeRepository implements IRepository<Relative> {
private LinkedList<Relative> repository;
	
	private Relative getNewRelative()
	{
		Relative model = new Relative();
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
		model.setCareGiver(false);
		return model;
	}

	public RelativeRepository() {
		repository = new LinkedList<Relative>();
		repository.add(getNewRelative()); 
	}
	@Override
	public Iterable<Relative> getAll() {
		return repository;
	}

	@Override
	public Iterable<Relative> getAllPaged(int currentpage, int nextNbrItems) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Relative select(int id) {
		return repository.getFirst();
	}

	@Override
	public boolean update(Relative obj) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean create(Relative obj) {
		return repository.add(obj);
	}

	@Override
	public boolean delete(int id) {
		// TODO Auto-generated method stub
		return false;
	}

}
