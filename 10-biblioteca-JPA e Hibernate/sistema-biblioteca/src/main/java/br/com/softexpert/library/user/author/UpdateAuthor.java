package br.com.softexpert.library.user.author;
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
		String name=(JOptionPane.showInputDialog("Digite o nome do autor: "));
		try {
			author=authors.search(name);
		} catch (Exception e1) {
			JOptionPane.showMessageDialog(null, "Não foi possivel encontrar o autor.");
			update();
			e1.printStackTrace();
			return;
		}
		String n=JOptionPane.showInputDialog("Digite o nome do autor: ");
		author.setName(n);
		getBirthday(author);
		author.setNationality((JOptionPane.showInputDialog("Digite a nacionalidade: ")));
		try {
			authors.update(author);
			JOptionPane.showMessageDialog(null, "Autor Alterado.");
		} catch (Exception e) {
			if(author.getName().isEmpty()){
				JOptionPane.showMessageDialog(null, "O Campo Nome do autor deve ser preenchido.");
				update();
			}else{
				JOptionPane.showMessageDialog(null, "Não foi possivel alterar o autor.");
				update();
			}
			e.printStackTrace();
		}
	}
	private void getBirthday(Author nAuthor) {
		String date;
		DateOperations dateOperations = new DateOperations();
		date = JOptionPane.showInputDialog("Digite a data de nascimento no formato dd/MM/yyyy:");
		if(date == null ||  date.isEmpty() ){
			date = null;
			nAuthor.setBirthday(null);
		}else{
			if(dateOperations.CompareDate(dateOperations.getConvertedDate(date))){
				nAuthor.setBirthday(dateOperations.getConvertedDate(date));
			}else{
				JOptionPane.showMessageDialog(null,"A data de nascimento não pode ser superior a data atual.");
			}
		}}
}