package interacaoUsuario;

import javax.swing.JOptionPane;

import biblioteca.Categoria;
import interfaces.Acoes;
import operacoes.OperacoesDadosCategoriaEmMemoria;
import outrasOperacoes.ValidacaoRegistroExistente;


public class CadastroCategoriaI{

	Acoes<Categoria> gravaCategoria= new OperacoesDadosCategoriaEmMemoria();
	public void cadastra(){
		ValidacaoRegistroExistente valida = new ValidacaoRegistroExistente();
		String categoria="";
		Categoria c = new Categoria();
		categoria=JOptionPane.showInputDialog("Digite a categoria a ser cadastrada: ");
		if(valida.verificaCategoriaJaCadastrada(categoria) == false){
			JOptionPane.showMessageDialog(null,"A categoria ja está cadastrada.");
		}else{
			c.setDescricao(categoria);
			if(gravaCategoria.cadastra(c)==true){
				JOptionPane.showMessageDialog(null, "Categoria cadastrada.");
			}else{
				JOptionPane.showMessageDialog(null, "O campo Categoria deve ser preenchido.");
				cadastra();
			}
		}
	}
}
