package br.com.softexpert.biblioteca.operacoesregistros;

import java.util.ArrayList;
import java.util.List;

import br.com.softexpert.biblioteca.interfaces.Acoes;
import br.com.softexpert.biblioteca.outrasoperacoes.CodSequencial;
import br.com.softexpert.biblioteca.registros.Categoria;

public class OperacoesDadosCategoriaEmMemoria implements Acoes<Categoria>{

	private static List<Categoria> CadastroDeCategoria = new ArrayList<Categoria>();
	private Categoria categoria = new Categoria();
	private CodSequencial cod = new CodSequencial();
	private boolean achou = false;
	private int posicaoDoLivro;

	@Override
	public boolean cadastra(Categoria c){
		categoria=c;
		categoria.setCodCategoria(cod.codCategoria());
		if (categoria.getDescricao().isEmpty()) {
			return false;
		}else{
			CadastroDeCategoria.add(categoria);
		}
		return true;
	}
	@Override
	public boolean altera(Categoria c, int posicao){
		CadastroDeCategoria.get(posicao).setDescricao(c.getDescricao());  
		return true;
	}
	@Override
	public Categoria busca(String dCategoria){
		Categoria c = new Categoria();
		achou= false;
		for (int i=0;i<CadastroDeCategoria.size();i++){
			if (CadastroDeCategoria.get(i).getDescricao().equalsIgnoreCase(dCategoria)) {
				c = CadastroDeCategoria.get(i);
				achou = true;
				posicaoDoLivro = i;
			}
		}
		return c;
	}

	public int retornaPosicao(){
		return posicaoDoLivro;
	}
	public boolean achou(){
		return achou;
	}
	@Override
	public boolean remove(String dcategoria){
		
		for (int i=0;i<CadastroDeCategoria.size();i++){
			if (CadastroDeCategoria.get(i).getDescricao().equalsIgnoreCase(dcategoria)) {
				CadastroDeCategoria.remove(i);
				return true;
			}
		}
		return false;
		
	}

	public List<Categoria> getCadastroDeCategoria() {
		return CadastroDeCategoria;
	}
	public void setCadastroDeCategoria(List<Categoria> cadastroDeCategoria) {
		CadastroDeCategoria = cadastroDeCategoria;
	}

}
