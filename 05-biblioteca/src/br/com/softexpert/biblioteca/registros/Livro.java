
package br.com.softexpert.biblioteca.registros;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Date;
public class Livro  implements Comparable<Livro>, Serializable{
	private static final long serialVersionUID = 1L;
	private int codLivro;
	private String titulo;
	private String resumo;
	private int codigoDeBarras;
	private int qntPaginas;
	private String local;
	private Date dataDeAquisicao;
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
	public Date getDataDeAquisicao() {
		return dataDeAquisicao;
	}
	public void setDataDeAquisicao(Date data) {
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
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		String aquisicao=sdf.format(getDataDeAquisicao());
		String imprime="";
		if(sdf.format(getDataDeAquisicao()).equals("01/01/0001"))
			aquisicao="";
		imprime=imprime+"Código do livro: "+getCodLivro()+"\n"+"Titulo do livro: "+getTitulo()
		+"\n"+"Resumo do livro: "+getResumo()+"\n"+"Código de Barras: "+getCodigoDeBarras()
		+"\n"+"Quantidade de páginas: "+getQntPaginas()+"\n"+"Local: "+getLocal()
		+"\n"+"Data de Aquisição: "+aquisicao
		+"\n"+infoDeAutores()+"\n"+getCategoria()+
		"\n\n\n";
		return imprime;
	}
	private String infoDeAutores() {
		StringBuilder builder = new StringBuilder();
		for (Autor autor : listaDeAutores) {
			builder.append("\n"+autor);
		}
		return builder.toString();
	}

}
