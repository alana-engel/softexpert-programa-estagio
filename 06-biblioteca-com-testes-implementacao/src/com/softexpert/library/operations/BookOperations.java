package com.softexpert.library.operations;

import java.text.ParseException;
import java.util.List;
import java.util.Scanner;

import com.softexpert.library.entity.Author;
import com.softexpert.library.entity.Book;
import com.softexpert.library.entity.Category;
import com.softexpert.library.repository.BookRepository;
import com.softexpert.library.repository.Repository;
import com.softexpert.library.repository.RepositoryFactory;
import com.softexpert.library.utils.DateConverter;

public class BookOperations {

    private static BookRepository bookRepository = (BookRepository) RepositoryFactory.getRepositoryFrom(Book.class);
    private static Repository<Category> categoryRepository = RepositoryFactory.getRepositoryFrom(Category.class);
    private static Repository<Author> authorRepository = RepositoryFactory.getRepositoryFrom(Author.class);

    public static ConsoleOperation cadastrarLivro() {

        return (scanner) -> {
            Book book = getBookFromConsole(scanner, null);
            bookRepository.save(book);
            System.out.println("Livro cadastrado = " + book);
        };
    }
    
    public static ConsoleOperation alterarLivro() {
        return scanner -> {
            System.out.print("Insira o código do livro que seja alterar: ");
            scanner.nextLine();
            Integer id = scanner.nextInt();
            Book book = bookRepository.loadById(id);
            if (book == null) {
                System.out.println("Não existe nenhum livro com o código " + id);
                return;
            }
            
            System.out.println("\nAlterando informações do livro:");
            System.out.println(book);
            System.out.println();
            book = getBookFromConsole(scanner, book);
            bookRepository.update(book);
            System.out.println("Livro " + id + " alterado = " + book);
        };
    }

    public static ConsoleOperation listarLivros() {
        return (scanner) -> {
            printarLivrosNaConsole(bookRepository.listAll());
        };
    }
    
    public static ConsoleOperation listarLivrosPeloNomeDoAutor() {
        return (scanner) -> {
            System.out.print("Informe o nome do autor para buscar livros: ");
            scanner.nextLine();
            String filtro = scanner.nextLine();
            printarLivrosNaConsole(bookRepository.listByAuthorName(filtro));
        };
    }
    
    private static Book getBookFromConsole(Scanner scanner, Book bookToEdit) throws ParseException {
        Book book = new Book();
        if (bookToEdit != null) {
            book.setId(bookToEdit.getId());
            book.setBarcode(bookToEdit.getBarcode());
        }
        System.out.println("========== CADASTRO/ALTERAÇÃO DE LIVRO ==========");

        System.out.print("Título: ");
        scanner.nextLine();
        book.setTitle(scanner.nextLine());

        System.out.print("Resumo: ");
        book.setSummary(scanner.nextLine());

        System.out.print("Páginas: ");
        book.setPages(scanner.nextInt());

        System.out.print("Localização: ");
        book.setLocation(scanner.nextLine());

        System.out.print("Data de aquisição (DD/MM/YYYY): ");
        book.setAcquisition(DateConverter.asDate(scanner.nextLine()));

        System.out.println("Categoria (insira o código de uma das categorias abaixo): ");
        System.out.println(categoryRepository.listAll());
        book.setCategory(categoryRepository.loadById(scanner.nextInt()));

        System.out.println("Autor (insira o código de um ou mais autor): ");
        System.out.println(authorRepository.listAll());
        scanner.nextLine();
        String authors = scanner.nextLine();
        addAuthors(authors, book);
        
        return book;
    }
    
    private static void addAuthors(String authorsIDs, Book book) {
        String[] ids = authorsIDs.split("\\s+");
        for (String id : ids) {
            Author autorEscolhido = authorRepository.loadById(Integer.parseInt(id));
            book.addAuthor(autorEscolhido);
        }
    }
    
    private static void printarLivrosNaConsole(List<Book> books) {
        for (Book book : books) {
            System.out.println(book);
            System.out.println();
        }
    }

}
