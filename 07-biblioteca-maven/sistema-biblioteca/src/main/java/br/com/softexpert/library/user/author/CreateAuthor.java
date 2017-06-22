package br.com.softexpert.library.user.author;

import javax.swing.JOptionPane;

import br.com.softexpert.library.entity.Author;
import br.com.softexpert.library.interfaces.Operations;
import br.com.softexpert.library.library.DateOperations;
import br.com.softexpert.library.operations.memory.AuthorsInMemory;

public class CreateAuthor{
	private Operations<Author> authors= new AuthorsInMemory();
	private DateOperations date = new DateOperations();

	public void create(){
		String birthday;
		Author author = new Author();
		String n = getName();
		if(((AuthorsInMemory) authors).checkIfAuthorExists(n)!=-1){
			JOptionPane.showMessageDialog(null,"O autor ja está cadastrado.");
		}else{
			author.setName(n);
			boolean checkDate;
			do{
				birthday = JOptionPane.showInputDialog("Digite a data de nascimento no formato dd/MM/yyyy:");
				if(birthday.isEmpty()){
					birthday = "01/01/01";
					checkDate=true;
				}else{
					checkDate=date.dateConverter(birthday);
				}
			}while(checkDate==false);
			if(date.CompareDate(date.getConvertedDate(birthday))){
				author.setBirthday(date.getConvertedDate(birthday));
				author.setNationality((JOptionPane.showInputDialog("Digite a nacionalidade: ")));
				try {
					if(authors.create(author)){
						JOptionPane.showMessageDialog(null, "Autor cadastrado.");
					}
				} catch (Exception e) {
					JOptionPane.showMessageDialog(null, "O campo Nome do autor deve ser preenchido.");
					create();
					e.printStackTrace();
				}
			}else{
				JOptionPane.showMessageDialog(null,"A data de nascimento não pode ser superior a data atual.");
			}

		}
	}
	public void returnMessage(){
		JOptionPane.showMessageDialog(null,"Não foi possível encontrar o autor, um novo autor será cadastrado.");
	}
	public String getName(){
		String name=(JOptionPane.showInputDialog("Digite o nome do autor: "));
		return name;
	}
}