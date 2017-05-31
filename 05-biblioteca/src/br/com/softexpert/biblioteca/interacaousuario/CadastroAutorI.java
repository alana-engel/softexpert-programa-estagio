package br.com.softexpert.biblioteca.interacaousuario;

import javax.swing.JOptionPane;

import br.com.softexpert.biblioteca.interfaces.Acoes;
import br.com.softexpert.biblioteca.operacoesregistros.OperacoesDadosAutorEmMemoria;
import br.com.softexpert.biblioteca.outrasoperacoes.Data;
import br.com.softexpert.biblioteca.registros.Autor;

public class CadastroAutorI{
	private Acoes<Autor> operacoesAutor= new OperacoesDadosAutorEmMemoria();
	private Data operacoesData = new Data();

	public void cadastra(){
		boolean dataT=false;
		String data;
		Autor autor = new Autor();
		String n = recebeNomeAutor();
		if(((OperacoesDadosAutorEmMemoria) operacoesAutor).verificaSeAutorExisteRetornaPosicao(n)!=-1){
			JOptionPane.showMessageDialog(null,"O autor ja est� cadastrado.");
		}else{

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
				JOptionPane.showMessageDialog(null,"A data de nascimento n�o pode ser superior a data atual.");
			}
			autor.setNacionalidade((JOptionPane.showInputDialog("Digite a nacionalidade: ")));

			if(operacoesAutor.cadastra(autor)){
				JOptionPane.showMessageDialog(null, "Autor cadastrado.");
			}else{
				JOptionPane.showMessageDialog(null, "O Campo Nome do autor deve ser preenchido.");
				cadastra();
			}
		}
	}

	public void retornaMensagemAutorNaoEncontrado(){
		JOptionPane.showMessageDialog(null,"N�o foi poss�vel encontrar o autor, um novo autor ser� cadastrado.");

	}
	public String recebeNomeAutor(){
		String nomeAutor=(JOptionPane.showInputDialog("Digite o nome do autor: "));
		if (nomeAutor.isEmpty()){
			JOptionPane.showMessageDialog(null, "O campo Nome do autor deve ser preenchido.");
			recebeNomeAutor();
		}
		return nomeAutor;
	}
}
