package br.com.softexpert.library.operations.memory;

import java.util.ArrayList;
import java.util.List;

import br.com.softexpert.library.entity.Author;
import br.com.softexpert.library.interfaces.Authors;
import br.com.softexpert.library.library.CreateRecordException;

public class AuthorsInMemory implements Authors{
	private static List<Author> authors = new ArrayList<Author>();
	private SequentialCode cod = new SequentialCode();

	@Override
	public boolean create(Author author) {
		if (author.getName().isEmpty()) {
				throw new CreateRecordException("Não foi possível cadastrar o autor. Preencha o campo Nome.");
		} 
		author.setSequentialCode(cod.authorCode());
		authors.add(author);
		return true;
	}

	@Override
	public boolean update(Author author, int position){
		if(author.getName().isEmpty()){

		}else{
			authors.get(position).setName(author.getName());  
		}
		authors.get(position).setBirthday(author.getBirthday());
		authors.get(position).setNationality(author.getNationality());
		return true;
	}
	@Override
	public Author search(String name){
		Author author = new Author();
		for (int i=0;i<authors.size();i++){
			if (authors.get(i).getName().equalsIgnoreCase(name)) {
				author = authors.get(i);
			}
		}
		return author;
	}

	public int checkIfAuthorExists(String name){
		for (int i=0;i<authors.size();i++){
			if (authors.get(i).getName().equalsIgnoreCase(name)) {
				return i;
			}
		}
		return -1;
	}

	@Override
	public boolean delete(String name){

		for (int i=0;i<authors.size();i++){
			if (authors.get(i).getName().equalsIgnoreCase(name)) {
				authors.remove(i);
				return true;
			}
		}
		return false;
	}

	public void setAuthors(List<Author> authors) {
		AuthorsInMemory.authors = authors;
	}

	public List<Author> getAuthors() {
		return authors;
	}

}
