package br.com.softexpert.biblioteca.registros;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
public class Autor implements Serializable{
	private static final long serialVersionUID = 1L;
	private int codAutores;
	private String nome;
	private Date dataDeNascimento;
	private String nacionalidade;
	public int getCodAutores() {
		return codAutores;
	}
	public void setCodAutores(int codAutores) {
		this.codAutores = codAutores;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public Date getDataDeNascimento() {
		return dataDeNascimento;
	}
	public void setDataDeNascimento(Date data) {
		this.dataDeNascimento = data;
	}
	public String getNacionalidade() {
		return nacionalidade;
	}
	public void setNacionalidade(String nacionalidade) {
		this.nacionalidade = nacionalidade;
	}
	public String toString() {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		String nascimento=sdf.format(getDataDeNascimento());
		if(sdf.format(getDataDeNascimento()).equals("01/01/0001"))
			nascimento="";
		String autor="";
		autor=autor+"Código do Autor: "+getCodAutores()
		+"\n"+"Nome do Autor: "+getNome()+"\n"
		+"Data de Nascimento: "+nascimento
		+"\n"+"Nacionalidade: "+getNacionalidade();
		return autor;
	}

}
