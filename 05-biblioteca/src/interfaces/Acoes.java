package interfaces;



public interface Acoes<T> {
	
	boolean cadastra(T registro);
	
	boolean remove(String registro);
	
	boolean altera(T registro, int posicao);
	
	T busca(String nomeRegistro);

}
