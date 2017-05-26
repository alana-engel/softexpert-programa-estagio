package interacaoUsuario;

import javax.swing.JOptionPane;

import biblioteca.Livro;
import operacoes.OperacoesDadosLivrosEmMemoria;



public class CadastroLivroI{

	RecebeData data = new RecebeData();
	OperacoesDadosLivrosEmMemoria gravaLivro= new OperacoesDadosLivrosEmMemoria();

	public void cadastra(){

		Livro livro = new Livro();
		livro.setTitulo(JOptionPane.showInputDialog("Digite o titulo do livro: "));
		livro.setResumo(JOptionPane.showInputDialog("Digite o resumo do livro: "));
		String pag=JOptionPane.showInputDialog("Digite a quantidade de páginas: ");
		if(pag.isEmpty()){
			livro.setQntPaginas(0);
		}else{
			livro.setQntPaginas(Integer.parseInt(pag));
		}
		livro.setLocal(JOptionPane.showInputDialog("Digite o Local:"));
		livro.setDataDeAquisicao(data.recebeDataLivro());
		livro.setLista(gravaLivro.adicionaAutoresAoLivro());
		livro.setCategoria(gravaLivro.adicionaCategoria());
		if(gravaLivro.cadastra(livro)==true){
			JOptionPane.showMessageDialog(null, "Livro cadastrado.");
		}else{
			JOptionPane.showMessageDialog(null, "Livro não cadastrado, os campos Titulo e Local devem ser preenchidos.");
		}
	}

}
