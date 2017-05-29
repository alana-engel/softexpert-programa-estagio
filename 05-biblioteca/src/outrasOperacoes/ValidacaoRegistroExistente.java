package outrasOperacoes;

import operacoes.OperacoesDadosAutorEmMemoria;
import operacoes.OperacoesDadosCategoriaEmMemoria;

public class ValidacaoRegistroExistente {
	OperacoesDadosCategoriaEmMemoria gravaCategoria= new OperacoesDadosCategoriaEmMemoria();
	OperacoesDadosAutorEmMemoria gravaAutor= new OperacoesDadosAutorEmMemoria();

	public boolean verificaAutorJaCadastrado(String nome){
		gravaAutor.busca(nome);
		boolean achou=gravaAutor.achou();
		if(achou==true){
			return false;
		}
		return true;
	}
	public boolean verificaCategoriaJaCadastrada(String c){
		gravaCategoria.busca(c);
		boolean achou=gravaCategoria.achou();
		if(achou==true){
			return false;
		}
		return true;
	}
}


