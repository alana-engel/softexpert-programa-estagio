package com.softexpert.library;

import java.text.ParseException;
import java.util.Scanner;

import com.softexpert.library.operations.UserOperations;
import com.softexpert.library.repository.CannotLocateRepositoryException;
import com.softexpert.library.repository.InvalidEntityException;

public class Main {
    
    public static void main(String[] args) {
        new Main();
    }
    
    private Scanner scanner = new Scanner(System.in);

    public Main() {
        new DataPopulator().initializeData();
        executionWorkflow();
    }
    
    public void executionWorkflow() {
        while (true) {
            showMenu();
            try {
                executeOptionFromMenu();
            }catch (InvalidEntityException | CannotLocateRepositoryException | ParseException e) {
                System.err.println("Erro! " + e.getMessage());
            }catch (Exception e) {
                System.err.println("Erro fatal! " + e.getMessage());
                e.printStackTrace();
                System.exit(0);
            }
        }
    }
    
    public void showMenu() {
        System.out.println("\nSelecione uma opção:");
        for (UserOperations operacao : UserOperations.values()) {
            System.out.println(operacao);
        }
    }
    
    public void executeOptionFromMenu() throws Exception {
        Integer opcao = scanner.nextInt();
        UserOperations operacao = UserOperations.valueOfOpcao(opcao);
        operacao.getConsoleOperation().execute(scanner);
    }
}
