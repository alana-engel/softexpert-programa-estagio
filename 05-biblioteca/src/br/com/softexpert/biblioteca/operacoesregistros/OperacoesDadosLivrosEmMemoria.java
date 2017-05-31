package br.com.softexpert.biblioteca.operacoesregistros;

import java.util.ArrayList;
import java.util.List;

import br.com.softexpert.biblioteca.interacaousuario.AdicionaAutorAoLivro;
import br.com.softexpert.biblioteca.interacaousuario.AdicionaCategoriaAoLivro;
import br.com.softexpert.biblioteca.interacaousuario.CadastroAutorI;
import br.com.softexpert.biblioteca.interacaousuario.CadastroCategoriaI;
import br.com.softexpert.biblioteca.interfaces.Acoes;
import br.com.softexpert.biblioteca.outrasoperacoes.CodSequencial;
import br.com.softexpert.biblioteca.registros.Autor;
import br.com.softexpert.biblioteca.registros.Categoria;
import br.com.softexpert.biblioteca.registros.Livro;



public class OperacoesDadosLivrosEmMemoria implements Acoes<Livro> {
	private static List<Livro> CadastroDeLivros = new ArrayList<Livro>();
	private AdicionaAutorAoLivro  adicionaAutorAoLivro = new AdicionaAutorAoLivro();
	private boolean achou = true;
	private OperacoesDadosAutorEmMemoria operacoesAutor= new OperacoesDadosAutorEmMemoria();
	@Override
	public boolean cadastra(Livro registro) {
		CodSequencial cod = new CodSequencial();
		registro.setCodLivro(cod.codLivro());
		registro.setCodigoDeBarras(cod.codBarras());
		if (registro.getTitulo().isEmpty() || registro.getLocal().isEmpty()){
			return false;
		}else{
			CadastroDeLivros.add(registro);
		}
		return true;
	}
	
	public List<Autor> adicionaAutoresAoLivro(String qnt){
		OperacoesDadosAutorEmMemoria gravaAutor= new OperacoesDadosAutorEmMemoria();
		Autor a = new Autor();
		List<Autor> listaAutores = new ArrayList<>();
		int q=Integer.parseInt(qnt);
		for(int i=0;i<q;i++){
		String nome = adicionaAutorAoLivro.recebeNomeAutor();
		int existe=operacoesAutor.verificaSeAutorExisteRetornaPosicao(nome);
		int p=existe;
			if(p!=-1){
				a = gravaAutor.getCadastroDeAutores().get(p);
			}
			else{
			a = verificaAutorParaAdicionar(a,p, nome);
			}
		listaAutores.add(a);
		}
		return listaAutores;
	}
	private Autor verificaAutorParaAdicionar(Autor a, int p, String nome) {
		boolean achou = false;
		if (!achou){
			a = cadastrarAutorNaoEncontrado(a,p, nome);
		}
		return a;
	}
	private Autor cadastrarAutorNaoEncontrado(Autor a, int p, String nome) {
		OperacoesDadosAutorEmMemoria gravaAutor= new OperacoesDadosAutorEmMemoria();
		CadastroAutorI cadastroDeAutor = new CadastroAutorI();
		cadastroDeAutor.retornaMensagemAutorNaoEncontrado();
		cadastroDeAutor.cadastra();
		for (int j=0;j<gravaAutor.getCadastroDeAutores().size();j++){
			if (gravaAutor.getCadastroDeAutores().get(j).getNome().equalsIgnoreCase(nome)) {
				a = gravaAutor.getCadastroDeAutores().get(j);
				achou = true;
			}
		}
		return a;
	}
	
	public Categoria adicionaCategoria(){
		AdicionaCategoriaAoLivro adicionaCategoriaAoLivro = new AdicionaCategoriaAoLivro();
		OperacoesDadosCategoriaEmMemoria gravaCategoria= new OperacoesDadosCategoriaEmMemoria();
		Categoria c1 = new Categoria();
		CadastroCategoriaI cadastroCategoria = new CadastroCategoriaI();
		String dCategoria=adicionaCategoriaAoLivro.retornaDescricaoCategoria();
		if(dCategoria.isEmpty()){
			do{
				dCategoria=adicionaCategoriaAoLivro.retornaDescricaoCategoria();
			}while(dCategoria.isEmpty());
		}
		c1 = verificaCategoriaParaAdicionar(adicionaCategoriaAoLivro, gravaCategoria, c1, cadastroCategoria, dCategoria);
		return c1;
	}
	private Categoria verificaCategoriaParaAdicionar(AdicionaCategoriaAoLivro adicionaCategoriaAoLivro,
			OperacoesDadosCategoriaEmMemoria gravaCategoria, Categoria c1, CadastroCategoriaI cadastroCategoria,
			String dCategoria) {
		boolean achou = false;
		for (int i=0;i<gravaCategoria.getCadastroDeCategoria().size();i++){
			if (gravaCategoria.getCadastroDeCategoria().get(i).getDescricao().equalsIgnoreCase(dCategoria)) {
				c1 = gravaCategoria.getCadastroDeCategoria().get(i);
				achou = true;
			}
		}
		if (!achou){
			c1 = cadastraCategoriaNaoEncontrada(adicionaCategoriaAoLivro, gravaCategoria, c1, cadastroCategoria, dCategoria);
		}
		return c1;
	}
	private Categoria cadastraCategoriaNaoEncontrada(AdicionaCategoriaAoLivro adicionaCategoriaAoLivro,
			OperacoesDadosCategoriaEmMemoria gravaCategoria, Categoria c1, CadastroCategoriaI cadastroCategoria,
			String dCategoria) {
		adicionaCategoriaAoLivro.retornaMensagemCategoriaNaoEncontrado();

		cadastroCategoria.cadastra();
		for (int i=0;i<gravaCategoria.getCadastroDeCategoria().size();i++){
			if (gravaCategoria.getCadastroDeCategoria().get(i).getDescricao().equalsIgnoreCase(dCategoria)) {
				c1 = gravaCategoria.getCadastroDeCategoria().get(i);
				achou = true;
			}
		}
		return c1;
	}
	
	
	public boolean remove(String TLivro){
		for (int i=0;i<getCadastroDeLivros().size();i++){
			if (getCadastroDeLivros().get(i).getTitulo().equalsIgnoreCase(TLivro)) {
				getCadastroDeLivros().remove(i);
				return true;
			}
		}
		return false;
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
			CadastroDeLivros.get(posicao).setDataDeAquisicao(registro.getDataDeAquisicao());
			CadastroDeLivros.get(posicao).setLista(registro.getLista());
			CadastroDeLivros.get(posicao).setCategoria(registro.getCategoria());
			return true;
		}
	}
	
	
	@Override
	public Livro busca(String nomeRegistro) {
		return null;
	}
	public int verificaSeLivroExisteRetornaPosicao(int n){
		for (int i=0;i<CadastroDeLivros.size();i++){
			if (CadastroDeLivros.get(i).getCodLivro()==n) {
				CadastroDeLivros.get(i);
				return i;
			}
		}
		return -1;
	}
	public Livro buscaCod(int n){
		Livro livro = new Livro();
		achou=false;
		for (int i=0;i<CadastroDeLivros.size();i++){
			if (CadastroDeLivros.get(i).getCodLivro()==n) {
				livro = CadastroDeLivros.get(i);
				achou = true;
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

	
	public List<Livro> getCadastroDeLivros() {
		return CadastroDeLivros;
	}
	public void setCadastroDeLivros(List<Livro> cadastroDeLivros) {
		CadastroDeLivros = cadastroDeLivros;
	}	
}
