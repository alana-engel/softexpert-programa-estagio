package br.com.softexpert.library.operations.memory;

import br.com.softexpert.library.entity.Author;
import br.com.softexpert.library.exception.RecordException;
import br.com.softexpert.library.interfaces.Authors;
import br.com.softexpert.library.repository.Repository;

public class AuthorsInMemory implements Authors{
	@Override
	public boolean create(Author author) throws RecordException {
		SequentialCode cod = new SequentialCode();
		if (author.getName().isEmpty()) {
			throw new RecordException("Não foi possível cadastrar o autor. Preencha o campo Nome.");
		} 
		author.setSequentialCode(cod.authorCode());
		Repository.getAuthors().add(author);
		return true;
	}

	//@Override
	public void update(Author author, int position) throws RecordException{
		if(author.getName().isEmpty()){
			throw new RecordException("Não foi possível alterar o autor. Preencha o campo Nome.");
		}else{
			Repository.getAuthors().get(position).setName(author.getName());  
		}
		Repository.getAuthors().get(position).setBirthday(author.getBirthday());
		Repository.getAuthors().get(position).setNationality(author.getNationality());
	}

	//@Override
	public Author search(String name) throws RecordException{
		boolean found = false;
		Author author = new Author();
		for (int i=0;i<Repository.getAuthors().size();i++){
			if (Repository.getAuthors().get(i).getName().equalsIgnoreCase(name)) {
				author = Repository.getAuthors().get(i);
				found = true;
			}
		}
		if(found== false) 
			throw new RecordException("Não foi possível encontrar o autor.");
		return author;
	}

	public int checkIfAuthorExists(String name){
		for (int i=0;i<Repository.getAuthors().size();i++){
			if (Repository.getAuthors().get(i).getName().equalsIgnoreCase(name)) {
				return i;
			}
		}
		return -1;
	}

	//@Override
	public boolean delete(String name){

		for (int i=0;i<Repository.getAuthors().size();i++){
			if (Repository.getAuthors().get(i).getName().equalsIgnoreCase(name)) {
				Repository.getAuthors().remove(i);
				return true;
			}
		}
		return false;
	}
}
