package br.com.softexpert.biblioteca.operacoesregistros;

import java.util.ArrayList;
import java.util.List;

import br.com.softexpert.biblioteca.interfaces.Acoes;
import br.com.softexpert.biblioteca.outrasoperacoes.CodSequencial;
import br.com.softexpert.biblioteca.registros.Autor;

public class OperacoesDadosAutorEmMemoria implements Acoes<Autor>{
	private static List<Autor> CadastroDeAutores = new ArrayList<Autor>();
	private CodSequencial cod = new CodSequencial();

	@Override
	public boolean cadastra(Autor registro) {
		registro.setCodAutores(cod.codAutor());
		CadastroDeAutores.add(registro);
		return true;
	}

	@Override
	public boolean altera(Autor autor, int posicao){
		if(autor.getNome().isEmpty()){

		}else{
			CadastroDeAutores.get(posicao).setNome(autor.getNome());  
		}
		CadastroDeAutores.get(posicao).setDataDeNascimento(autor.getDataDeNascimento());
		CadastroDeAutores.get(posicao).setNacionalidade(autor.getNacionalidade());
		return true;
	}
	@Override
	public Autor busca(String nAutor){
		Autor autor = new Autor();
		for (int i=0;i<CadastroDeAutores.size();i++){
			if (CadastroDeAutores.get(i).getNome().equalsIgnoreCase(nAutor)) {
				autor = CadastroDeAutores.get(i);
			}
		}
		return autor;
	}

	public int verificaSeAutorExisteRetornaPosicao(String nAutor){
		for (int i=0;i<CadastroDeAutores.size();i++){
			if (CadastroDeAutores.get(i).getNome().equalsIgnoreCase(nAutor)) {
				return i;
			}
		}
		return -1;
	}

	@Override
	public boolean remove(String nAutor){

		for (int i=0;i<CadastroDeAutores.size();i++){
			if (CadastroDeAutores.get(i).getNome().equalsIgnoreCase(nAutor)) {
				CadastroDeAutores.remove(i);
				return true;
			}
		}
		return false;
	}

	public List<Autor> getCadastroDeAutores() {
		return CadastroDeAutores;
	}
	public  void setCadastroDeAutores(List<Autor> cadastroDeAutores) {
		CadastroDeAutores = cadastroDeAutores;
	}

}
