package br.com.softexpert.client.author;

import javax.swing.JOptionPane;

import br.com.softexpert.library.entity.Author;
import br.com.softexpert.library.interfaces.Operations;
import br.com.softexpert.library.library.DateOperations;
import br.com.softexpert.library.operations.db.hibernate.AuthorJPA;
import br.com.softexpert.library.operations.db.hibernate.Manager;



public class CreateAuthor{

	private Operations<Author> authors= new AuthorJPA(Manager.getEntityManager());
	private DateOperations date = new DateOperations();

	public void create(){
		Author author = new Author();
		String n = getName();
		author.setName(n);
		String birthday = JOptionPane.showInputDialog("Digite a data de nascimento no formato dd/MM/yyyy:");
		author.setNationality((JOptionPane.showInputDialog("Digite a nacionalidade: ")));
		if(birthday == null ||  birthday.isEmpty() ){
			birthday = null;
			author.setBirthday(null);
		}else{
			if(date.CompareDate(date.getConvertedDate(birthday))){
				author.setBirthday(date.getConvertedDate(birthday));	
			}else{
				JOptionPane.showMessageDialog(null,"A data de nascimento não pode ser superior a data atual.");
				create();
				return;
			}
		}
		try {
			if(authors.create(author)){
				JOptionPane.showMessageDialog(null, "Autor cadastrado.");
			}else{
				JOptionPane.showMessageDialog(null, "O campo Nome do autor deve ser preenchido.");
				create();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void returnMessage(){
		JOptionPane.showMessageDialog(null,"não foi possível encontrar o autor, um novo autor será cadastrado.");
	}

	public String getName(){
		String name=(JOptionPane.showInputDialog("Digite o nome do autor: "));
		if (name.isEmpty()) {
			JOptionPane.showMessageDialog(null, "O campo nome deve ser preenchido.");
			getName();
		}
		return name;
	}

	public String quantityOfAuthors() {
		String q = null;
		do{
			q=JOptionPane.showInputDialog("Digite a quantidade de autores: ");
			if(q.isEmpty()){
				JOptionPane.showMessageDialog(null, "Preencha a quantidade de autores.");
			}
		}while(q == null || q.isEmpty());
		return q;
	}
}