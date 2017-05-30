package br.com.softexpert.biblioteca.outrasoperacoes;

import br.com.softexpert.biblioteca.operacoesregistros.OperacoesDadosAutorEmMemoria;
import br.com.softexpert.biblioteca.operacoesregistros.OperacoesDadosCategoriaEmMemoria;

public class ValidacaoRegistroExistente {
	private OperacoesDadosCategoriaEmMemoria operacoesCategoria= new OperacoesDadosCategoriaEmMemoria();
	private OperacoesDadosAutorEmMemoria operacoesAutor= new OperacoesDadosAutorEmMemoria();

	public boolean verificaAutorJaCadastrado(String nome){
		operacoesAutor.busca(nome);
		boolean achou=operacoesAutor.achou();
		if(achou==true){
			return false;
		}
		return true;
	}
	public boolean verificaCategoriaJaCadastrada(String c){
		operacoesCategoria.busca(c);
		boolean achou=operacoesCategoria.achou();
		if(achou==true){
			return false;
		}
		return true;
	}
}


