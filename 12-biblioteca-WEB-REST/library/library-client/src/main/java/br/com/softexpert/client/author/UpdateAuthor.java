package br.com.softexpert.client.author;
import javax.swing.JOptionPane;
import br.com.softexpert.library.entity.Author;
import br.com.softexpert.library.interfaces.Operations;
import br.com.softexpert.library.library.DateOperations;
import br.com.softexpert.library.operations.db.hibernate.AuthorJPA;
import br.com.softexpert.library.operations.db.hibernate.Manager;


public class UpdateAuthor{
	private Operations<Author> authors= new AuthorJPA(Manager.getEntityManager());

	public void update(){ 
		Author author = new Author();
		int id=Integer.parseInt(JOptionPane.showInputDialog("Digite o código do autor: "));
		try {
			author=authors.search(id);
		} catch (Exception e1) {
			JOptionPane.showMessageDialog(null, "Não foi possível encontrar o autor.");
			update();
			return;
		}
		String n=JOptionPane.showInputDialog("Digite o nome do autor: ");
		author.setName(n);
		getBirthday(author);
		author.setNationality((JOptionPane.showInputDialog("Digite a nacionalidade: ")));
		try {
			authors.update(author);
			JOptionPane.showMessageDialog(null, "Autor alterado.");
		} catch (Exception e) {
			if(author.getName().isEmpty()){
				JOptionPane.showMessageDialog(null, "O Campo Nome do autor deve ser preenchido.");
				update();
			}else{
				JOptionPane.showMessageDialog(null, "Não foi possível alterar o autor.");
				update();
			}
		}
	}
	private void getBirthday(Author nAuthor) {
		DateOperations dateOperations = new DateOperations();
		String date = JOptionPane.showInputDialog("Digite a data de nascimento no formato dd/MM/yyyy:");
		if(date == null ||  date.isEmpty() ){
			nAuthor.setBirthday(null);
		}else{
			if(dateOperations.CompareDate(dateOperations.getConvertedDate(date))){
				nAuthor.setBirthday(dateOperations.getConvertedDate(date));
			}else{
				JOptionPane.showMessageDialog(null,"A data de nascimento não pode ser superior a data atual.");
			}
		}
	}
}