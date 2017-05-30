package br.com.softexpert.biblioteca.interacaousuario;

import javax.swing.JOptionPane;

import br.com.softexpert.biblioteca.operacoesregistros.OperacoesDadosAutorEmMemoria;
import br.com.softexpert.biblioteca.outrasoperacoes.Data;
import br.com.softexpert.biblioteca.registros.Autor;

public class AlteracaoAutorI{
	private OperacoesDadosAutorEmMemoria operacoesAutor= new OperacoesDadosAutorEmMemoria();
	private Data operacoesData = new Data();
	public void altera(){
		String data;
		boolean dataT=false;
		Autor autor = new Autor();
		int posicao =operacoesAutor.retornaPosicao();
		String nAutor=(JOptionPane.showInputDialog("Digite o nome do autor: "));
		autor=operacoesAutor.busca(nAutor);
		boolean achou=operacoesAutor.achou();
		if (!achou){
			JOptionPane.showMessageDialog(null,"Não foi possível encontrar o autor.");
		}
		else{
			String n=JOptionPane.showInputDialog("Digite o nome do autor: ");
			autor.setNome(n);
			do{
				data = JOptionPane.showInputDialog("Digite a data de nascimento no formato dd/MM/yyyy:");
				if(data.isEmpty()){
					data = "01/01/01";
					dataT=true;
				}else{
				dataT=operacoesData.dataTransformada(data);
				}
				}while(dataT==false);
				if(operacoesData.ComparaData(operacoesData.retornaDataTransformada(data))){
					autor.setDataDeNascimento(operacoesData.retornaDataTransformada(data));
				}else{
					JOptionPane.showMessageDialog(null,"A data de nascimento não pode ser superior a data atual.");
				}
				autor.setNacionalidade((JOptionPane.showInputDialog("Digite a nacionalidade: ")));

			if(operacoesAutor.altera(autor, posicao)){
				JOptionPane.showMessageDialog(null, "Autor Cadastrado.");
			}else{
				JOptionPane.showMessageDialog(null, "O Campo Nome do autor deve ser preenchido.");
			}
		}
	}
}
