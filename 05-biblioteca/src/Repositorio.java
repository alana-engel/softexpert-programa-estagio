
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.swing.JOptionPane;

public class Repositorio {
	private static List<Livro> CadastroDeLivros = new ArrayList<Livro>();
	private static List<Categoria> CadastroDeCategoria = new ArrayList<Categoria>();
	private static List<Autor> CadastroDeAutores = new ArrayList<Autor>();

	public List<Livro> getCadastroDeLivros() {
		return CadastroDeLivros;
	}
	public void setCadastroDeLivros(List<Livro> cadastroDeLivros) {
		CadastroDeLivros = cadastroDeLivros;
	}	
	public List<Categoria> getCadastroDeCategoria() {
		return CadastroDeCategoria;
	}
	public void setCadastroDeCategoria(List<Categoria> cadastroDeCategoria) {
		CadastroDeCategoria = cadastroDeCategoria;
	}
	public List<Autor> getCadastroDeAutores() {
		return CadastroDeAutores;
	}
	public  void setCadastroDeAutores(List<Autor> cadastroDeAutores) {
		CadastroDeAutores = cadastroDeAutores;
	}

	public void ordena(){
		Collections.sort(getCadastroDeLivros());
	}
	void gravaDadosArquivoLivro(){
		try{
			ObjectOutputStream grava = new ObjectOutputStream(new FileOutputStream("CadastroDeLivros.dat"));
			grava.writeObject(getCadastroDeLivros());
			grava.close();
		}
		catch(Exception e){
			System.out.print("Erro: " + e);
			System.exit(1);
		}
		JOptionPane.showMessageDialog(null,"Dados gravados no arquivo 'CadastroDeLivros.dat'");

		try{
			ObjectOutputStream grava = new ObjectOutputStream(new FileOutputStream("CadastroDeAutores.dat"));
			grava.writeObject(CadastroDeAutores);
			grava.close();
		}
		catch(Exception e){
			System.out.print("Erro: " + e);
			System.exit(1);
		}
		JOptionPane.showMessageDialog(null,"Dados gravados no arquivo 'CadastroDeAutores.dat'");
		try{
			ObjectOutputStream grava = new ObjectOutputStream(new FileOutputStream("CadastroDeCategoria.dat"));
			grava.writeObject(CadastroDeCategoria);
			grava.close();
		}
		catch(Exception e){
			System.out.print("Erro: " + e);
			System.exit(1);
		}
		JOptionPane.showMessageDialog(null,"Dados gravados no arquivo 'CadastroDeCategoria.dat'");
	}
	void pegaDadosArquivoLivro(){
		try{
			FileInputStream CLivro= new FileInputStream("CadastroDeLivros.dat");
			ObjectInputStream cadastroDeLivro = new ObjectInputStream(CLivro);
			setCadastroDeLivros((ArrayList<Livro>) cadastroDeLivro.readObject());
			cadastroDeLivro.close();
		}
		catch(Exception e){
			System.out.print("Erro----->: " + e);
			System.exit(1);
		}
		JOptionPane.showMessageDialog(null,"Dados lidos do arquivo 'CadastroDeLivros.dat'");

		try{
			FileInputStream CAutores= new FileInputStream("CadastroDeAutores.dat");
			ObjectInputStream cadastroDeAutores = new ObjectInputStream(CAutores);
			CadastroDeAutores = (ArrayList<Autor>) cadastroDeAutores.readObject();
			cadastroDeAutores.close();
		}
		catch(Exception e){
			System.out.print("Erro----->: " + e);
			System.exit(1);
		}
		JOptionPane.showMessageDialog(null,"Dados lidos do arquivo 'CadastroDeAutores.dat'");
		try{
			FileInputStream CCategoria= new FileInputStream("CadastroDeCategoria.dat");
			ObjectInputStream cadastroDeCategoria = new ObjectInputStream(CCategoria);
			CadastroDeCategoria = (ArrayList<Categoria>) cadastroDeCategoria.readObject();
			cadastroDeCategoria.close();
		}
		catch(Exception e){
			System.out.print("Erro----->: " + e);
			System.exit(1);
		}
		JOptionPane.showMessageDialog(null,"Dados lidos do arquivo 'CadastroDeCategoria.dat'");
	}

}
