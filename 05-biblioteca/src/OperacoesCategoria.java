import javax.swing.JOptionPane;

public class OperacoesCategoria implements Opcoes{
	Categoria c = new Categoria();
	Repositorio grava = new Repositorio();


	public void cadastra(){
		String categoria="";
		Categoria c = new Categoria();
		c.setCodCategoria(grava.getCadastroDeCategoria().size() +1);
		categoria=JOptionPane.showInputDialog("Digite a categoria a ser cadastrada: ");
		if (categoria.isEmpty()) {
			JOptionPane.showMessageDialog(null, "O campo Categoria deve ser preenchido.");
			cadastra();
		}else{
			c.setDescricao(categoria);
			grava.getCadastroDeCategoria().add(c);
			JOptionPane.showMessageDialog(null, "Categoria Cadastrada.");
		}
	}
	public void altera(){
		String dCategoria = JOptionPane.showInputDialog("Digite a categoria que deseja Alterar");  
		for(int i=0;i<grava.getCadastroDeCategoria().size();i++){  
			if(grava.getCadastroDeCategoria().get(i).getDescricao().equals(dCategoria)){  
				grava.getCadastroDeCategoria().get(i).setDescricao((JOptionPane.showInputDialog("Digite a nova categoria do livro: ")));  
				JOptionPane.showMessageDialog(null,"Os dados foram alterados.");
			}
		}
	}
	public void busca(){
		String imp=new String();
		String dCategoria=(JOptionPane.showInputDialog("Digite a categoria: "));
		boolean achou = false;
		for (int i=0;i<grava.getCadastroDeCategoria().size();i++){
			if (grava.getCadastroDeCategoria().get(i).getDescricao().equalsIgnoreCase(dCategoria)) {
				c = grava.getCadastroDeCategoria().get(i);
				achou = true;
			}
		}

		if (!achou){
			JOptionPane.showMessageDialog(null,"Não foi possível encontrar a categoria");
		}
		else{

			imp=imp+"Código da Categoria: "+c.getCodCategoria()+"\n"+"Descrição da Categoria: "+c.getDescricao();
			JOptionPane.showMessageDialog(null, imp);
		}
	}
	public void exclui(){
		String dcategoria=(JOptionPane.showInputDialog("Digite a categoria a ser removida."));
		boolean achou = false;
		for (int i=0;i<grava.getCadastroDeCategoria().size();i++){
			if (grava.getCadastroDeCategoria().get(i).getDescricao().equalsIgnoreCase(dcategoria)) {
				grava.getCadastroDeCategoria().remove(i);
				achou = true;
			}
		}
		if (!achou){
			JOptionPane.showMessageDialog(null,"Não foi possível remover a categoria.");
		}
		else{
			JOptionPane.showMessageDialog(null,"Categoria removida.");
		}
	}
}

