import java.io.Serializable;

public class Autor implements Serializable{
	private static final long serialVersionUID = 1L;
	private int codAutores;
	private String nome;
	private Data dataDeNascimento= new Data();;
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
	public Data getDataDeNascimento() {
		return dataDeNascimento;
	}
	public void setDataDeNascimento(Data data) {
		this.dataDeNascimento = data;
	}
	public String getNacionalidade() {
		return nacionalidade;
	}
	public void setNacionalidade(String nacionalidade) {
		this.nacionalidade = nacionalidade;
	}
	public String toString() {
		String autor="";
		autor=autor+"Código do Autor: "+getCodAutores()
		+"\n"+"Nome do Autor: "+getNome()+"\n"
		+"Data de Nascimento: "+getDataDeNascimento()
		+"\n"+"Nacionalidade: "+getNacionalidade();
		return autor;
	}

}
