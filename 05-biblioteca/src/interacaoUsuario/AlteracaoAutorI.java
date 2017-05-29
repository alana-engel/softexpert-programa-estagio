package interacaoUsuario;
import javax.swing.JOptionPane;

import biblioteca.Autor;
import interfaces.Acoes;
import operacoes.OperacoesDadosAutorEmMemoria;

public class AlteracaoAutorI{
	OperacoesDadosAutorEmMemoria buscaAutor= new OperacoesDadosAutorEmMemoria();
	RecebeData data = new RecebeData();
	Acoes<Autor> gravaAutor= new OperacoesDadosAutorEmMemoria();
	public void altera(){
		Autor autor = new Autor();
		int posicao =buscaAutor.retornaPosicao();
		String nAutor=(JOptionPane.showInputDialog("Digite o nome do autor: "));
		autor=buscaAutor.busca(nAutor);
		boolean achou=buscaAutor.achou();
		if (!achou){
			JOptionPane.showMessageDialog(null,"Não foi possível encontrar o autor.");
		}
		else{
			String n=JOptionPane.showInputDialog("Digite o nome do autor: ");
			autor.setNome(n);
			autor.setDataDeNascimento(data.recebeDataAutor());
			autor.setNacionalidade((JOptionPane.showInputDialog("Digite a nacionalidade: ")));

			if(gravaAutor.altera(autor, posicao)){
				JOptionPane.showMessageDialog(null, "Autor Cadastrado.");
			}else{
				JOptionPane.showMessageDialog(null, "O Campo Nome do autor deve ser preenchido.");
			}
		}
	}
}
