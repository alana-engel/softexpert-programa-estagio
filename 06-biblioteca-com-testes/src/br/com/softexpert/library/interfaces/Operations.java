package br.com.softexpert.library.interfaces;

public interface Operations<T> {

	boolean create(T record);

	boolean delete(String record);

	boolean update(T record, int position);

	T search(String name);

	
}