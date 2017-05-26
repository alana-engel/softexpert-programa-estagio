package menuBiblioteca;
import javax.swing.JOptionPane;

import outrasOperacoes.GravaEmArquivo;

public class BibliotecaPrincipal {
	public static void main(String []args){
		GravaEmArquivo grava = new GravaEmArquivo();
		SubMenu1 menu =new SubMenu1();

		int opcao;
		do{
			opcao=Integer.parseInt(JOptionPane.showInputDialog(""
					+ "1-Cadastrar\n"
					+ "2-Alterar dados\n"
					+ "3-Buscar\n"
					+ "4-Excluir\n"
					+ "5-Gravar dados em arquivo\n"
					+ "6-Ler dados do arquivo\n"
					+ "7-Sair"));
			switch(opcao){
			case 1:menu.menuCadastrar();break;
			case 2:menu.menuAlterar();break;
			case 3:menu.menuBuscar();break;
			case 4:menu.menuExcluir();break;
			case 5:grava.gravaDadosArquivoLivro();break;
			case 6:grava.pegaDadosArquivoLivro();break;
			}
		}while(opcao!=7);
	}
}
