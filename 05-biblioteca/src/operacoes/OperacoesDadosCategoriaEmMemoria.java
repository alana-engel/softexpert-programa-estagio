package operacoes;

import java.util.ArrayList;
import java.util.List;

import biblioteca.Categoria;
import interfaces.Acoes;
import outrasOperacoes.CodSequencial;

public class OperacoesDadosCategoriaEmMemoria implements Acoes<Categoria>{

	private static List<Categoria> CadastroDeCategoria = new ArrayList<Categoria>();
	Categoria categoria = new Categoria();
	CodSequencial cod = new CodSequencial();
	boolean achou = false;
	int posicaoDoLivro;

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

	void posicaoLivro(int n){
		posicaoDoLivro = n;
	}
	public int retornaPosicao(){
		return posicaoDoLivro;
	}


	public boolean achou(){
		return achou;
	}
	@Override
	public boolean remove(String dcategoria){
		boolean exclui = false;
		for (int i=0;i<CadastroDeCategoria.size();i++){
			if (CadastroDeCategoria.get(i).getDescricao().equalsIgnoreCase(dcategoria)) {
				CadastroDeCategoria.remove(i);
				exclui = true;
			}else
				exclui =false;
		}
		return exclui;
	}

	public List<Categoria> getCadastroDeCategoria() {
		return CadastroDeCategoria;
	}
	public void setCadastroDeCategoria(List<Categoria> cadastroDeCategoria) {
		CadastroDeCategoria = cadastroDeCategoria;
	}

}
