package br.com.softexpert.library.interfaces;

public interface Operations<T> {
	boolean create(T record) throws Exception;

	boolean delete(int id);

	void update(T record) throws Exception;

	T search(int id) throws Exception;
}
