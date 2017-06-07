package com.softexpert.library.operations;

import com.softexpert.library.entity.Category;
import com.softexpert.library.repository.Repository;
import com.softexpert.library.repository.RepositoryFactory;

public class CategoryOperations {

    private static Repository<Category> repository = RepositoryFactory.getRepositoryFrom(Category.class);

    public static ConsoleOperation cadastrarCategoria() {

        return (scanner) -> {

            Category categoria = new Category();
            System.out.println("========== CADASTRO DE CATEGORIA ==========");
            System.out.print("Descrição: ");
            scanner.nextLine();
            categoria.setDescription(scanner.nextLine());

            repository.save(categoria);
            System.out.println("Categoria cadastrada = " + categoria);
        };
    }

}
