package br.com.softexpert.biblioteca.outrasoperacoes;

import java.util.Collections;

import br.com.softexpert.biblioteca.operacoesregistros.OperacoesDadosLivrosEmMemoria;

public class Ordenacao {
	private OperacoesDadosLivrosEmMemoria operacoesLivro= new OperacoesDadosLivrosEmMemoria();
	public void ordena(){
		Collections.sort(operacoesLivro.getCadastroDeLivros());
	}
}
