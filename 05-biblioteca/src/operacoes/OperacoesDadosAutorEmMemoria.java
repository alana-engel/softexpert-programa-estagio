package operacoes;

import java.util.ArrayList;
import java.util.List;

import biblioteca.Autor;

import interfaces.Acoes;
import outrasOperacoes.CodSequencial;

public class OperacoesDadosAutorEmMemoria implements Acoes<Autor>{
	private static List<Autor> CadastroDeAutores = new ArrayList<Autor>();
	CodSequencial cod = new CodSequencial();
	int posicaoDoLivro;
	boolean achou = false;

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
				achou = true;
				posicaoDoLivro=i;
			}
		}
		return autor;
	}
	void posicaoLivro(int n){
		posicaoDoLivro = n;
	}
	public int retornaPosicao(){
		return posicaoDoLivro;
	}

	public boolean achou(){
		return achou;
	}
	public List<Autor> getCadastroDeAutores() {
		return CadastroDeAutores;
	}
	public  void setCadastroDeAutores(List<Autor> cadastroDeAutores) {
		CadastroDeAutores = cadastroDeAutores;
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
}
