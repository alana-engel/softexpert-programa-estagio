package interacaoUsuario;

import javax.swing.JOptionPane;

public class AdicionaCategoriaAoLivro {
	//CadastroCategoriaI cadastroCategoria =new CadastroCategoriaI();
	public String retornaDescricaoCategoria(){
		String dCategoria=(JOptionPane.showInputDialog("Digite a categoria do livro: "));
		if (dCategoria.isEmpty()) {
			JOptionPane.showMessageDialog(null, "Categoria n�o cadastrada, o campo Categoria deve ser preenchido.");
			retornaDescricaoCategoria();
		}
		return dCategoria;
	}
	public void retornaMensagemCategoriaNaoEncontrado(){
		JOptionPane.showMessageDialog(null,"N�o foi poss�vel encontrar a categoria, uma nova categoria ser� cadastrada.");

	}
}
