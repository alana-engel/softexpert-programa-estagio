package interacaoUsuario;
import javax.swing.JOptionPane;

import biblioteca.Autor;
import interfaces.Acoes;
import operacoes.OperacoesDadosAutorEmMemoria;
import outrasOperacoes.ValidacaoRegistroExistente;


public class CadastroAutorI{
	RecebeData data = new RecebeData();
	ValidacaoRegistroExistente valida = new ValidacaoRegistroExistente();
	Acoes<Autor> gravaAutor= new OperacoesDadosAutorEmMemoria();
	public void cadastra(){
		Autor autor = new Autor();
		String n=JOptionPane.showInputDialog("Digite o nome do autor: ");
		 if(valida.verificaAutorJaCadastrado(n) == false){
			 JOptionPane.showMessageDialog(null,"O autor ja está cadastrado.");
		 }else{
		if (n.isEmpty()) {
			cadastra();
		}else{
			autor.setNome(n);
			autor.setDataDeNascimento(data.recebeDataAutor());
			autor.setNacionalidade((JOptionPane.showInputDialog("Digite a nacionalidade: ")));
		}
		if(gravaAutor.cadastra(autor)==true){
			JOptionPane.showMessageDialog(null, "Autor Cadastrado.");
		}else{
			JOptionPane.showMessageDialog(null, "O Campo Nome do autor deve ser preenchido.");
			cadastra();
		}
		 }
	}
	
	
}
