package br.com.softexpert.library.operations.memory;

import br.com.softexpert.library.entity.Author;
import br.com.softexpert.library.interfaces.Authors;
import br.com.softexpert.library.library.CreateRecordException;
import br.com.softexpert.library.repository.Repository;

public class AuthorsInMemory implements Authors{
	Repository repository = new Repository();

	@Override
	public boolean create(Author author) {
		SequentialCode cod = new SequentialCode();
		if (author.getName().isEmpty()) {
				throw new CreateRecordException("Não foi possível cadastrar o autor. Preencha o campo Nome.");
		} 
		author.setSequentialCode(cod.authorCode());
		repository.getAuthors().add(author);
		return true;
	}

	@Override
	public boolean update(Author author, int position){
		if(author.getName().isEmpty()){

		}else{
			repository.getAuthors().get(position).setName(author.getName());  
		}
		repository.getAuthors().get(position).setBirthday(author.getBirthday());
		repository.getAuthors().get(position).setNationality(author.getNationality());
		return true;
	}
	@Override
	public Author search(String name){
		boolean found = false;
		Author author = new Author();
		for (int i=0;i<repository.getAuthors().size();i++){
			if (repository.getAuthors().get(i).getName().equalsIgnoreCase(name)) {
				author = repository.getAuthors().get(i);
				found = true;
			}
		}
		if(found== false) 
			throw new CreateRecordException("Não foi possível encontrar o autor.");
		return author;
	}

	public int checkIfAuthorExists(String name){
		for (int i=0;i<repository.getAuthors().size();i++){
			if (repository.getAuthors().get(i).getName().equalsIgnoreCase(name)) {
				return i;
			}
		}
		return -1;
	}

	@Override
	public boolean delete(String name){

		for (int i=0;i<repository.getAuthors().size();i++){
			if (repository.getAuthors().get(i).getName().equalsIgnoreCase(name)) {
				repository.getAuthors().remove(i);
				return true;
			}
		}
		return false;
	}
}
