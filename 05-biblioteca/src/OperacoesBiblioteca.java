
import javax.swing.JOptionPane;

public class OperacoesBiblioteca {
	OperacoesCategoria oc = new OperacoesCategoria();
	OperacoesLivro ol= new OperacoesLivro();
	OperacoesAutores oa=new OperacoesAutores();

	public void cadastrar(){
		int opcao;
		do{
			opcao=Integer.parseInt(JOptionPane.showInputDialog(""
					+ "1-Cadastrar Livro\n"
					+ "2-Cadastrar Categoria\n"
					+ "3-Cadastrar Autor\n"
					+ "4-Voltar"));
		switch(opcao){
		case 1:ol.cadastra();break;
		case 2:oc.cadastra();break;
		case 3:oa.cadastra();break;
		}
	}while(opcao!=4);
	}
	
	public void alterar(){
		int opcao;
		do{
			opcao=Integer.parseInt(JOptionPane.showInputDialog(""
					+ "1-Alterar dados do Livro\n"
					+ "2-Alterar dados da Categoria\n"
					+ "3-Alterar dados do Autor\n"
					+ "4-Voltar"));
		switch(opcao){
		case 1:ol.altera();break;
		case 2:oc.altera();break;
		case 3:oa.altera();break;
		}
	}while(opcao!=4);
	}
	
	public void buscar(){
		int opcao;
		do{
			opcao=Integer.parseInt(JOptionPane.showInputDialog(""
					+ "1-Buscar Livro\n"
					+ "2-Buscar Categoria\n"
					+ "3-Buscar Autor\n"
					+ "4-Voltar"));
		switch(opcao){
		case 1:ol.busca();break;
		case 2:oc.busca();break;
		case 3:oa.busca();break;
		}
	}while(opcao!=4);
	}
	
	public void excluir(){
		int opcao;
		do{
			opcao=Integer.parseInt(JOptionPane.showInputDialog(""
					+ "1-Excluir Livro\n"
					+ "2-Excluir Categoria\n"
					+ "3-Excluir Autor\n"
					+ "4-Voltar"));
		switch(opcao){
		case 1:ol.exclui();break;
		case 2:oc.exclui();break;
		case 3:oa.exclui();break;
		}
	}while(opcao!=4);
	}
	
	
}
