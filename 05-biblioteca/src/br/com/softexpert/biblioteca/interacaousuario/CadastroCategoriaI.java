package br.com.softexpert.biblioteca.interacaousuario;

import javax.swing.JOptionPane;

import br.com.softexpert.biblioteca.interfaces.Acoes;
import br.com.softexpert.biblioteca.operacoesregistros.OperacoesDadosCategoriaEmMemoria;
import br.com.softexpert.biblioteca.outrasoperacoes.ValidacaoRegistroExistente;
import br.com.softexpert.biblioteca.registros.Categoria;

public class CadastroCategoriaI{

	private Acoes<Categoria> operacoesCategoria= new OperacoesDadosCategoriaEmMemoria();
	
	public void cadastra(){
		ValidacaoRegistroExistente valida = new ValidacaoRegistroExistente();
		String categoria="";
		Categoria c = new Categoria();
		categoria=JOptionPane.showInputDialog("Digite a categoria a ser cadastrada: ");
		if(!valida.verificaCategoriaJaCadastrada(categoria)){
			JOptionPane.showMessageDialog(null,"A categoria ja está cadastrada.");
		}else{
			c.setDescricao(categoria);
			if(operacoesCategoria.cadastra(c)){
				JOptionPane.showMessageDialog(null, "Categoria cadastrada.");
			}else{
				JOptionPane.showMessageDialog(null, "O campo Categoria deve ser preenchido.");
				cadastra();
			}
		}
	}
}
