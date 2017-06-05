package br.com.softexpert.library.main;

import javax.swing.JOptionPane;

import br.com.softexpert.library.user.author.CreateAuthor;
import br.com.softexpert.library.user.author.DeleteAuthor;
import br.com.softexpert.library.user.author.SearchAuthor;
import br.com.softexpert.library.user.author.UpdateAuthor;
import br.com.softexpert.library.user.book.CreateBook;
import br.com.softexpert.library.user.book.DeleteBook;
import br.com.softexpert.library.user.book.UpdateBook;
import br.com.softexpert.library.user.category.CreateCategory;
import br.com.softexpert.library.user.category.DeleteCategory;
import br.com.softexpert.library.user.category.SearchCategory;
import br.com.softexpert.library.user.category.UpdateCategory;

public class Menu1 {
	private UpdateBook updateBook = new UpdateBook();
	private UpdateAuthor updateAuthor  = new UpdateAuthor();
	private UpdateCategory updateCategory = new UpdateCategory();
	private CreateBook createBook = new CreateBook();
	private CreateCategory createCategory = new CreateCategory();
	private CreateAuthor createAuthor = new CreateAuthor();
	private DeleteBook deleteBook = new DeleteBook();
	private DeleteAuthor deleteAuthor= new DeleteAuthor();
	private DeleteCategory deleteCategory = new DeleteCategory();
	private SearchAuthor searchAuthor = new SearchAuthor();
	private SearchCategory searchCategory = new SearchCategory();
	private Menu2 menu = new Menu2();
	
	
	public void menuCreate() {
		int option=0;
		do{
			option=Integer.parseInt(JOptionPane.showInputDialog(""
					+ "1-Cadastrar Livro\n"
					+ "2-Cadastrar Categoria\n"
					+ "3-Cadastrar Autor\n"
					+ "4-Voltar"));
			switch(option){
			case 1:createBook.create();break;
			case 2:createCategory .create();break;
			case 3:createAuthor.create();break;
			}
		}while(option!=4);
	}

	public void menuUpdate() {
		int option;
		do{
			option=Integer.parseInt(JOptionPane.showInputDialog(""
					+ "1-Alterar dados do Livro\n"
					+ "2-Alterar dados da Categoria\n"
					+ "3-Alterar dados do Autor\n"
					+ "4-Voltar"));
			switch(option){
			case 1:updateBook.update();break;
			case 2:updateCategory.update();break;
			case 3:updateAuthor.update();break;
			}
		}while(option!=4);
	}

	public void menuFind(){
		int option;
		do{
			option=Integer.parseInt(JOptionPane.showInputDialog(""
					+ "1-Buscar Livro\n"
					+ "2-Buscar Categoria\n"
					+ "3-Buscar Autor\n"
					+ "4-Voltar"));
			switch(option){
			case 1:menu.menuFindBook();break;
			case 2:searchCategory.search();break;
			case 3:searchAuthor.search();break;
			}
		}while(option!=4);
	}

	public void menuDelete(){
		int option;
		do{
			option=Integer.parseInt(JOptionPane.showInputDialog(""
					+ "1-Excluir Livro\n"
					+ "2-Excluir Categoria\n"
					+ "3-Excluir Autor\n"
					+ "4-Voltar"));
			switch(option){
			case 1:deleteBook.delete();break;
			case 2:deleteCategory.delete();;break;
			case 3:deleteAuthor.delete();;break;
			}
		}while(option!=4);
	}

}
