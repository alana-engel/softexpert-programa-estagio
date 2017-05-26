package menuBiblioteca;

import javax.swing.JOptionPane;

import interacaoUsuario.BuscaLivroI;
import outrasOperacoes.GravaEmArquivo;
import outrasOperacoes.Ordenacao;

public class SubMenu2 {
	GravaEmArquivo grava = new GravaEmArquivo();
	BuscaLivroI opcoesBuscaLivro = new BuscaLivroI();
	Ordenacao ordenacao = new Ordenacao();
	public void menuBuscarLivro(){
		ordenacao.ordena();
		int opcao=0;
		do{
			opcao=Integer.parseInt(JOptionPane.showInputDialog(""
					+ "1-Buscar pelo Código\n"
					+ "2-Buscar pelo Título\n"
					+ "3-Buscar pela Categoria\n"
					+ "4-Buscar pelo Autor\n"
					+ "5-Voltar"));
			switch(opcao){
			case 1:opcoesBuscaLivro.buscaLivroCod();;break;
			case 2:opcoesBuscaLivro.buscaLivroTitulo();break;
			case 3:opcoesBuscaLivro.buscaLivroCategoria();break;
			case 4:opcoesBuscaLivro.buscaLivroAutor();break;
			}
		}while(opcao!=5);
	}
}
