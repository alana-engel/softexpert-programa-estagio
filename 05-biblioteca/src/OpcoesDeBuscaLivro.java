import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

public class OpcoesDeBuscaLivro {
	OperacoesAutores oa=new OperacoesAutores();
	OperacoesCategoria oc =new OperacoesCategoria();
	Livro livro = new Livro();
	Repositorio g = new Repositorio();

	public void buscaCod(){
		int n=Integer.parseInt(JOptionPane.showInputDialog("Digite o código do livro: "));

		boolean achou = false;
		for (int i=0;i<g.getCadastroDeLivros().size();i++){
			if (g.getCadastroDeLivros().get(i).getCodLivro()==n) {
				livro = g.getCadastroDeLivros().get(i);
				achou = true;
			}
		}

		if (!achou){
			JOptionPane.showMessageDialog(null,"Não foi possível encontrar o livro.");
		}
		else{
			JOptionPane.showMessageDialog(null, livro);
		}
	}

	public void buscaTitulo(){
		g.ordena();

		String n=(JOptionPane.showInputDialog("Digite o titulo do livro: "));
		boolean achou = false;
		for (int i=0;i<g.getCadastroDeLivros().size();i++){
			if (g.getCadastroDeLivros().get(i).getTitulo().equalsIgnoreCase(n)) {
				livro = g.getCadastroDeLivros().get(i);
				achou = true;
			}
		}

		if (!achou){
			JOptionPane.showMessageDialog(null,"Não foi possível encontrar o livro.");
		}
		else{
			JOptionPane.showMessageDialog(null, livro);
		}
	} 

	public void buscaCategoria(){
		g.ordena();
		String imp=new String();
		List<Livro> listaPorCategoria = new ArrayList<>();
		String n=(JOptionPane.showInputDialog("Digite a categoria do livro: "));
		boolean achou = false;
		for (int i=0;i<g.getCadastroDeLivros().size();i++){
			if (g.getCadastroDeLivros().get(i).getCategoria().getDescricao().equalsIgnoreCase(n)) {
				listaPorCategoria.add(g.getCadastroDeLivros().get(i));
				achou = true;
			}
		}

		if (!achou){
			JOptionPane.showMessageDialog(null,"Não foi possível encontrar o livro");
		}
		else{
			for (int i=0;i<listaPorCategoria.size();i++){
				Livro livro = new Livro();
				livro=listaPorCategoria.get(i);
				imp=imp+livro;
			}
			JOptionPane.showMessageDialog(null, imp);
		}
	}

	public void buscaAutor(){
		g.ordena();
		List<Livro> listaPorAutor = new ArrayList<>();
		List<Autor> listap = new ArrayList<>();
		Autor a = new Autor();
		String imp=new String();
		String n=(JOptionPane.showInputDialog("Digite o autor do livro: "));
		boolean achou = false;
		for (int i=0;i<g.getCadastroDeLivros().size();i++){
			listap=g.getCadastroDeLivros().get(i).getLista();
			for (int j=0;j<listap.size();j++){
				a=listap.get(j);
				if (a.getNome().equalsIgnoreCase(n)) {
					listaPorAutor.add(g.getCadastroDeLivros().get(i));
					achou = true;
				}
			}
		}
		if (!achou){
			JOptionPane.showMessageDialog(null,"Não foi possível encontrar o livro.");
		}else{
			for (int cont=0;cont<listaPorAutor.size();cont++){
				Livro livro = new Livro();
				livro=listaPorAutor.get(cont);
				imp=imp+livro;

			}
			JOptionPane.showMessageDialog(null, imp);
		}

	}

}
