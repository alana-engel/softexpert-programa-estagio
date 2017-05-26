package interacaoUsuario;

import javax.swing.JOptionPane;

public class AdicionaCategoriaAoLivro {
	//CadastroCategoriaI cadastroCategoria =new CadastroCategoriaI();
	public String retornaDescricaoCategoria(){
		String dCategoria=(JOptionPane.showInputDialog("Digite a categoria do livro: "));
		if (dCategoria.isEmpty()) {
			JOptionPane.showMessageDialog(null, "Categoria não cadastrada, o campo Categoria deve ser preenchido.");
			retornaDescricaoCategoria();
		}
		return dCategoria;
	}
	public void retornaMensagemCategoriaNaoEncontrado(){
		JOptionPane.showMessageDialog(null,"Não foi possível encontrar a categoria, uma nova categoria será cadastrada.");

	}
}
