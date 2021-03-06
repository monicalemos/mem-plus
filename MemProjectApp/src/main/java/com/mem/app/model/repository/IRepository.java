package com.mem.app.model.repository;

public interface IRepository<T> {
	public Iterable<T> getAll();
	public Iterable<T> getAllPaged(int currentpage,int nextNbrItems);
	public T select(int id);
	public T selectObject(T obj);
	public boolean update(T obj);
	public boolean create(T obj);
	public boolean delete(int id);	
}
