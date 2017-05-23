import java.io.Serializable;

public class Categoria implements Serializable{
	private static final long serialVersionUID = -9098599194068402992L;
	private int codCategoria;
	private String descricao;
	public int getCodCategoria() {
		return codCategoria;
	}
	public void setCodCategoria(int codCategoria) {
		this.codCategoria = codCategoria;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String toString() {
		String c="";
		c=c+"Código da Categoria: "+getCodCategoria()
		+"\n"+"Descrição da Categoria: "+getDescricao();
		return c;
	}
}
