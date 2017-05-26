package operacoes;

import java.util.ArrayList;
import java.util.List;

import biblioteca.Autor;
import biblioteca.Categoria;
import biblioteca.Livro;
import interacaoUsuario.AdicionaAutorAoLivro;
import interacaoUsuario.AdicionaCategoriaAoLivro;
import interacaoUsuario.CadastroAutorI;
import interacaoUsuario.CadastroCategoriaI;
import interfaces.Acoes;
import outrasOperacoes.CodSequencial;



public class OperacoesDadosLivrosEmMemoria implements Acoes<Livro> {
	private static List<Livro> CadastroDeLivros = new ArrayList<Livro>();
	CodSequencial cod = new CodSequencial();
	AdicionaCategoriaAoLivro adicionaCategoriaAoLivro = new AdicionaCategoriaAoLivro();
	AdicionaAutorAoLivro  adicionaAutorAoLivro = new AdicionaAutorAoLivro();
	OperacoesDadosCategoriaEmMemoria gravaCategoria= new OperacoesDadosCategoriaEmMemoria();
	OperacoesDadosAutorEmMemoria gravaAutor= new OperacoesDadosAutorEmMemoria();
	Livro livro = new Livro();
	boolean achou = true;
	int posicaoDoLivro;

	
	@Override
	public boolean cadastra(Livro registro) {
		registro.setCodLivro(cod.codLivro());
		registro.setCodigoDeBarras(cod.codBarras());
		
		if (registro.getTitulo().isEmpty() || registro.getLocal().isEmpty()) {
			return false;
		}else{
		CadastroDeLivros.add(registro);
		}
		return true;
	}
public List<Autor> adicionaAutoresAoLivro(){
		
		Autor a = new Autor();
		CadastroAutorI cadastroDeAutor = new CadastroAutorI();
		List<Autor> listaAutores = new ArrayList<>();
		String qnt;
		qnt=adicionaAutorAoLivro.recebeQntAutores();
		if(qnt.isEmpty()){
			do{
			qnt=adicionaAutorAoLivro.recebeQntAutores();
			}while(qnt.isEmpty());
		}
		int q=Integer.parseInt(qnt);
			for(int i=0;i<q;i++){
					a = comparaAutores(a, cadastroDeAutor);
			}
				listaAutores.add(a);
		return listaAutores;
	}


	private Autor comparaAutores(Autor a, CadastroAutorI cadastroDeAutor) {
		boolean achou = false;
		adicionaAutorAoLivro.recebeNomeAutor();
		for (int j=0;j<gravaAutor.getCadastroDeAutores().size();j++){
			
			if (gravaAutor.getCadastroDeAutores().get(j).getNome().equalsIgnoreCase(adicionaAutorAoLivro.retornaNomeAutor())) {
				a = gravaAutor.getCadastroDeAutores().get(j);
				achou = true;
			}
			
		}
		if (!achou){
			adicionaAutorAoLivro.retornaMensagemAutorNaoEncontrado();
			cadastroDeAutor.cadastra();
			for (int j=0;j<gravaAutor.getCadastroDeAutores().size();j++){
				if (gravaAutor.getCadastroDeAutores().get(j).getNome().equalsIgnoreCase(adicionaAutorAoLivro.retornaNomeAutor())) {
					a = gravaAutor.getCadastroDeAutores().get(j);
					achou = true;
				}
			}
		}
		return a;
	}

	public Categoria adicionaCategoria(){
		Categoria c1 = new Categoria();
		CadastroCategoriaI cadastroCategoria = new CadastroCategoriaI();
		String dCategoria=adicionaCategoriaAoLivro.retornaDescricaoCategoria();
			boolean achou = false;
			for (int i=0;i<gravaCategoria.getCadastroDeCategoria().size();i++){
				if (gravaCategoria.getCadastroDeCategoria().get(i).getDescricao().equalsIgnoreCase(dCategoria)) {
					c1 = gravaCategoria.getCadastroDeCategoria().get(i);
					achou = true;
				}
			}
			if (!achou){
				adicionaCategoriaAoLivro.retornaMensagemCategoriaNaoEncontrado();
				
				cadastroCategoria.cadastra();
				for (int i=0;i<gravaCategoria.getCadastroDeCategoria().size();i++){
					if (gravaCategoria.getCadastroDeCategoria().get(i).getDescricao().equalsIgnoreCase(dCategoria)) {
						c1 = gravaCategoria.getCadastroDeCategoria().get(i);
						achou = true;
					}
				}
			}
		
		return c1;

	}

	public boolean remove(String TLivro){
			boolean exclui = false;
			for (int i=0;i<getCadastroDeLivros().size();i++){
				if (getCadastroDeLivros().get(i).getTitulo().equalsIgnoreCase(TLivro)) {
					getCadastroDeLivros().remove(i);
					exclui= true;
				}else{
					exclui= false;
				}
			}
			return exclui;
		}
	

	@Override
	public boolean altera(Livro registro, int posicao) {
		if(registro.getTitulo().isEmpty()){
			return false;
		}else{
		CadastroDeLivros.get(posicao).setTitulo(registro.getTitulo());
		CadastroDeLivros.get(posicao).setResumo(registro.getResumo());
		CadastroDeLivros.get(posicao).setQntPaginas(registro.getQntPaginas());
		CadastroDeLivros.get(posicao).setLocal(registro.getLocal());
		CadastroDeLivros.get(posicao).setDataDeAquisicao(registro.getDataDeAquisicao());;
		CadastroDeLivros.get(posicao).setLista(registro.getLista());
		CadastroDeLivros.get(posicao).setCategoria(registro.getCategoria());
		return true;
		}
	}

	@Override
	public Livro busca(String nomeRegistro) {
		return null;
	}
	public Livro buscaCod(int n){
		achou=false;
		for (int i=0;i<CadastroDeLivros.size();i++){
			if (CadastroDeLivros.get(i).getCodLivro()==n) {
				livro = CadastroDeLivros.get(i);
				achou = true;
				posicaoLivro(i);
			}
		}
		
		return livro;
	}
	
	public List<Livro> buscaTitulo(String titulo){
		achou=false;
		List<Livro> listaPorTitulo = new ArrayList<>();
		for (int i=0;i<CadastroDeLivros.size();i++){
			if (CadastroDeLivros.get(i).getTitulo().contains(titulo)) {
				listaPorTitulo.add(CadastroDeLivros.get(i));
				achou = true;
			}
		}
		return listaPorTitulo;
	} 

	public List<Livro> buscaCategoria(String categoria){
		achou=false;
		List<Livro> listaPorCategoria = new ArrayList<>();
		for (int i=0;i<CadastroDeLivros.size();i++){
			if (CadastroDeLivros.get(i).getCategoria().getDescricao().equalsIgnoreCase(categoria)) {
				listaPorCategoria.add(CadastroDeLivros.get(i));
				achou = true;
			}
		}
		return listaPorCategoria;
	}

	public List<Livro> buscaAutor(String n){
		List<Livro> listaPorAutor = new ArrayList<>();
		List<Autor> listap = new ArrayList<>();
		achou = false;
		for (int i=0;i<CadastroDeLivros.size();i++){
			listap=CadastroDeLivros.get(i).getLista();
			buscaNomeAutor(n, listaPorAutor, listap, i);
		}
	return listaPorAutor;

	}
	
	private void buscaNomeAutor(String n, List<Livro> listaPorAutor, List<Autor> listap, int i) {
		Autor a;
		for (int j=0;j<listap.size();j++){
			a=listap.get(j);
			if (a.getNome().equalsIgnoreCase(n)) {
				listaPorAutor.add(CadastroDeLivros.get(i));
				achou = true;
			}
		}
	}
	
	public boolean achou(){
		return achou;
	}
	void posicaoLivro(int n){
		posicaoDoLivro = n;
	}
	public int retornaPosicao(){
		return posicaoDoLivro;
	}

	public List<Livro> getCadastroDeLivros() {
		return CadastroDeLivros;
	}
	public void setCadastroDeLivros(List<Livro> cadastroDeLivros) {
		CadastroDeLivros = cadastroDeLivros;
	}	
}
