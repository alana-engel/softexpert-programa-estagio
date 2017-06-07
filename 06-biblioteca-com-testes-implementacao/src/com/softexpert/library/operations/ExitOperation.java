package com.softexpert.library.operations;

import java.util.Scanner;

public class ExitOperation implements ConsoleOperation {

    @Override
    public void execute(Scanner scanner) throws Exception {
        System.out.println("Fim!");
        System.exit(0);
    }

}
