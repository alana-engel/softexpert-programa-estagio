package interacaoUsuario;

import javax.swing.JOptionPane;

public class AdicionaAutorAoLivro {
	String nomeAutor;
	public void retornaMensagemAutorNaoEncontrado(){
		JOptionPane.showMessageDialog(null,"N�o foi poss�vel encontrar o autor, um novo autor ser� cadastrado.");

	}
	public void recebeNomeAutor(){
		nomeAutor=(JOptionPane.showInputDialog("Digite o nome do autor: "));
		if (nomeAutor.isEmpty()){
			JOptionPane.showMessageDialog(null, "Autor n�o cadastrado, o campo Nome do autor deve ser preenchido.");
			recebeNomeAutor();
		}
	}
	public String retornaNomeAutor(){
		return nomeAutor;
	}
	public String recebeQntAutores(){
		
		String qntdade=JOptionPane.showInputDialog("Digite a quantidade de autores: ");
		if(qntdade.isEmpty()){
			JOptionPane.showMessageDialog(null, "preencha a quantidade de autores.");
	
		}
		
		return qntdade;

	}
}	
