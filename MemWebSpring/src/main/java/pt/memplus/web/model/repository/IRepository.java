package pt.memplus.web.model.repository;

public interface IRepository<T> {
	public Iterable<T> getAll();
	public Iterable<T> getAllPaged(int currentpage,int nextNbrItems);
	public T select(int id);
	public boolean update(T obj);
	public boolean create(T obj);
	public boolean delete(int id);	
}
