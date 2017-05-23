
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

public class OperacoesLivro implements Opcoes {
	OpcoesDeBuscaLivro b = new OpcoesDeBuscaLivro();
	OperacoesAutores oa=new OperacoesAutores();
	OperacoesCategoria oc =new OperacoesCategoria();
	Livro livro = new Livro();
	Repositorio grava = new Repositorio();
	Autor a = new Autor();
	Categoria c1 = new Categoria();

	public void cadastra(){
		Livro livro = new Livro();
		livro.setCodLivro(grava.getCadastroDeLivros().size() +1);
		livro.setCodigoDeBarras(grava.getCadastroDeLivros().size() +1);
		livro.setTitulo(JOptionPane.showInputDialog("Digite o titulo do livro: "));
		livro.setResumo(JOptionPane.showInputDialog("Digite o resumo do livro: "));
		String pag=JOptionPane.showInputDialog("Digite a quantidade de páginas: ");
		if(pag.isEmpty()){
			livro.setQntPaginas(0);
		}else{
			livro.setQntPaginas(Integer.parseInt(pag));
		}
		livro.setLocal(JOptionPane.showInputDialog("Digite o Local:"));
		livro.setDataDeAquisicao(adicionaData());
		livro.setLista(adicionaAutoresAoLivro());
		livro.setCategoria(adicionaCategoria());
		if (livro.getTitulo().isEmpty() || livro.getLocal().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Livro não cadastrado, os campos Titulo e Local devem ser preenchidos.");

		}else{
			grava.getCadastroDeLivros().add(livro);
			JOptionPane.showMessageDialog(null, "Livro cadastrado.");

		}

	}
	Data adicionaData(){
		Data data = new Data();
		String dia=JOptionPane.showInputDialog("Data de aquisição. Digite o dia: ");
		String mes = JOptionPane.showInputDialog("Digite o mes: ");
		String ano = JOptionPane.showInputDialog("Digite o ano: ");
		if (dia.isEmpty() || mes.isEmpty() || ano.isEmpty()) {


		}else{
			data.dia = Integer.parseInt(dia);
			data.mes = Integer.parseInt(mes);
			data.ano = Integer.parseInt(ano);

			boolean valida = data.ComparaData();
			if(valida == false){
				JOptionPane.showMessageDialog(null,"A data de aquisição não pode ser superior a data atual.");
				data = new Data();
				adicionaData();
			}
		}
		return data;


	}
	List<Autor> adicionaAutoresAoLivro(){
		List<Autor> listaAutores = new ArrayList<>();
		int qnt=0;
		String qntdade=JOptionPane.showInputDialog("Digite a quantidade de autores: ");
		if(qntdade.isEmpty()){
			JOptionPane.showMessageDialog(null, "preencha a quantidade de autores.");
			adicionaAutoresAoLivro();
		}else{
			qnt=Integer.parseInt(qntdade);
			for(int i=0;i<qnt;i++){

				String nAutor=(JOptionPane.showInputDialog("Digite o nome do autor: "));
				if (nAutor.isEmpty()){
					JOptionPane.showMessageDialog(null, "Autor não cadastrado, o campo Nome do autor deve ser preenchido.");
					adicionaAutoresAoLivro();
				}else{
					boolean achou = false;
					for (int j=0;j<grava.getCadastroDeAutores().size();j++){
						if (grava.getCadastroDeAutores().get(j).getNome().equalsIgnoreCase(nAutor)) {
							a = grava.getCadastroDeAutores().get(j);
							achou = true;
						}
					}

					if (!achou){
						JOptionPane.showMessageDialog(null,"Não foi possível encontrar o autor, um novo autor será cadastrado.");
						oa.cadastra();
						for (int j=0;j<grava.getCadastroDeAutores().size();j++){
							if (grava.getCadastroDeAutores().get(j).getNome().equalsIgnoreCase(nAutor)) {
								a = grava.getCadastroDeAutores().get(j);
								achou = true;
							}
						}
					}
				}
				listaAutores.add(a);

			}
		}
		return listaAutores;

	}

	Categoria adicionaCategoria(){

		String dCategoria=(JOptionPane.showInputDialog("Digite a categoria do livro: "));
		if (dCategoria.isEmpty()) {
			JOptionPane.showMessageDialog(null, "Categoria não cadastrada, o campo Categoria deve ser preenchido.");
			adicionaCategoria();
		}
		else{
			boolean achou = false;
			for (int i=0;i<grava.getCadastroDeCategoria().size();i++){
				if (grava.getCadastroDeCategoria().get(i).getDescricao().equalsIgnoreCase(dCategoria)) {
					c1 = grava.getCadastroDeCategoria().get(i);
					achou = true;
				}
			}

			if (!achou){
				JOptionPane.showMessageDialog(null,"Não foi possível encontrar a categoria, uma nova categoria será cadastrada.");
				oc.cadastra();
				for (int i=0;i<grava.getCadastroDeCategoria().size();i++){
					if (grava.getCadastroDeCategoria().get(i).getDescricao().equalsIgnoreCase(dCategoria)) {
						c1 = grava.getCadastroDeCategoria().get(i);
						achou = true;
					}
				}
			}
		}
		return c1;

	}
	public void altera(){
		String Tlivro = JOptionPane.showInputDialog("Digite o nome do livro que deseja Alterar: ");  
		for(int i=0;i<grava.getCadastroDeLivros().size();i++){  
			if(grava.getCadastroDeLivros().get(i).getTitulo().equals(Tlivro)){  
				String titulo=JOptionPane.showInputDialog("Digite o titulo do livro: ");
				if(titulo.isEmpty()){

				}else{
					grava.getCadastroDeLivros().get(i).setTitulo(titulo);
				}
				grava.getCadastroDeLivros().get(i).setResumo(JOptionPane.showInputDialog("Digite o resumo do livro: "));
				String pag=JOptionPane.showInputDialog("Digite a quantidade de páginas: ");
				if(pag.isEmpty()){
					livro.setQntPaginas(0);
				}else{
					livro.setQntPaginas(Integer.parseInt(pag));
				}
				String local=JOptionPane.showInputDialog("Digite o Local:");
				if(local.isEmpty()){

				}else{
					grava.getCadastroDeLivros().get(i).setLocal(local);
				}
				grava.getCadastroDeLivros().get(i).setDataDeAquisicao(adicionaData());;
				grava.getCadastroDeLivros().get(i).setLista(adicionaAutoresAoLivro());
				grava.getCadastroDeLivros().get(i).setCategoria(adicionaCategoria());
				JOptionPane.showMessageDialog(null,"Os dados foram alterados.");
			}
		}

	}

	public void busca(){
		grava.ordena();
		int opcao;
		do{
			opcao=Integer.parseInt(JOptionPane.showInputDialog(""
					+ "1-Buscar pelo Código\n"
					+ "2-Buscar pelo Título\n"
					+ "3-Buscar pela Categoria\n"
					+ "4-Buscar pelo Autor\n"
					+ "5-Voltar"));
			switch(opcao){
			case 1:b.buscaCod();;break;
			case 2:b.buscaTitulo();break;
			case 3:b.buscaCategoria();break;
			case 4:b.buscaAutor();break;
			}
		}while(opcao!=5);
	}

	public void exclui(){
		String nLivro=(JOptionPane.showInputDialog("Digite titulo do livro a ser removido."));
		boolean achou = false;
		for (int i=0;i<grava.getCadastroDeLivros().size();i++){
			if (grava.getCadastroDeLivros().get(i).getTitulo().equalsIgnoreCase(nLivro)) {
				grava.getCadastroDeLivros().remove(i);
				achou = true;
			}
		}
		if (!achou){
			JOptionPane.showMessageDialog(null,"Não foi possível remover o livro.");
		}
		else{
			JOptionPane.showMessageDialog(null,"livro removido.");
		}
	}

}

