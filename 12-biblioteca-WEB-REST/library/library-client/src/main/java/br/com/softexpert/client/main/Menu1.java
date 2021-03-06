package br.com.softexpert.client.main;

import javax.swing.JOptionPane;

import br.com.softexpert.client.author.CreateAuthor;
import br.com.softexpert.client.author.DeleteAuthor;
import br.com.softexpert.client.author.SearchAuthor;
import br.com.softexpert.client.author.UpdateAuthor;
import br.com.softexpert.client.book.CreateBook;
import br.com.softexpert.client.book.DeleteBook;
import br.com.softexpert.client.book.UpdateBook;
import br.com.softexpert.client.category.CreateCategory;
import br.com.softexpert.client.category.DeleteCategory;
import br.com.softexpert.client.category.SearchCategory;
import br.com.softexpert.client.category.UpdateCategory;
import br.com.softexpert.library.exception.RecordException;

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
			case 1:try {
					createBook.create();
				} catch (RecordException e) {
					e.printStackTrace();
				}break;
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
			case 1:menu.menuSearchBook();break;
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
