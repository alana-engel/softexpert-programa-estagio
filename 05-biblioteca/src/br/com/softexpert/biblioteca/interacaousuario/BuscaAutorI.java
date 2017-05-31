package br.com.softexpert.biblioteca.interacaousuario;
import javax.swing.JOptionPane;
import br.com.softexpert.biblioteca.operacoesregistros.OperacoesDadosAutorEmMemoria;

public class BuscaAutorI{
	private OperacoesDadosAutorEmMemoria operacoesAutor= new OperacoesDadosAutorEmMemoria();
	public void busca(){
		String nAutor=(JOptionPane.showInputDialog("Digite o nome do autor: "));
		int existe=operacoesAutor.verificaSeAutorExisteRetornaPosicao(nAutor);
		if (existe == -1){
			JOptionPane.showMessageDialog(null,"Não foi possível encontrar o autor.");
		}
		else{
			JOptionPane.showMessageDialog(null, operacoesAutor.busca(nAutor));
		}
	}
}
