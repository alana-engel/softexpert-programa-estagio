package br.com.softexpert.library.interfaces;

public interface Operations<T> {

	boolean create(T record) throws Exception;

	boolean delete(String record);

	void update(T record, int position) throws Exception;

	T search(String name) throws Exception;

	
}
