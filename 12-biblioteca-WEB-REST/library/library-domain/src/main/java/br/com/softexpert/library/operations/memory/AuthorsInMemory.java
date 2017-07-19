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

	@Override
	public void update(Author author) throws RecordException{
		if(author.getName().isEmpty()){
			throw new RecordException("Não foi possível alterar o autor. Preencha o campo Nome.");
		}else{
					author.setName(author.getName());
					author.setBirthday(author.getBirthday());
					author.setNationality(author.getNationality());
		}
	}

	@Override
	public Author search(int id) throws RecordException{
		boolean found = false;
		Author author = new Author();
		for (int i=0;i<Repository.getAuthors().size();i++){
			if (Repository.getAuthors().get(i).getSequentialCode() == (id)) {
				author = Repository.getAuthors().get(i);
				found = true;
			}
		}
		if(found== false) 
			throw new RecordException("Não foi possível encontrar o autor.");
		return author;
	}

	@Override
	public boolean delete(int id){

		for (int i=0;i<Repository.getAuthors().size();i++){
			if (Repository.getAuthors().get(i).getSequentialCode()== id) {
				Repository.getAuthors().remove(i);
				return true;
			}
		}
		return false;
	}
	public Author searchByName(String name) throws RecordException{
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
}
