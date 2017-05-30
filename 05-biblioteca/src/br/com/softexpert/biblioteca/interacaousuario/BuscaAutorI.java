package br.com.softexpert.biblioteca.interacaousuario;

import javax.swing.JOptionPane;

import br.com.softexpert.biblioteca.operacoesregistros.OperacoesDadosAutorEmMemoria;
import br.com.softexpert.biblioteca.registros.Autor;

public class BuscaAutorI{
	private OperacoesDadosAutorEmMemoria operacoesAutor= new OperacoesDadosAutorEmMemoria();
	public void busca(){
		Autor autor = new Autor();
		String imp=new String();
		String nAutor=(JOptionPane.showInputDialog("Digite o nome do autor: "));
		autor=operacoesAutor.busca(nAutor);
		boolean achou=operacoesAutor.achou();
		if (!achou){
			JOptionPane.showMessageDialog(null,"N�o foi poss�vel encontrar o autor.");
		}
		else{
			imp=imp+"C�digo do Autor: "+autor.getCodAutores()+"\n"
					+"Nome do Autor: "+autor.getNome()
					+"\n"+"Data de Nascimento: "+autor.getDataDeNascimento()+"\n"
					+"Nacionalidade: "+autor.getNacionalidade();
			JOptionPane.showMessageDialog(null, imp);
		}
	}
}
