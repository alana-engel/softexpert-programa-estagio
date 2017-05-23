import javax.swing.JOptionPane;

public class OperacoesAutores implements Opcoes{
	Autor autores = new Autor();
	Repositorio grava = new Repositorio();



	public void cadastra(){
		String n = "";
		Autor autores = new Autor();
		autores.setCodAutores(grava.getCadastroDeAutores().size() +1);
		n=JOptionPane.showInputDialog("Digite o nome do autor: ");
		if (n.isEmpty()) {
			JOptionPane.showMessageDialog(null, "O Campo Nome do autor deve ser preenchido.");
			cadastra();
		}else{
			autores.setNome(n);
			autores.setDataDeNascimento(adicionaData());
			autores.setNacionalidade((JOptionPane.showInputDialog("Digite a nacionalidade: ")));
			grava.getCadastroDeAutores().add(autores);
			JOptionPane.showMessageDialog(null, "Autor Cadastrado.");
		}
	}

	public void altera(){
		String nAutor = JOptionPane.showInputDialog("Digite o nome do Autor que deseja Alterar");  
		for(int i=0;i<grava.getCadastroDeAutores().size();i++){  
			if(grava.getCadastroDeAutores().get(i).getNome().equals(nAutor)){  
				grava.getCadastroDeAutores().get(i).setNome((JOptionPane.showInputDialog("Digite o nome do autor: ")));  
				grava.getCadastroDeAutores().get(i).setDataDeNascimento(adicionaData());
				grava.getCadastroDeAutores().get(i).setNacionalidade((JOptionPane.showInputDialog("Digite a nacionalidade: ")));
				JOptionPane.showMessageDialog(null,"Os dados foram alterados.");
			}

		}
	}
	Data adicionaData(){
		Data data = new Data();
		String dia=JOptionPane.showInputDialog("Data de Nascimento. Digite o dia: ");
		String mes = JOptionPane.showInputDialog("Digite o mes: ");
		String ano = JOptionPane.showInputDialog("Digite o ano: ");
		if (dia.isEmpty() || mes.isEmpty() || ano.isEmpty()) {


		}else{
			data.dia = Integer.parseInt(dia);
			data.mes = Integer.parseInt(mes);
			data.ano = Integer.parseInt(ano);

			boolean valida = data.ComparaData();
			if(valida == false){
				JOptionPane.showMessageDialog(null,"A data de nascimento nao pode ser superior a data atual.");
				data = new Data();
				adicionaData();
			}
		}
		return data;

	}
	public void busca(){
		String imp=new String();
		String nAutor=(JOptionPane.showInputDialog("Digite o nome do autor: "));
		boolean achou = false;
		for (int i=0;i<grava.getCadastroDeAutores().size();i++){
			if (grava.getCadastroDeAutores().get(i).getNome().equalsIgnoreCase(nAutor)) {
				autores = grava.getCadastroDeAutores().get(i);
				achou = true;
			}
		}

		if (!achou){
			JOptionPane.showMessageDialog(null,"Não foi possível encontrar o autor.");
		}
		else{
			imp=imp+"Código do Autor: "+autores.getCodAutores()+"\n"
					+"Nome do Autor: "+autores.getNome()
					+"\n"+"Data de Nascimento: "+autores.getDataDeNascimento()+"\n"
					+"Nacionalidade: "+autores.getNacionalidade();
			JOptionPane.showMessageDialog(null, imp);
		}
	}

	public void exclui(){
		String nAutor=(JOptionPane.showInputDialog("Digite o nome do autor a ser removido."));
		boolean achou = false;
		for (int i=0;i<grava.getCadastroDeAutores().size();i++){
			if (grava.getCadastroDeAutores().get(i).getNome().equalsIgnoreCase(nAutor)) {
				grava.getCadastroDeAutores().remove(i);
				achou = true;
			}
		}
		if (!achou){
			JOptionPane.showMessageDialog(null,"Não foi possível remover o autor.");
		}
		else{
			JOptionPane.showMessageDialog(null,"autor removido.");
		}
	}
}
