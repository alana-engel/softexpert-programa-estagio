package outrasOperacoes;

public class CodSequencial {
	static int codL=0;
	static int codB=0;
	static int codC=0;
	static int codA=0;
	
	public int codLivro(){
		codL=codL+1;
		return codL;
	}
	public int codBarras(){
		codB=codB+1;
		return codB;
	}
	public  int codCategoria(){
		codC=codC+1;
		return codC;
	}
	public int codAutor(){
		codA=codA+1;
		return codA;
	}
}
