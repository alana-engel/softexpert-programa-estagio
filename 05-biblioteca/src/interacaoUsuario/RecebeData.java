package interacaoUsuario;
import javax.swing.JOptionPane;

import outrasOperacoes.Data;

public class RecebeData {

	public Data recebeDataLivro(){
		Data data = new Data();
		String dia=JOptionPane.showInputDialog("Data de aquisição. Digite o dia: ");
		String mes = JOptionPane.showInputDialog("Digite o mes: ");
		String ano = JOptionPane.showInputDialog("Digite o ano: ");
		if (dia.isEmpty() || mes.isEmpty() || ano.isEmpty()) {


		}else{
			int diaInt=Integer.parseInt(dia);
			int mesInt = Integer.parseInt(mes);
			int anoInt = Integer.parseInt(ano);
			data.dia = diaInt;
			data.mes = mesInt;
			data.ano = anoInt;
			boolean validaData = data.verificaData(diaInt, mesInt, anoInt);
			boolean valida = data.ComparaData();
			if(validaData == false){
				JOptionPane.showMessageDialog(null,"Data inválida.");
				data = new Data();
				recebeDataLivro();
			}else if(valida == false){
				JOptionPane.showMessageDialog(null,"A data de aquisição não pode ser superior a data atual.");
				data = new Data();
				recebeDataLivro();
			}
		}
		return data;


	}
	public Data recebeDataAutor(){
		Data data = new Data();
		String dia=JOptionPane.showInputDialog("Data de Nascimento. Digite o dia: ");
		String mes = JOptionPane.showInputDialog("Digite o mes: ");
		String ano = JOptionPane.showInputDialog("Digite o ano: ");
		if (dia.isEmpty() || mes.isEmpty() || ano.isEmpty()) {


		}else{
			int diaInt=Integer.parseInt(dia);
			int mesInt = Integer.parseInt(mes);
			int anoInt = Integer.parseInt(ano);
			data.dia = diaInt;
			data.mes = mesInt;
			data.ano = anoInt;
			boolean validaData = data.verificaData(diaInt, mesInt, anoInt);
			boolean valida = data.ComparaData();
			if(validaData == false){
				JOptionPane.showMessageDialog(null,"Data inválida.");
				data = new Data();
				recebeDataLivro();
			}else if(valida == false){
				JOptionPane.showMessageDialog(null,"A data de nascimento nao pode ser superior a data atual.");
				data = new Data();
				recebeDataAutor();
			}
		}
		return data;

	}

}
