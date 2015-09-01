package com.mem.app.model.repository.memory;

import com.mem.app.model.Evento;
import com.mem.app.model.repository.IRepository;

public class EventoRepository implements IRepository<Evento> {

	@Override
	public Iterable<Evento> getAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Iterable<Evento> getAllPaged(int currentpage, int nextNbrItems) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Evento select(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean update(Evento obj) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean create(Evento obj) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean delete(int id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Evento selectObject(Evento obj) {
		// TODO Auto-generated method stub
		return null;
	}

}
