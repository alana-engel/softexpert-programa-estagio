package br.com.softexpert.library.user.author;
import javax.swing.JOptionPane;

import br.com.softexpert.library.entity.Author;
import br.com.softexpert.library.interfaces.Authors;
import br.com.softexpert.library.library.DateOperations;
import br.com.softexpert.library.operations.memory.AuthorsInMemory;


public class UpdateAuthor{
	private Authors authors= new AuthorsInMemory();

	public void update(){ 
		Author author = new Author();
		String name=(JOptionPane.showInputDialog("Digite o nome do autor: "));
		int exist = authors.checkIfAuthorExists(name);
		if (exist==-1){
			JOptionPane.showMessageDialog(null,"Não foi possível encontrar o autor.");
		}
		else{
			String n=JOptionPane.showInputDialog("Digite o nome do autor: ");
			if(((AuthorsInMemory) authors).checkIfAuthorExists(n)!=-1){
				JOptionPane.showMessageDialog(null,"O autor ja está cadastrado.");
			}else{
				author.setName(n);
				getBirthday(author);
				author.setNationality((JOptionPane.showInputDialog("Digite a nacionalidade: ")));
				try {
					authors.update(author, exist);
					JOptionPane.showMessageDialog(null, "Autor Cadastrado.");
				} catch (Exception e) {
					JOptionPane.showMessageDialog(null, "O Campo Nome do autor deve ser preenchido.");
					e.printStackTrace();
				}
			}
		}
	}
	private void getBirthday(Author autor) {
		String date;
		boolean dateT;
		DateOperations dateOperations = new DateOperations();
		do{
			date = JOptionPane.showInputDialog("Digite a data de nascimento no formato dd/MM/yyyy:");
			if(date.isEmpty()){
				date = "01/01/01";
				dateT=true;
			}else{
				dateT=dateOperations.dateConverter(date);
			}
		}while(dateT==false);
		if(dateOperations.CompareDate(dateOperations.getConvertedDate(date))){
			autor.setBirthday(dateOperations.getConvertedDate(date));
		}else{
			JOptionPane.showMessageDialog(null,"A data de nascimento não pode ser superior a data atual.");
		}
	}
}