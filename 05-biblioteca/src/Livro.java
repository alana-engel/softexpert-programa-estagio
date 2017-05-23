import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Livro  implements Comparable<Livro>, Serializable{
	private static final long serialVersionUID = 1L;
	private int codLivro;
	private String titulo;
	private String resumo;
	private int codigoDeBarras;
	private int qntPaginas;
	private String local;
	private Data dataDeAquisicao = new Data();
	private List<Autor> listaDeAutores = new ArrayList<>();
	private Categoria categoria;

	public Livro(){

	}

	public int getCodLivro() {
		return codLivro;
	}

	public void setCodLivro(int codLivro) {
		this.codLivro = codLivro;
	}


	public String getTitulo() {
		return titulo;
	}


	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}


	public String getResumo() {
		return resumo;
	}


	public void setResumo(String resumo) {
		this.resumo = resumo;
	}


	public int getCodigoDeBarras() {
		return codigoDeBarras;
	}


	public void setCodigoDeBarras(int codigoDeBarras) {
		this.codigoDeBarras = codigoDeBarras;
	}


	public int getQntPaginas() {
		return qntPaginas;
	}


	public void setQntPaginas(int qntPaginas) {
		this.qntPaginas = qntPaginas;
	}


	public String getLocal() {
		return local;
	}


	public void setLocal(String local) {
		this.local = local;
	}


	public Data getDataDeAquisicao() {
		return dataDeAquisicao;
	}


	public void setDataDeAquisicao(Data data) {
		this.dataDeAquisicao = data;
	}


	public Categoria getCategoria() {
		return categoria;
	}


	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}


	public List<Autor> getLista() {
		return listaDeAutores;
	}


	public void setLista(List<Autor> lista) {
		this.listaDeAutores = lista;
	}

	@Override
	public int compareTo(Livro o) {
		Livro e = (Livro) o; return this.titulo.compareToIgnoreCase(e.getTitulo());
	}

	@Override
	public String toString() {
		//StringBuilder builder = new StringBuilder();
		//builder.append("Código "+codLivro);
		//builder.append("\nAutores: "+infoDeAutores());
		String imprime="";
		imprime=imprime+"Código do livro: "+getCodLivro()+"\n"+"Titulo do livro: "+getTitulo()
		+"\n"+"Resumo do livro: "+getResumo()+"\n"+"Código de Barras: "+getCodigoDeBarras()
		+"\n"+"Quantidade de páginas: "+getQntPaginas()+"\n"+"Local: "+getLocal()
		+"\n"+"Data de Aquisição: "+getDataDeAquisicao()
		+"\n"+infoDeAutores()+"\n"+getCategoria()+
		"\n\n\n";
		return imprime;
	}

	private String infoDeAutores() {
		StringBuilder builder = new StringBuilder();
		for (Autor autor : listaDeAutores) {
			builder.append(autor);
		}
		return builder.toString();
	}


}
