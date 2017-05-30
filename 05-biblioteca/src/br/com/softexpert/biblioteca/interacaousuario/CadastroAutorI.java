package br.com.softexpert.biblioteca.interacaousuario;
import javax.swing.JOptionPane;

import br.com.softexpert.biblioteca.interfaces.Acoes;
import br.com.softexpert.biblioteca.operacoesregistros.OperacoesDadosAutorEmMemoria;
import br.com.softexpert.biblioteca.outrasoperacoes.Data;
import br.com.softexpert.biblioteca.outrasoperacoes.ValidacaoRegistroExistente;
import br.com.softexpert.biblioteca.registros.Autor;

public class CadastroAutorI{

	private ValidacaoRegistroExistente valida = new ValidacaoRegistroExistente();
	private Acoes<Autor> operacoesAutor= new OperacoesDadosAutorEmMemoria();
	private Data operacoesData = new Data();
	public void cadastra(){
		Autor autor = new Autor();
		String n=JOptionPane.showInputDialog("Digite o nome do autor: ");
		if(!valida.verificaAutorJaCadastrado(n)){
			JOptionPane.showMessageDialog(null,"O autor ja está cadastrado.");
		}else{
			if (n.isEmpty()) {
				cadastra();
			}else{
				autor.setNome(n);
				String data = JOptionPane.showInputDialog("Digite a data de nascimento:");
				if(data.isEmpty())
					data = "01/01/01";
				if(operacoesData.ComparaData(data)){
					autor.setDataDeNascimento(operacoesData.retornaDataTransformada(data));
				}else{
					JOptionPane.showMessageDialog(null,"A data de nascimento não pode ser superior a data atual.");
				}
				autor.setNacionalidade((JOptionPane.showInputDialog("Digite a nacionalidade: ")));
			}
			if(operacoesAutor.cadastra(autor)){
				JOptionPane.showMessageDialog(null, "Autor cadastrado.");
			}else{
				JOptionPane.showMessageDialog(null, "O Campo Nome do autor deve ser preenchido.");
				cadastra();
			}
		}
	}
}
