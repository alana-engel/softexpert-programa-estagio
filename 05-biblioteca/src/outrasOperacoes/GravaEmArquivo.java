package outrasOperacoes;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;


import javax.swing.JOptionPane;

import biblioteca.Autor;
import biblioteca.Categoria;
import biblioteca.Livro;
import operacoes.OperacoesDadosAutorEmMemoria;
import operacoes.OperacoesDadosCategoriaEmMemoria;
import operacoes.OperacoesDadosLivrosEmMemoria;

public class GravaEmArquivo {
	OperacoesDadosLivrosEmMemoria gravaLivro= new OperacoesDadosLivrosEmMemoria();
	OperacoesDadosCategoriaEmMemoria gravaCategoria= new OperacoesDadosCategoriaEmMemoria();
	OperacoesDadosAutorEmMemoria gravaAutor= new OperacoesDadosAutorEmMemoria();
	
	public void gravaDadosArquivoLivro(){
		try{
			ObjectOutputStream grava = new ObjectOutputStream(new FileOutputStream("CadastroDeLivros.dat"));
			grava.writeObject(gravaLivro.getCadastroDeLivros());
			grava.close();
		}
		catch(Exception e){
			System.out.print("Erro: " + e);
			System.exit(1);
		}
		JOptionPane.showMessageDialog(null,"Dados gravados no arquivo 'CadastroDeLivros.dat'");

		gravaDadosAutor();
		gravaDadosCategoria();
	}
	private void gravaDadosCategoria() {
		try{
			ObjectOutputStream grava = new ObjectOutputStream(new FileOutputStream("CadastroDeCategoria.dat"));
			grava.writeObject(gravaCategoria.getCadastroDeCategoria());
			grava.close();
		}
		catch(Exception e){
			System.out.print("Erro: " + e);
			System.exit(1);
		}
		JOptionPane.showMessageDialog(null,"Dados gravados no arquivo 'CadastroDeCategoria.dat'");
	}
	private void gravaDadosAutor() {
		try{
			ObjectOutputStream grava = new ObjectOutputStream(new FileOutputStream("CadastroDeAutores.dat"));
			grava.writeObject(gravaAutor.getCadastroDeAutores());
			grava.close();
		}
		catch(Exception e){
			System.out.print("Erro: " + e);
			System.exit(1);
		}
		JOptionPane.showMessageDialog(null,"Dados gravados no arquivo 'CadastroDeAutores.dat'");
	}
	public void pegaDadosArquivoLivro(){
		try{
			FileInputStream CLivro= new FileInputStream("CadastroDeLivros.dat");
			ObjectInputStream cadastroDeLivro = new ObjectInputStream(CLivro);
			gravaLivro.setCadastroDeLivros((ArrayList<Livro>) cadastroDeLivro.readObject());
			cadastroDeLivro.close();
		}
		catch(Exception e){
			System.out.print("Erro----->: " + e);
			System.exit(1);
		}
		JOptionPane.showMessageDialog(null,"Dados lidos do arquivo 'CadastroDeLivros.dat'");

		pegaDadosAutor();
		pegaDadosCategoria();
	}
	private void pegaDadosCategoria() {
		try{
			FileInputStream CCategoria= new FileInputStream("CadastroDeCategoria.dat");
			ObjectInputStream cadastroDeCategoria = new ObjectInputStream(CCategoria);
			gravaCategoria.setCadastroDeCategoria((ArrayList<Categoria>) cadastroDeCategoria.readObject());
			cadastroDeCategoria.close();
		}
		catch(Exception e){
			System.out.print("Erro----->: " + e);
			System.exit(1);
		}
		JOptionPane.showMessageDialog(null,"Dados lidos do arquivo 'CadastroDeCategoria.dat'");
	}
	private void pegaDadosAutor() {
		try{
			FileInputStream CAutores= new FileInputStream("CadastroDeAutores.dat");
			ObjectInputStream cadastroDeAutores = new ObjectInputStream(CAutores);
			gravaAutor.setCadastroDeAutores((ArrayList<Autor>) cadastroDeAutores.readObject());
			cadastroDeAutores.close();
		}
		catch(Exception e){
			System.out.print("Erro----->: " + e);
			System.exit(1);
		}
		JOptionPane.showMessageDialog(null,"Dados lidos do arquivo 'CadastroDeAutores.dat'");
	}

}
