package interacaoUsuario;

import javax.swing.JOptionPane;

import biblioteca.Autor;
import operacoes.OperacoesDadosAutorEmMemoria;

public class BuscaAutorI{
	OperacoesDadosAutorEmMemoria buscaAutor= new OperacoesDadosAutorEmMemoria();
	public void busca(){
		Autor autor = new Autor();
		String imp=new String();
		String nAutor=(JOptionPane.showInputDialog("Digite o nome do autor: "));
		autor=buscaAutor.busca(nAutor);
		boolean achou=buscaAutor.achou();
		if (!achou){
			JOptionPane.showMessageDialog(null,"Não foi possível encontrar o autor.");
		}
		else{
			imp=imp+"Código do Autor: "+autor.getCodAutores()+"\n"
					+"Nome do Autor: "+autor.getNome()
					+"\n"+"Data de Nascimento: "+autor.getDataDeNascimento()+"\n"
					+"Nacionalidade: "+autor.getNacionalidade();
			JOptionPane.showMessageDialog(null, imp);
		}
	}
}
