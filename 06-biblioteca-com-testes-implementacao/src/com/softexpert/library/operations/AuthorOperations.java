package com.softexpert.library.operations;

import java.util.Date;
import java.util.List;

import com.softexpert.library.entity.Author;
import com.softexpert.library.repository.AuthorRepository;
import com.softexpert.library.repository.RepositoryFactory;
import com.softexpert.library.utils.DateConverter;

public class AuthorOperations {

    private static AuthorRepository repository = (AuthorRepository) RepositoryFactory.getRepositoryFrom(Author.class);

    public static ConsoleOperation cadastrarAutor() {

        return (scanner) -> {

            System.out.println("========== CADASTRO DE AUTOR ==========");
            System.out.print("Nome: ");
            scanner.nextLine();
            String nome = scanner.nextLine();
            System.out.print("Data de nascimento (dd/mm/yyyy): ");
            Date nascimento = DateConverter.asDate(scanner.nextLine());
            System.out.print("Nacionalidade: ");
            String nacionalidade = scanner.nextLine();

            Author autor = new Author();
            autor.setName(nome);
            autor.setNationality(nacionalidade);
            autor.setBirthday(nascimento);

            repository.save(autor);
            System.out.println("Autor cadastrado = " + autor);
        };
    }

    public static ConsoleOperation listarAutores() {

        return (scanner) -> {
            List<Author> listaDeAutores = repository.listAll();
            for (Author autor : listaDeAutores) {
                System.out.println(autor);
            }
        };
    }
    
    public static ConsoleOperation listarAutoresPorNome() {

        return (scanner) -> {
            System.out.print("Filtro (nome): ");
            scanner.nextLine();
            String authorName = scanner.nextLine();
            
            List<Author> listaDeAutores = repository.listByName(authorName);
            for (Author autor : listaDeAutores) {
                System.out.println(autor);
            }
        };
    }

}
