package br.com.softexpert.biblioteca.outrasoperacoes;

import br.com.softexpert.biblioteca.operacoesregistros.OperacoesDadosCategoriaEmMemoria;

public class ValidacaoRegistroExistente {
	private OperacoesDadosCategoriaEmMemoria operacoesCategoria= new OperacoesDadosCategoriaEmMemoria();
	public boolean verificaCategoriaJaCadastrada(String c){
		operacoesCategoria.busca(c);
		boolean achou=operacoesCategoria.achou();
		if(achou==true){
			return false;
		}
		return true;
	}
}


