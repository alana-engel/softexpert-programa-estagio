package br.com.softexpert.library.main;

import javax.swing.JOptionPane;

import br.com.softexpert.library.operations.file.SaveInFile;

public class LibraryMainMenu {
	public static void main(String []args)  {
		SaveInFile save = new SaveInFile();
		Menu1 menu =new Menu1();
		int option;
		do{
			option=Integer.parseInt(JOptionPane.showInputDialog(""
					+ "1-Cadastrar\n"
					+ "2-Alterar dados\n"
					+ "3-Buscar\n"
					+ "4-Excluir\n"
					+ "5-Gravar dados em arquivo\n"
					+ "6-Ler dados do arquivo\n"
					+ "7-Sair"));
			switch(option){
			case 1:menu.menuCreate();break;
			case 2:menu.menuUpdate();break;
			case 3:menu.menuFind();break;
			case 4:menu.menuDelete();break;
			case 5:save.saveRecordsInFile();break;
			case 6:save.getRecordsFromFile();break;
			}
		}while(option!=7);
	
	}
}
