package outrasOperacoes;

import java.util.Collections;

import operacoes.OperacoesDadosLivrosEmMemoria;


public class Ordenacao {
	OperacoesDadosLivrosEmMemoria grava= new OperacoesDadosLivrosEmMemoria();
	public void ordena(){
		Collections.sort(grava.getCadastroDeLivros());
	}
}
