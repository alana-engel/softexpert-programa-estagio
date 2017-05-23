import javax.swing.JOptionPane;

public class BibliotecaPrincipal {
	public static void main(String []args){
		Repositorio grava = new Repositorio();
		OperacoesBiblioteca operacoes = new OperacoesBiblioteca();
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
			case 1:operacoes.cadastrar();break;
			case 2:operacoes.alterar();break;
			case 3:operacoes.buscar();break;
			case 4:operacoes.excluir();break;
			case 5:grava.gravaDadosArquivoLivro();break;
			case 6:grava.pegaDadosArquivoLivro();break;
			}
		}while(opcao!=7);
	}
}
