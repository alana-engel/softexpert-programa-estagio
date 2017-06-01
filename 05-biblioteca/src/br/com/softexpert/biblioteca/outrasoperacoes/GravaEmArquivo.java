package br.com.softexpert.biblioteca.outrasoperacoes;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;


import javax.swing.JOptionPane;

import br.com.softexpert.biblioteca.operacoesregistros.OperacoesDadosAutorEmMemoria;
import br.com.softexpert.biblioteca.operacoesregistros.OperacoesDadosCategoriaEmMemoria;
import br.com.softexpert.biblioteca.operacoesregistros.OperacoesDadosLivrosEmMemoria;
import br.com.softexpert.biblioteca.registros.Autor;
import br.com.softexpert.biblioteca.registros.Categoria;
import br.com.softexpert.biblioteca.registros.Livro;

public class GravaEmArquivo {
	OperacoesDadosLivrosEmMemoria gravaLivro= new OperacoesDadosLivrosEmMemoria();
	OperacoesDadosCategoriaEmMemoria gravaCategoria= new OperacoesDadosCategoriaEmMemoria();
	OperacoesDadosAutorEmMemoria gravaAutor= new OperacoesDadosAutorEmMemoria();
	CodSequencial codigo = new CodSequencial();
	
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

		gravaDadosAutor();
		gravaDadosCategoria();
		
		try{
			ObjectOutputStream grava = new ObjectOutputStream(new FileOutputStream("CodigoC.dat"));
			grava.writeObject(codigo.codCategoria());
			grava.close();
		}
		catch(Exception e){
			System.out.print("Erro: " + e);
			System.exit(1);
		}
		try{
			ObjectOutputStream grava = new ObjectOutputStream(new FileOutputStream("CodigoL.dat"));
			grava.writeObject(codigo.codLivro());
			grava.close();
		}
		catch(Exception e){
			System.out.print("Erro: " + e);
			System.exit(1);
		}
		try{
			ObjectOutputStream grava = new ObjectOutputStream(new FileOutputStream("CodigoB.dat"));
			grava.writeObject(codigo.codBarras());
			grava.close();
		}
		catch(Exception e){
			System.out.print("Erro: " + e);
			System.exit(1);
		}
		try{
			ObjectOutputStream grava = new ObjectOutputStream(new FileOutputStream("CodigoA.dat"));
			grava.writeObject(codigo.codAutor());
			grava.close();
		}
		catch(Exception e){
			System.out.print("Erro: " + e);
			System.exit(1);
		}

		
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
		

		pegaDadosAutor();
		pegaDadosCategoria();
		
		try{
			FileInputStream cod= new FileInputStream("CodigoC.dat");
			ObjectInputStream codS = new ObjectInputStream(cod);
			codigo.codCategoria();
			codS.close();
		}
		catch(Exception e){
			System.out.print("Erro----->: " + e);
			System.exit(1);
		}
		try{
			FileInputStream cod= new FileInputStream("CodigoA.dat");
			ObjectInputStream codS = new ObjectInputStream(cod);
			codigo.codAutor();
			codS.close();
		}
		catch(Exception e){
			System.out.print("Erro----->: " + e);
			System.exit(1);
		}
		try{
			FileInputStream cod= new FileInputStream("CodigoL.dat");
			ObjectInputStream codS = new ObjectInputStream(cod);
			codigo.codLivro();
			codS.close();
		}
		catch(Exception e){
			System.out.print("Erro----->: " + e);
			System.exit(1);
		}
		try{
			FileInputStream cod= new FileInputStream("CodigoB.dat");
			ObjectInputStream codS = new ObjectInputStream(cod);
			codigo.codBarras();
			codS.close();
		}
		catch(Exception e){
			System.out.print("Erro----->: " + e);
			System.exit(1);
		}
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
	
	}

}
